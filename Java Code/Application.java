import javax.swing.JFrame;

public class Application {
    
    JFrame frame = new JFrame();
    
    public Application() {
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Application();
    }
}