import javax.swing.*;
import java.awt.event.*;
import java.awt.GridBagLayout;

public class Application implements ActionListener{    
    JFrame frame = new JFrame();
    JButton button = new JButton("Setup hand here");

    public Application() {
        //Frame Setup
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridBagLayout());
        //Button Setup
        frame.add(button);
        button.setVisible(true);
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