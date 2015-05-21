package TwoDDrawing;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.geom.Line2D;


public class MyLine extends MyShape {

    public MyLine (Point startingPt, Point endingPt, BasicStroke basicStroke, Paint paint)
    {
        super(startingPt, endingPt, basicStroke, paint);
    }

    @Override
    public void drawShape(Graphics2D g2d){        
        g2d.setPaint(getPaint());
        g2d.setStroke(getBasicStroke());       
        g2d.draw(new Line2D.Double(getStartingPt().x, getStartingPt().y, getEndingPt().x, getEndingPt().y)); 
    }

}
