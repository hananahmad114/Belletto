
import java.awt.event.*;
import java.awt.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iocoder
 */
public class Shapes {
    
    // screen elements
    private static JPanel buttonPane;
    private static JExtPanel drawingArea;
    private static JColorChooser colorSel;
    
    // the shape that is currently being drawn.
    private static String butName;
    private static Shape sample;
    
    // arrays:
    public static java.util.List<Shape> arr;
    public static java.util.List<Shape> selectedShapes;
    public static java.util.List<Shape> samples;
    
    private static void dMousePressed(MouseEvent evt) {
        
        for(Shape s : samples) {
            if (s.getClass().getName().equals(butName)) {
                // create new object:
                try {
                    sample = s.getClass().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    // TODO: some error.
                    System.out.println("Exception: " + e);
                }
                break;
            }
        }
        
        arr.add(sample);
        Pixel start = new Pixel(evt.getX(), evt.getY());
        Pixel end = new Pixel(evt.getX(), evt.getY());
        sample.setVars(start, end, colorSel.getColor());
        sample.paint(drawingArea.getGraphics());
        drawingArea.repaint();
    }
    
    private static void dMouseDragged(MouseEvent evt) {
        if (sample == null)
            return;
        Pixel end = new Pixel(evt.getX(), evt.getY());
        sample.setEnd(end);
        sample.paint(drawingArea.getGraphics());
        drawingArea.repaint();
    }

    private static void dMouseReleased(MouseEvent evt) {                                
         dMouseDragged(evt);
         sample = null;
    }
    
    private static void button_clicked(ActionEvent e) {
        
        butName = e.getActionCommand();
        
        for(MouseListener ml : drawingArea.getMouseListeners())
            drawingArea.removeMouseListener(ml);
        
        for(MouseMotionListener mml : drawingArea.getMouseMotionListeners())
            drawingArea.removeMouseMotionListener(mml);
        
        drawingArea.setCursor(new java.awt.Cursor(Cursor.CROSSHAIR_CURSOR));
        
        unselectAll();
        
        drawingArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                Shapes.dMousePressed(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt) {
                Shapes.dMouseReleased(evt);
            }
        });
        drawingArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                Shapes.dMouseDragged(evt);
            }
        });
    }
    
    public static void unselectAll() {
        while(selectedShapes.size() > 0) {
            selectedShapes.get(0).unselect();
            selectedShapes.remove(selectedShapes.get(0));
        }
        MainWindow.drawingArea.repaint();
    }
    
    // initialize workspace:
    public static void initStuff(JPanel buttonPane,
                                 JExtPanel drawingArea,
                                 JColorChooser colorSel) {

        arr = new ArrayList();
        selectedShapes = new ArrayList();
        samples = new ArrayList();
        
        Shapes.buttonPane = buttonPane;
        Shapes.drawingArea = drawingArea;
        Shapes.colorSel = colorSel;

    }
    
    // add button:
    public static void addButton(String butName) {
        JButton button = new JButton(butName);
        button.setIcon(new javax.swing.ImageIcon(
                Shapes.class.getClassLoader().getResource(butName+".png")));
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_clicked(e);
            }
        });
        buttonPane.add(button);
    }
    
}
