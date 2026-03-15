import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("N-Body Galaxy Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setLocationRelativeTo(null);

        SimulationPanel panel = new SimulationPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}