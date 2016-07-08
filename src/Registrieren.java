
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Registrieren extends JFrame {
    DBCon ZPA = null;

    public static void main(String[] args) throws SQLException {
        Registrieren frameTabel = new Registrieren();
    }

    JButton breg = new JButton("Registrieren");
    JButton babbrechen = new JButton("Abbrechen");
    JPanel panel = new JPanel();

    JTextField txmatrikel = new JTextField();
    JTextField txifw = new JTextField();
    JTextField txbenutzer = new JTextField();
    JTextField txvorname = new JTextField();
    JTextField txnachname = new JTextField();
    JTextField txstraße = new JTextField();
    JTextField txgeburtsdatum = new JTextField();
    JTextField txpasswort = new JTextField();
    JTextField txpasswortReRun = new JTextField();

    JLabel jlmatrikel = new JLabel("Deine Matrikelnummer:");
    JLabel jlifw = new JLabel("Deine IFW-Kennung:");
    JLabel jlbenutzer = new JLabel("Bitte wähle deinen Benutzernamen:");
    JLabel jlvorname = new JLabel("Vorname:");
    JLabel jlnachname = new JLabel("Nachname:");
    JLabel jlstraße = new JLabel("Straße:");
    JLabel jlgeburtsdatum = new JLabel("Geburtsdatum:");
    JLabel jlpasswort = new JLabel("Passwort:");
    JLabel jlpasswortReRun = new JLabel("Passwort:");

    Registrieren() throws SQLException {

        super("ZPA Registrierung");
        setSize(450, 600);
        setLocationRelativeTo(null);
        panel.setLayout(null);

        txmatrikel.setBounds(240, 50, 150, 20);
        jlmatrikel.setBounds(20, 50, 150, 20);
        txifw.setBounds(240, 100, 150, 20);
        jlifw.setBounds(20, 100, 200, 20);
        txbenutzer.setBounds(240, 150, 150, 20);
        jlbenutzer.setBounds(20, 150, 200, 20);
        txvorname.setBounds(240, 200, 150, 20);
        jlvorname.setBounds(20, 200, 120, 20);
        txnachname.setBounds(240, 250, 150, 20);
        jlnachname.setBounds(20, 250, 120, 20);
        txstraße.setBounds(240, 300, 150, 20);
        jlstraße.setBounds(20, 300, 120, 20);
        txgeburtsdatum.setBounds(240, 350, 150, 20);
        jlgeburtsdatum.setBounds(20, 350, 120, 20);
        txpasswort.setBounds(240, 400, 150, 20);
        jlpasswort.setBounds(20, 400, 120, 20);
        txpasswortReRun.setBounds(240, 450, 150, 20);
        jlpasswortReRun.setBounds(20, 450, 120, 20);

        breg.setBounds(20, 500, 150, 20);
        babbrechen.setBounds(240, 500, 150, 20);

        panel.add(txmatrikel);
        panel.add(jlmatrikel);
        panel.add(txifw);
        panel.add(jlifw);
        panel.add(txbenutzer);
        panel.add(jlbenutzer);
        panel.add(txvorname);
        panel.add(jlvorname);
        panel.add(txnachname);
        panel.add(jlnachname);
        panel.add(txstraße);
        panel.add(jlstraße);
        panel.add(txgeburtsdatum);
        panel.add(jlgeburtsdatum);
        panel.add(txpasswort);
        panel.add(jlpasswort);
        panel.add(txpasswortReRun);
        panel.add(jlpasswortReRun);

        panel.add(breg);
        panel.add(babbrechen);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        ZPA = new DBCon();

        actionlogin();
    }

    public void actionlogin() throws SQLException {
        breg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                String smatrikel = txmatrikel.getText();
                String sifw = txifw.getText();
                String sbenutzer = txbenutzer.getText();
                String svorname = txvorname.getText();
                String snachname = txnachname.getText();
                String sstraße = txstraße.getText();
                String sgeburtstag = txgeburtsdatum.getText();
                String spasswort = txpasswort.getText();
                String spasswortReRun = txpasswortReRun.getText();


            }
        });
    }

}




