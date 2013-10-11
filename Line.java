/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iocoder
 */

import java.awt.*;
import java.awt.geom.*;

public class Line extends Shape {
    
    // setVars
    @Override
    public void setVars(Pixel start, Pixel end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }
    
    // paint:
    @Override
    public void paint(Graphics g) {
        
        int stroke = 6;
        Graphics2D g2 = (Graphics2D) g;
        
        if (selected) {
            g2.setColor(new Color(0xFF^color.getRed(),
                                  0xFF^color.getGreen(),
                                  0xFF^color.getBlue()));
            g2.setStroke(new BasicStroke(stroke));
            g2.draw(new Line2D.Float(start.getX(), start.getY(),
                                     end.getX(), end.getY()));
            stroke-=3;
        }
        
        g2.setColor(color);
        g2.setStroke(new BasicStroke(stroke));
        g2.draw(new Line2D.Float(start.getX(), start.getY(),
                                 end.getX(), end.getY()));
        
    }

    @Override
    public boolean contains(Pixel point) {
        
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = point.getX();
        double y3 = point.getY();
        Pixel i;
        
        // special cases:
        if (start.getX() == end.getX()) {
            // perpendicular to y
            // L: x = x1 = x2
            // intersection point:
            i = new Pixel(start.getX(), point.getY());
        } else if (y1 == y2) {
            // perpendicular to x:
            // L: y = y1 = y2
            // intersection point:
            i = new Pixel(point.getX(), start.getY());
        } else {

            // equation of the drawn line:
            // L1: Y = mX + b
            // get m:
            double m = (y2-y1)/(x2-x1);

            // get b:
            double b = y1 - m*x1;

            // euqation of perpendicular:
            // L2: Y = (-1/m)X + c
            // we need c
            double c = y3 + x3/m;

            // we want to know intersection point
            // solve to lines together
            // mX + b = (-1/m)X + c
            // m^2X + mb = -X + mc
            // (m^2+1)X = mc - mb
            // X = (m*(c-b))/(m^2+1)
            double x4 = (m*(c-b))/(m*m+1);
            double y4 = m*x4 + b;
            i = new Pixel((int)x4, (int)y4);
        }
        
        // intersection point belongs to the visible part of the line?
        double d1 = Pixel.distance(start, i);
        double d2 = Pixel.distance(end, i);
        double d = Pixel.distance(start, end);

        if (Math.abs(d-(d1+d2)) > 1) {
            // d != d1+d2
            return false;
        }
        
        // calculate distance between (x4, y4) and (x3, y3):
        d = Pixel.distance(i, point);
        if (d < 6 /*strok*/) {
            return true;
        } else {
            return false;
        }
        
    }
    
}
