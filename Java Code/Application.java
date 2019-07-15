import javax.swing.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.AbstractButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application implements ActionListener{
    
    JFrame frame = new JFrame();
    JButton button = new JButton("Setup hand here");

    public Application() {
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        button.setSize(250, 50);
        button.setVisible(true);
        frame.getContentPane().add(button);
        button.addActionListener(this);
    }
    public static void main(String[] args) {
        new Application();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        button.setVisible(false);
    }
}