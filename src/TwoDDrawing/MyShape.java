package TwoDDrawing;

import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;

public abstract class MyShape {

    private Point startingPt, endingPt;
    private BasicStroke basicStroke;
    private Paint paint;

    public MyShape(Point startingPt, Point endingPt, BasicStroke basicStroke, Paint paint) {
        this.startingPt = startingPt;
        this.endingPt = endingPt;
        this.basicStroke = basicStroke;
        this.paint = paint;
    }

    public abstract void drawShape(Graphics2D g2d);

    public void setStartingPt(Point startingPt) {
        this.startingPt = startingPt;
    }

    public void setEndingPt(Point endingPt) {
        this.endingPt = endingPt;
    }

    public void setBasicStroke(BasicStroke basicStroke) {
        this.basicStroke = basicStroke;
    }
    
    public void setGradient(GradientPaint gradientPaint) {
        this.paint = gradientPaint;
    }

    public Point getStartingPt() {
        return startingPt;
    }

    public Point getEndingPt() {
        return endingPt;
    }

    public BasicStroke getBasicStroke() {
        return basicStroke;
    }

    public Paint getPaint() {
        return paint;
    }
}
