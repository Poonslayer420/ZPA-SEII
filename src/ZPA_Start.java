
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ZPA_Start extends JFrame {
    DBCon ZPA = null;

    public static void main(String[] args) throws SQLException {
        ZPA_Start frameTabel = new ZPA_Start();
    }

    JButton blogin = new JButton("Login");
    JButton bregister = new JButton("Registrieren");
    JPanel panel = new JPanel();
    JTextField txuser = new JTextField(15);
    JLabel txuserLabel = new JLabel("Benutzer:");
    JPasswordField pass = new JPasswordField(15);
    JLabel passLabel = new JLabel("Passwort:");

    ZPA_Start() throws SQLException {

        super("ZPA Anmeldung");
        setSize(420, 200);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        txuser.setBounds(140, 30, 150, 20);
        txuserLabel.setBounds(20, 30, 150, 20);
        pass.setBounds(140, 65, 150, 20);
        passLabel.setBounds(20, 65, 150, 20);

        blogin.setBounds(210, 100, 80, 20);
        bregister.setBounds(20, 100, 120, 20);

        panel.add(blogin);
        panel.add(bregister);
        panel.add(txuser);
        panel.add(txuserLabel);
        panel.add(pass);
        panel.add(passLabel);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        ZPA = new DBCon();

        actionlogin();
        actionregister();
    }

    public void actionlogin() throws SQLException {
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String puname = txuser.getText();
                String ppaswd = String.valueOf(pass.getPassword()); //JPassword.getText is decprecated so we used this way. :-)

                ZPA.connect_DB();

                if (puname.equals("")) {
                    JOptionPane.showMessageDialog(null, "Bitte Benutzerfeld füllen!");
                } else {
                    if (ppaswd.equals((""))) {
                        JOptionPane.showMessageDialog(null, "Bitte Passwordfeld füllen!");
                    } else {
                        if (ZPA.getData(puname, 1) == null) {
                            JOptionPane.showMessageDialog(null, "Dein eingegebener Benutzername existiert nicht!");
                        } else {
                            if (ZPA.getData(puname, 1).equals(puname) && ZPA.getData(puname, 2).equals(ppaswd)) {
                                LoggedIn loggedInFace = new LoggedIn();
                                loggedInFace.setVisible(true);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Falsches Password und/oder Benutzername ist falsch!");
                                txuser.setText("");
                                pass.setText("");
                                txuser.requestFocus();
                            }
                        }
                    }
                }
            }
        });
    }

    public void actionregister() throws SQLException {
        bregister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Registrieren regFace = null;
                try {
                    regFace = new Registrieren();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                regFace.setVisible(true);
                dispose();
            }
        });
    }

}




