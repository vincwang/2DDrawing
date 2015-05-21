package TwoDDrawing;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DrawShapesFrame extends JFrame {

    private int shapeSelected = 0;
    private boolean filledOrNot;
    private boolean useGradientOrNot;
    private int lineWidth = 10;
    private int dashLength = 15;
    float[] dashes = {dashLength};
    private boolean dashedOrNot;
    private final JLabel mouseStatusBar = new JLabel("");
    private Paint paint;

    ArrayList<MyShape> myShapesArray = new ArrayList<>();
    MyShape currentShape;

////////////compoenents for MenuJPanelA///////
    private final JButton undo = new JButton("Undo");
    private final JButton clear = new JButton("Clear");
    private final JLabel shapeLabel = new JLabel("Shape:");
    private final String[] shapeNames = {"line", "oval", "rectangle"};
    private final JComboBox<String> shapeComboBox = new JComboBox<>(shapeNames);
    private final JCheckBox filledCheckBox = new JCheckBox("filled");
    ;

////////////compoenents for MenuJPanelB///////    
    private final JCheckBox useGradientCheckBox = new JCheckBox("Use Gradient");
    private Color color1st = Color.BLACK;
    private Color color2nd = Color.WHITE;
    private final JButton change1stColorButton = new JButton("1st Color");
    private final JButton change2ndColorButton = new JButton("2nd Color");
    private final JLabel lineWidthLabel = new JLabel("Line Width: ");
    private final JTextField lineWidthTextField = new JTextField("10", 2);
    private final JLabel dashLengthLabel = new JLabel("Dash Length:");
    private final JTextField dashLengthTextField = new JTextField("15", 2);
    private final JCheckBox dashedCheckBox = new JCheckBox("Dashed");

    public DrawShapesFrame() {
        super("Java 2D Drawing");
        setLayout(new BorderLayout());
        setSize(750, 500);

////////////////Consctructing MenuA///////////////////
        JPanel menuJPanelA = new JPanel();
        menuJPanelA.setLayout(new FlowLayout());
        menuJPanelA.setBackground(Color.LIGHT_GRAY);

        menuJPanelA.add(undo);
        menuJPanelA.add(clear);
        menuJPanelA.add(shapeLabel);
        menuJPanelA.add(shapeComboBox);
        menuJPanelA.add(filledCheckBox);

        UndoButtonHandler undoButtonHandler = new UndoButtonHandler();
        undo.addActionListener(undoButtonHandler);

        ClearButtonHandler clearButtonHandler = new ClearButtonHandler();
        clear.addActionListener(clearButtonHandler);

        ShapeComboBoxHandler shapeComboBoxHandler = new ShapeComboBoxHandler();
        shapeComboBox.addItemListener(shapeComboBoxHandler);

        FilledCheckBoxHandler filledCheckBoxHandler = new FilledCheckBoxHandler();
        filledCheckBox.addItemListener(filledCheckBoxHandler);

////////////////Consctructing MenuB/////////////////// 
        JPanel menuJPanelB = new JPanel();
        menuJPanelB.setLayout(new FlowLayout());
        menuJPanelB.setBackground(Color.LIGHT_GRAY);

        menuJPanelB.add(useGradientCheckBox);
        menuJPanelB.add(change1stColorButton);
        menuJPanelB.add(change2ndColorButton);
        menuJPanelB.add(lineWidthLabel);
        menuJPanelB.add(lineWidthTextField);
        menuJPanelB.add(dashLengthLabel);
        menuJPanelB.add(dashLengthTextField);
        menuJPanelB.add(dashedCheckBox);

        UseGradientCheckBoxHandler useGradientCheckBoxHandler = new UseGradientCheckBoxHandler();
        useGradientCheckBox.addItemListener(useGradientCheckBoxHandler);

        Change1stColorHandler change1stColorHandler = new Change1stColorHandler();
        change1stColorButton.addActionListener(change1stColorHandler);

        Change2ndColorHandler change2ndColorHandler = new Change2ndColorHandler();
        change2ndColorButton.addActionListener(change2ndColorHandler);

        LineWidthTextFieldHandler lineWidthTextFieldHandler = new LineWidthTextFieldHandler();
        lineWidthTextField.addActionListener(lineWidthTextFieldHandler);

        DashLengthTextFieldHandler dashLengthTextFieldHandler = new DashLengthTextFieldHandler();
        dashLengthTextField.addActionListener(dashLengthTextFieldHandler);

        DashedCheckBoxHandler dashedCheckBoxHandler = new DashedCheckBoxHandler();
        dashedCheckBox.addItemListener(dashedCheckBoxHandler);

////////////////Consctructing MenuCombined/////////////////// 
        JPanel menuCombined = new JPanel();
        menuCombined.setLayout(new GridLayout(2, 1));
        menuCombined.add(menuJPanelA);
        menuCombined.add(menuJPanelB);
        add(menuCombined, BorderLayout.NORTH);

////////////////Consctructing DrawShapesPanel////////////////        
        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setLayout(new BorderLayout());
        drawPanel.add(mouseStatusBar, BorderLayout.SOUTH);
        add(drawPanel, BorderLayout.CENTER);

        setVisible(true);
/////////////////////////////////////////////////////////////

    }

    private class DrawPanel extends JPanel {

        MouseHandler mouseHandler = new MouseHandler();

        public DrawPanel() {
            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            for (MyShape shape : myShapesArray) {
                shape.drawShape(g2d);
            }
            
            if (currentShape != null) {
                currentShape.drawShape(g2d);
            }
        }
    }

    private class UndoButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!myShapesArray.isEmpty()) {
                myShapesArray.remove(myShapesArray.size() - 1);
            }
            repaint();
        }
    }

    private class ClearButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            while (!myShapesArray.isEmpty()) {
                myShapesArray.remove(myShapesArray.size() - 1);
            }
            repaint();
        }
    }

    private class ShapeComboBoxHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                shapeSelected = shapeComboBox.getSelectedIndex();
            }
        }
    }

    private class FilledCheckBoxHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            filledOrNot = filledCheckBox.isSelected();
        }
    }

    private class UseGradientCheckBoxHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            useGradientOrNot = useGradientCheckBox.isSelected() ? true : false;
        }
    }

    private class Change1stColorHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            color1st = JColorChooser.showDialog(DrawShapesFrame.this, "Choose first color", color1st);
        }
    }

    private class Change2ndColorHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            color2nd = JColorChooser.showDialog(DrawShapesFrame.this, "Choose first color", color2nd);
        }
    }

    private class LineWidthTextFieldHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            lineWidth = Integer.parseInt(event.getActionCommand());
        }
    }

    private class DashLengthTextFieldHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            dashLength = Integer.parseInt(event.getActionCommand());
        }
    }

    private class DashedCheckBoxHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            dashedOrNot = dashedCheckBox.isSelected() ? true : false;
        }
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {

        @Override
        public void mousePressed(MouseEvent event) {
            
            dashes[0] = dashLength;
            BasicStroke stroke;
            if (dashedOrNot == true) {
                stroke = new BasicStroke(lineWidth,
                        BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0);
            } else {
                stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            }

            if (useGradientOrNot) {
                paint = new GradientPaint(event.getPoint(), color1st, event.getPoint(), color2nd, true);
            } else {
                paint = color1st;
            }

            if (shapeSelected == 0) {
                currentShape = new MyLine(event.getPoint(), event.getPoint(), stroke, paint);
            } else if (shapeSelected == 1) {
                currentShape = new MyOval(event.getPoint(), event.getPoint(), stroke, paint, filledOrNot);
            } else if (shapeSelected == 2) {
                currentShape = new MyRectangle(event.getPoint(), event.getPoint(), stroke, paint, filledOrNot);
            }

        }

        @Override
        public void mouseDragged(MouseEvent event) {

            currentShape.setEndingPt(new Point(event.getX(), event.getY()));
            if (useGradientOrNot) {
                currentShape.setGradient(new GradientPaint(currentShape.getStartingPt(), color1st, event.getPoint(), color2nd, true));
            }
            mouseStatusBar.setText(String.format("(%d, %d)", event.getX(), event.getY()));
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            currentShape.setEndingPt(new Point(event.getX(), event.getY()));
            myShapesArray.add(currentShape);
            currentShape = null;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent event) {
            mouseStatusBar.setText("Mouse Outside Drawpanel");
        }

        @Override
        public void mouseEntered(MouseEvent event) {

        }

        @Override
        public void mouseClicked(MouseEvent event) {

        }

        @Override
        public void mouseMoved(MouseEvent event) {
            mouseStatusBar.setText(String.format("(%d, %d)", event.getX(), event.getY()));
        }
    }

}
