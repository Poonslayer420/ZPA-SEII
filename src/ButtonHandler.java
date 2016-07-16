import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ButtonHandler implements ActionListener {

    Decoupling gui;

    public ButtonHandler(Decoupling gui) {
        // 1. create reference to GUI
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals(Decoupling.ACTION_LOGIN)) {
                String puname = this.gui.getFullName();
                String ppaswd = this.gui.getPassword();

                DBCon ZPA = new DBCon();
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
            } else if (e.getActionCommand().equals(Decoupling.ACTION_REGISTRIEREN)) {
                gui.setRegistration();
            } else if (e.getActionCommand().equals(Decoupling.ACTION_BACK)) {
                gui.setLogin();
            } else if (e.getActionCommand().equals(Decoupling.ACTION_UPDATE)) {

                String smatrikel = this.gui.getMatrikel();
                String sifw = this.gui.getIfw();
                String sbenutzer = this.gui.getUser();
                String svorname = this.gui.getVorname();
                String snachname = this.gui.getNachname();
                String sstraße = this.gui.getStraße();
                String sgeburtstag = this.gui.getBirthday();
                String spasswort = this.gui.getPassword();
                String spasswortReRun = this.gui.getPassword2();

                ResultSet rsStudent = null;

                if (smatrikel.equals("") && smatrikel.length() != 8) {
                    JOptionPane.showMessageDialog(null, "Bitte Matrikelnummer füllen!");
                    return;
                } else {
                    if (sifw.equals((""))) {
                        JOptionPane.showMessageDialog(null, "Bitte IFW-KEnnung füllen!");
                        return;
                    } else {
                        if (sbenutzer.equals("")) {
                            JOptionPane.showMessageDialog(null, "Bitte Benutzernamen füllen!");
                            return;
                        } else {
                            if (svorname.equals((""))) {
                                JOptionPane.showMessageDialog(null, "Bitte Vorname füllen!");
                                return;
                            } else {
                                if (snachname.equals("")) {
                                    JOptionPane.showMessageDialog(null, "Bitte Nachname füllen!");
                                    return;
                                } else {
                                    if (sstraße.equals((""))) {
                                        JOptionPane.showMessageDialog(null, "Bitte Straße füllen!");
                                        return;
                                    } else {
                                        if (sgeburtstag.equals("")) {
                                            JOptionPane.showMessageDialog(null, "Bitte Geburtstag füllen!");
                                            return;
                                        } else {
                                            if (spasswort.equals((""))) {
                                                JOptionPane.showMessageDialog(null, "Bitte Passwordfeld1 füllen!");
                                                return;
                                            } else {
                                                if (spasswortReRun.equals("")) {
                                                    JOptionPane.showMessageDialog(null, "Bitte Passwortfeld2 füllen!");
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                DBCon ZPA = new DBCon();
                ZPA.connect_DB();

                String str = "";

                if (!ZPA.checkStatus(str, smatrikel, 1)) {
                    JOptionPane.showMessageDialog(null, "Student nicht immatrikuliert!");
                }

                rsStudent = ZPA.getRs(smatrikel, 3);

                while (rsStudent.next()) {
                    if (rsStudent.getString(3).equals(smatrikel) && rsStudent.getString(4).equals(sifw) && rsStudent.getString(5).equals(snachname)) {

                        ZPA.executeSqlQuery(sbenutzer, smatrikel, sifw, 1);
                        ZPA.executeSqlQuery(spasswort, smatrikel, sifw, 2);
                        JOptionPane.showMessageDialog(null, "Registrierung erfolgreich");

                        gui.setLogin();

                    }else {
                        JOptionPane.showMessageDialog(null, "Fehler bei der Registrierung! Bitte versuchen es später noch einmal");
                    }
                }

            }

        } catch (
                NullPointerException ex
                )

        {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        } catch (
                Exception ex
                )

        {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
    }
}
