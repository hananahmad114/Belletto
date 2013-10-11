
import java.awt.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iocoder
 */
public class Triangle extends Shape {
    // instance variables:
    protected Pixel center;
    protected Pixel baseStart;
    protected Pixel baseEnd;
    protected Pixel height;
    
    // setVars
    @Override
    public void setVars(Pixel start, Pixel end, Color color) {
        super.color = color;
        super.start = start;
        super.end = end;
        center = new Pixel((start.getX()+end.getX())/2,
                           (start.getY()+end.getY())/2);
        baseStart = new Pixel(start.getX(), start.getY());
        baseEnd = new Pixel(end.getX(), baseStart.getY());
	height = new Pixel((baseStart.getX()+baseEnd.getX())/2, end.getY());
    }
    
    // convert into polygon:
    private Polygon toPolygon() {
        int[] x = new int[3];
        int[] y = new int[3];
        x[0] = baseStart.getX();
        x[1] = baseEnd.getX();
        x[2] = height.getX();
        y[0] = baseStart.getY();
        y[1] = baseEnd.getY();
        y[2] = height.getY();
        return new Polygon(x, y, 3);
    }
    
    // paint:
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillPolygon(toPolygon());
        if (selected) {
            g2.setColor(new Color(0xFF ^ color.getRed(),
                                 0xFF ^ color.getGreen(),
                                 0xFF ^ color.getBlue()));
            g2.setStroke(new BasicStroke(3));
            g2.drawPolygon(toPolygon());
        }
        
    }

    @Override
    public boolean contains(Pixel point) {
        // make use of Polygon class:
        return toPolygon().contains(new Point(point.getX(), point.getY()));
    }
    
}
