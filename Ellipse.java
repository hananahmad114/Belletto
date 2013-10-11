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

public class Ellipse extends Shape {
    
    protected int width;
    protected int height;
    protected Pixel center;
    
    // setVars
    @Override
    public void setVars(Pixel start, Pixel end, Color color) {
        this.color = color;
        this.start = start;
        this.end = end;
        this.center = new Pixel((start.getX()+end.getX())/2,
                                (start.getY()+end.getY())/2);
        this.width = Math.abs(start.getX() - end.getX());
        this.height = Math.abs(start.getY() - end.getY());
    }
    
    // paint:
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(center.getX()-width/2,
                                     center.getY()-height/2,
                                     width, height));
        if (selected) {
            g2.setColor(new Color(0xFF ^ color.getRed(),
                                 0xFF ^ color.getGreen(),
                                 0xFF ^ color.getBlue()));
            g2.setStroke(new BasicStroke(3));
            g2.draw(new Ellipse2D.Double(center.getX()-width/2,
                                         center.getY()-height/2,
                                         width, height));
        }
        
    }

    @Override
    public boolean contains(Pixel point) {
        int x = point.getX();
        int y = point.getY();
        int rx = width/2;
        int ry = height/2;
        int h = center.getX();
        int k = center.getY();
        
        // ellipse equation:
        double d = Math.pow(x-h,2)/Math.pow(rx, 2) + 
                   Math.pow(y-k,2)/Math.pow(ry, 2);
        
        if (d <= 1) {
            return true;
        } else {
            return false;
        }
    }
    
}
