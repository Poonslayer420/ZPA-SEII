import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GUI implements Decoupling {

    public JFrame login, registrieren, LoggedIn;
    public Container contentpane1;
    public JPanel contentpane2, contentpane3;
    public JButton blogin, babbrechen, breg, bregister;
    public JTextField txuser, txmatrikel, txifw, txbenutzer, txvorname, txnachname, txstraße, txgeburtsdatum;
    public JLabel txuserLabel, passLabel, welcome;
    public ButtonHandler control;
    public JTableview tableview;
    public JPasswordField pass, pass2, pass3;
    JScrollPane scrollPane;


    public static void main(String[] args) throws SQLException {
        GUI guiApp;
        guiApp = new GUI();
        guiApp.launchFirstJFrame();
    }

    public String getFullName() {
        String name = txuser.getText();
        return name;
    }

    public String getMatrikel() {
        String matrikel = txmatrikel.getText();
        return matrikel;
    }

    public String getIfw() {
        String ifw = txifw.getText();
        return ifw;
    }

    public String getUser() {
        String user = txbenutzer.getText();
        return user;
    }

    public String getVorname() {
        String name = txvorname.getText();
        return name;
    }

    public String getNachname() {
        String name = txnachname.getText();
        return name;
    }

    public String getStraße() {
        String street = txstraße.getText();
        return street;
    }

    public String getBirthday() {
        String bday = txgeburtsdatum.getText();
        return bday;
    }


    public String getPassword() {
        String pw = String.valueOf(pass.getPassword()); //JPassword.getText is decprecated so we used this way. :-)
        return pw;
    }

    public String getPassword2() {
        String pw = String.valueOf(pass2.getPassword()); //JPassword.getText is decprecated so we used this way. :-)
        return pw;
    }

    public String getPassword3() {
        String pw = String.valueOf(pass3.getPassword()); //JPassword.getText is decprecated so we used this way. :-)
        return pw;
    }


    public GUI() {

        login = new JFrame("GUI1: Login");
        registrieren = new JFrame("GUI2: Registrieren");
        LoggedIn = new JFrame("GUI3: WELCOME");

        contentpane1 = new Container();
        contentpane2 = new JPanel();
        contentpane3 = new JPanel();

        // 1. ActionListener "control" is created
        //  GUI object is assigned to "control.gui"
        control = new ButtonHandler(this);
        // determine content pane to frame
        login.setContentPane(contentpane1);
        contentpane1.setLayout(null);
        registrieren.setContentPane(contentpane2);
        contentpane2.setLayout(null);
        contentpane3.setLayout(null);
        LoggedIn.setContentPane(contentpane3);


        blogin = new JButton("Login");
        bregister = new JButton("Registrieren");
        breg = new JButton("REGISTRIEREN!");
        txuser = new JTextField(15);
        txuserLabel = new JLabel("Benutzer:");
        pass = new JPasswordField(15);
        passLabel = new JLabel("Passwort:");
        welcome = new JLabel("Herzlich willlkommen im ZPA:");

        babbrechen = new JButton("ABBRECHEN");


        // 2. add interface constants as action command

        bregister.setActionCommand(ACTION_REGISTRIEREN);
        blogin.setActionCommand(ACTION_LOGIN);
        babbrechen.setActionCommand(ACTION_BACK);
        breg.setActionCommand(ACTION_UPDATE);


        // 3. ActionListener "control" is assigned to the Buttons of the GUI object

        bregister.addActionListener(control);
        blogin.addActionListener(control);
        babbrechen.addActionListener(control);
        breg.addActionListener(control);


    }

    public void launchFirstJFrame() throws SQLException {
        // set the bounds of the components and add to content pane
        txuser.setBounds(140, 30, 150, 20);
        txuserLabel.setBounds(20, 30, 150, 20);
        pass.setBounds(140, 65, 150, 20);
        passLabel.setBounds(20, 65, 150, 20);
        blogin.setBounds(210, 100, 80, 20);
        bregister.setBounds(20, 100, 120, 20);

        contentpane1.add(txuser);
        contentpane1.add(txuserLabel);
        contentpane1.add(pass);
        contentpane1.add(passLabel);
        contentpane1.add(blogin);
        contentpane1.add(bregister);

        login.setSize(420, 200);
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);
    }


    public void launchSecondJFrame() {
        // set the bounds of the components and add to content pane

        JLabel jlmatrikel = new JLabel("Deine Matrikelnummer:");
        JLabel jlifw = new JLabel("Deine IFW-Kennung:");
        JLabel jlbenutzer = new JLabel("Bitte wähle deinen Benutzernamen:");
        JLabel jlvorname = new JLabel("Vorname:");
        JLabel jlnachname = new JLabel("Nachname:");
        JLabel jlstraße = new JLabel("Straße:");
        JLabel jlgeburtsdatum = new JLabel("Geburtsdatum:");
        JLabel jlpasswort = new JLabel("Passwort:");
        JLabel jlpasswortReRun = new JLabel("Passwort:");

        txmatrikel = new JTextField(8);
        txifw = new JTextField(5);
        txbenutzer = new JTextField(30);
        txvorname = new JTextField(30);
        txnachname = new JTextField(30);
        txstraße = new JTextField(30);
        txgeburtsdatum = new JTextField(10);
        pass = new JPasswordField(30);
        pass2 = new JPasswordField(30);

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
        pass.setBounds(240, 400, 150, 20);
        jlpasswort.setBounds(20, 400, 120, 20);
        pass2.setBounds(240, 450, 150, 20);
        jlpasswortReRun.setBounds(20, 450, 120, 20);

        breg.setBounds(20, 500, 150, 20);
        babbrechen.setBounds(240, 500, 150, 20);

        contentpane2.add(txmatrikel);
        contentpane2.add(jlmatrikel);
        contentpane2.add(txifw);
        contentpane2.add(jlifw);
        contentpane2.add(txbenutzer);
        contentpane2.add(jlbenutzer);
        contentpane2.add(txvorname);
        contentpane2.add(jlvorname);
        contentpane2.add(txnachname);
        contentpane2.add(jlnachname);
        contentpane2.add(txstraße);
        contentpane2.add(jlstraße);
        contentpane2.add(txgeburtsdatum);
        contentpane2.add(jlgeburtsdatum);
        contentpane2.add(pass);
        contentpane2.add(jlpasswort);
        contentpane2.add(pass2);
        contentpane2.add(jlpasswortReRun);

        contentpane2.add(breg);
        contentpane2.add(babbrechen);

        registrieren.setSize(450, 600);
        registrieren.setLocationRelativeTo(null);
        registrieren.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrieren.setVisible(true);
    }

    public void launchLoggedIn() {
        // set the bounds of the components and add to content pane

        LoggedIn.setLocationRelativeTo(null);

        welcome.setBounds(70, 150, 150, 60);
        babbrechen.setBounds(70, 200, 150, 60);

        contentpane3.add(welcome);
        contentpane3.add(babbrechen);

        LoggedIn.setSize(600, 400);
        LoggedIn.setLocationRelativeTo(null);
        LoggedIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoggedIn.setVisible(true);
    }

    public void setLoggedIn() {
        // hide first (current) frame
        this.login.setVisible(false);
        this.launchLoggedIn();
    }

    public void setRegistration() {
        // hide first (current) frame
        this.login.setVisible(false);
        this.launchSecondJFrame();
    }

    public void setLogin() {
        // 5. update JTable content to gain reliability instead of performance
        // tableview.updateSQLTable("", 12);
        this.login.setVisible(true);
        this.LoggedIn.setVisible(false);
        // deallocate frame registrieren
        this.registrieren.dispose();
    }


}
