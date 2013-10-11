/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iocoder
 */

import java.awt.*;

public class Rectangle extends Shape {
    
    // instance variables:
    protected int width;
    protected int height;
    protected Pixel center;
    
    // setVars
    @Override
    public void setVars(Pixel start, Pixel end, Color color) {
        this.start = start;
        this.end = end;
        this.center = new Pixel((start.getX()+end.getX())/2,
                                (start.getY()+end.getY())/2);
        this.width = Math.abs(start.getX() - end.getX());
        this.height = Math.abs(start.getY() - end.getY());
        this.color = color;
    }
    
    // paint:
    @Override
    public void paint(Graphics g) {
        
        g.setColor(color);
        g.fillRect(center.getX()-width/2, center.getY()-height/2,
                   width, height);
        
        if (selected) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(0xFF ^ color.getRed(),
                                 0xFF ^ color.getGreen(),
                                 0xFF ^ color.getBlue()));
            g2.setStroke(new BasicStroke(3));
            g2.drawRect(center.getX()-width/2, center.getY()-height/2,
                        width, height);
        }
        
    }

    @Override
    public boolean contains(Pixel point) {
        int minX = start.getX() < end.getX() ? start.getX() : end.getX();
        int maxX = start.getX() > end.getX() ? start.getX() : end.getX();
        int minY = start.getY() < end.getY() ? start.getY() : end.getY();
        int maxY = start.getY() > end.getY() ? start.getY() : end.getY();
        if (point.getX() >= minX && point.getX() <= maxX &&
            point.getY() >= minY && point.getY() <= maxY)
            return true;
        else
            return false;
    }
    
}
