import javax.swing.JFrame;
import javax.swing.JLabel;
public class App {
    public static void main(String[] args) throws Exception {
        Canvas canvas = new Canvas();
        JFrame frame = new JFrame("hello");
        frame.setSize(400, 300); 
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel text = new JLabel("Hello, World!", JLabel.CENTER);
        frame.add(text);
        frame.setVisible(true); 

    }
}
