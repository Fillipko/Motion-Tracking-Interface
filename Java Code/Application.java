import javax.swing.*;
import java.awt.event.*;
import java.awt.GridBagLayout;
import com.github.sarxos.webcam.Webcam;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

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
        button.setSize(250, 50);
        frame.add(button);
        button.setVisible(true);
        button.addActionListener(this);
    }
    public static void main(String[] args) {
        new Application();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    	Webcam webcam = Webcam.getDefault();
    	webcam.open();
    	ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));

    }
}
