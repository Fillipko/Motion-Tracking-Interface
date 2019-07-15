import javax.swing.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.AbstractButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {
    
    JFrame frame = new JFrame();
    JButton button = new JButton("Setup hand here");
    JLabel label = new JLabel();
   

    public Application() {
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        button.setSize(250, 50);
        button.setVisible(true);
        frame.getContentPane().add(button);
        label.setBounds(10,10,100,100);
        button.addActionListener(new ActionListener());
       
    }
    public static void main(String[] args) {
        new Application();
    }
}