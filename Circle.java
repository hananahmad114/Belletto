/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iocoder
 */

import java.awt.*;

public class Circle extends Ellipse {
    
    // setVars
    @Override

    public void setVars(Pixel start, Pixel end, Color color) {

        int side1 = Math.abs(end.getX()-start.getX());
        int side2 = Math.abs(end.getY()-start.getY());
        int side = side1 > side2 ? side2 : side1;
        int x_sign = 1, y_sign = 1;
        
        if (end.getX() < start.getX())
            x_sign = -1;
        
        if (end.getY() < start.getY())
            y_sign = -1;
        
        end = new Pixel(start.getX()+x_sign*side,start.getY()+y_sign*side);
        super.setVars(start, end, color);
    }
    
}
