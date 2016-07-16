

import org.assertj.core.api.Assertions;
import org.junit.*;
import org.hamcrest.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;


public class DBConTest {

    String actualUSer = "TestUser";
    String actualPW = "";
    String testUSer = "";
    String testWrongPW = "wrongPW";
    String testPW = "Test12345";



    //Test whether a User is available in the DB
    @Test
    public void testConnection() throws SQLException {
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();
        String actualUSer = SQLTest.getData("TestUser",1);
        String testUSer = "TestUser";

        Assertions.assertThat(testUSer).isEqualTo(actualUSer);

    }

    //Test whether a Password matches the Username
    @Test
    public void testUserPW() throws SQLException {
        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();
        //creating user and PW as Strings
        actualUSer = SQLTest.getData("TestUser",1);
        actualPW = SQLTest.getData("TestUser",2);
        testUSer = "TestUser";
        testPW = "test12345";
        //comparing USer and PW

        Assertions.assertThat(testUSer).isEqualTo(actualUSer);
        Assertions.assertThat(testPW).isEqualTo(actualPW);
    }

    //Test that a wrong PW does not grant access to an existing user
        @Test
    public void testUserWrongPW() throws SQLException {

        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();
        //creating user and wrong PW as Strings
        String actualUSer = SQLTest.getData("TestUser",1);
        String actualPW = SQLTest.getData("TestUser",2);
        //comparing USer and PW
        Assertions.assertThat(testUSer).isEqualTo(actualUSer);
        Assertions.assertThat(testWrongPW).isNotEqualTo(actualPW);
    }

    //Test that a wrong PW does not grant access to an existing user
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

}
