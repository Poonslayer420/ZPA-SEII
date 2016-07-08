import javax.swing.*;
import java.awt.*;


public class LoggedIn extends JFrame {

    public static void main(String[] args) {
        LoggedIn frameTabel = new LoggedIn();
    }

    JLabel welcome = new JLabel("Willkommen im ZPA");
    JPanel panel = new JPanel();

    LoggedIn(){
        super("Willkommen");
        setSize(600,400);
        setLocationRelativeTo(null);
        panel.setLayout (null);

        welcome.setBounds(70,50,150,60);

        panel.add(welcome,BorderLayout.CENTER);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}