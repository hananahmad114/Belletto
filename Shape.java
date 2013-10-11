
import java.awt.*;

/**
 * Class Shape
 */
abstract public class Shape {

    // instance variables:
    protected Pixel start;
    protected Pixel end;
    protected Color color;
    protected boolean selected;

    Shape() {
        start = null;
        end = null;
        color = null;
        selected = false;
    }
    
    public void setStart(Pixel start) {
        setVars(start, end, color);
    }
    
    public void setEnd(Pixel end) {
        setVars(start, end, color);
    }
    
    public void setColor(Color color) {
        setVars(start, end, color);
    }
    
    public void select() {
        selected = true;
    }

    public void unselect() {
        selected = false;
    }
    
    abstract protected void setVars(Pixel start, Pixel end, Color color);
    abstract protected void paint(Graphics g);
    abstract protected boolean contains(Pixel point);

}
