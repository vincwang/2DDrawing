/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwoDDrawing;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Point;

public abstract class MyBoundedShape extends MyShape {

    private boolean filledOrNot;
    
    public MyBoundedShape(Point startingPt, Point endingPt, BasicStroke basicStroke, Paint paint, boolean filledOrNot) {
        super(startingPt, endingPt, basicStroke, paint);
        this.filledOrNot = filledOrNot;
    }

    public void setFilledOrNot(boolean filledOrNot) {
        this.filledOrNot = filledOrNot;
    }
    
    public boolean getisFilledOrNot() {
        return filledOrNot;
    }

    public double getLength() {
        return Math.abs(getEndingPt().x - getStartingPt().x);
    }

    public double getWidth() {
        return Math.abs(getEndingPt().y - getStartingPt().y);
    }
    
    public Point getUpperLeftPoint() {
        Point upperLeftPoint = new Point();
        upperLeftPoint.x = (getStartingPt().x < getEndingPt().x) ? getStartingPt().x : getEndingPt().x;
        upperLeftPoint.y = (getStartingPt().y < getEndingPt().y) ? getStartingPt().y : getEndingPt().y;
        return upperLeftPoint;
    }
    

}
