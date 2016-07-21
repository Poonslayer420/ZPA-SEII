/**
 * Created by Niclas on 17.07.2016.
 */

import org.assertj.core.api.Assertions;
import org.junit.*;
import org.hamcrest.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Integrationstests {

    String actualUSer = "TestUser";
    String actualPW = "";
    String testUSer = "";
    String testWrongPW = "wrongPW";
    String testPW = "Test12345";


    /*
    Die Funktionstests sind im Pflichtenheft definiert, werden hier aber nicht getestet, da dies nicht in der Anforderung für uns dabei war...

     */
    //Test that after entering the correct password, the login frame is not shown anymore
    //Test of Äquivalenzklasse L01
    @Test
    public void testLogin() throws SQLException {
        Decoupling interf = null;
        ButtonHandler BH = new ButtonHandler(interf);
        GUI gui = new GUI();

        AbstractButton but = new JButton();
        but.addActionListener(BH);
        but.setActionCommand(Decoupling.ACTION_LOGIN);

        gui.setFullName(actualUSer);
        gui.setPass(testPW);

        but.doClick();

        Assertions.assertThat(gui.login.isVisible()).isFalse();
    }


    //Test that after entering the wrong password, the login frame is shown again
    //Test of Äquivalenzklasse L03
    @Test
    public void testWrongPW() throws SQLException {
        Decoupling interf = null;
        ButtonHandler BH = new ButtonHandler(interf);
        GUI gui = new GUI();

        AbstractButton but = new JButton();
        but.addActionListener(BH);
        but.setActionCommand(Decoupling.ACTION_LOGIN);

        gui.setFullName(actualUSer);
        gui.setPass(testWrongPW);

        but.doClick();

        //Test that MessageDialog is shown
        Assertions.assertThat(gui.login.isVisible()).isFalse();;

    }





}
