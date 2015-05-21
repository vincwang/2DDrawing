/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwoDDrawing;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author vinc_wng
 */
public class MyOval extends MyBoundedShape {

    public MyOval(Point startingPt, Point endingPt, BasicStroke basicStroke, Paint paint, boolean filledOrNot) {
        super(startingPt, endingPt, basicStroke, paint, filledOrNot);
    }

    @Override
    public void drawShape(Graphics2D g2d) {
        g2d.setStroke(getBasicStroke());
        g2d.setPaint(getPaint());

        if (getisFilledOrNot()) {
            g2d.fill(new Ellipse2D.Double(getUpperLeftPoint().x, getUpperLeftPoint().y, getLength(), getWidth()));
        } else {
            g2d.draw(new Ellipse2D.Double(getUpperLeftPoint().x, getUpperLeftPoint().y, getLength(), getWidth()));
        }
    }

}
