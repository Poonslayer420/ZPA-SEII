import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ButtonHandler implements ActionListener {

    Decoupling gui;

    public ButtonHandler(Decoupling gui)
    {
        // 1. create reference to GUI
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent e)
    {
        try {
            if (e.getActionCommand().equals(Decoupling.ACTION_LOGIN))
            {
                String puname = this.gui.getFullName();
                String ppaswd = this.gui.getPassword(); //JPassword.getText is decprecated so we used this way. :-)

                DBCon ZPA = new DBCon();
                ZPA.connect_DB();

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
                                gui.setLoggedIn();

                            } else {
                                JOptionPane.showMessageDialog(null, "Falsches Password und/oder Benutzername ist falsch!");

                            }
                        }
                    }
                }
            }
            else if (e.getActionCommand().equals(Decoupling.ACTION_REGISTRIEREN))
            {
                gui.setRegistration();
            }
            else if (e.getActionCommand().equals(Decoupling.ACTION_BACK))
            {
                gui.setLogin();
            }
            else if (e.getActionCommand().equals(Decoupling.ACTION_UPDATE))
            {/*
                String smatrikel = txmatrikel.getText();
                String sifw = txifw.getText();
                String sbenutzer = txbenutzer.getText();
                String svorname = txvorname.getText();
                String snachname = txnachname.getText();
                String sstraße = txstraße.getText();
                String sgeburtstag = txgeburtsdatum.getText();
                String spasswort = txpasswort.getText();
                String spasswortReRun = txpasswortReRun.getText();
            */}
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),ex.getMessage());
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
    }
}
