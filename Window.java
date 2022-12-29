import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/*
 *  defines a window which will hold a component defined by GUI
 */
public class Window extends Canvas {
    
    public Window(int w, int h, GUI g){
        JFrame frame = new JFrame("Sorting Algorithms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension d = new Dimension(w,h);
        frame.setPreferredSize(d);
        frame.setMaximumSize(d);
        frame.setMinimumSize(d);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(g);
        frame.setVisible(true);
    }
}
