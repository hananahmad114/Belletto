
import java.awt.*;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iocoder
 */
public class JExtPanel extends JPanel {

    JExtPanel() {
        setBackground(Color.WHITE);
    }
   
    @Override
    public void paint(Graphics g) {
        if (g == null)
            return;
        super.paintComponent(g);
        for (Shape s : Shapes.arr) {
            s.paint(g);
        }
    }

}
