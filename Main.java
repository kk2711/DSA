import javax.swing.JFrame;

public class Main{

    public static void main(String[] args){
        JFrame  window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new WindowPanel());

        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);
    }
}