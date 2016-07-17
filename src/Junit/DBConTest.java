

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.hamcrest.*;
import org.junit.rules.ExpectedException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    String actualUSer = "TestUser";
    String actualPW = "Test12345";
    String actualMatrikel = "99999999";
    String actualifwKennung = "99999";
    String actualLastName = "Mustermann";
    String actualFirstName = "Max";
    String actualBirthday = "2000-01-01";
    String actualStreet = "Musterstr. 1";
    String actualEmail = "Muster@Mustermann.de";
    String actualImmaStatus = "1";
    String actualPWReset = "0";

    ResultSet rs = null;

    String testUSer = "";

    String testWrongPW = "wrongPW";
    String testPW = "Test12345";

/*
    //tests that SQL Exceptions are thrown
    @Test(expected = SQLServerException.class)
    public void testSQLExceptions() throws SQLException {

        //connection establishment
        DBCon SQLTest2 = new DBCon();
        SQLTest2.disconnect();


        SQLTest2.getData(actualUSer,1);


    }

*/


    //Test whether a User is available in the DB
    @Test
    public void testConnection() throws SQLException {
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();
        String actualUSer = SQLTest.getData("TestUser",1);
        String testUSer = "TestUser";

        Assertions.assertThat(testUSer).isEqualTo(actualUSer);

        SQLTest.disconnect();
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
        //comparing USer and PW

        Assertions.assertThat(testUSer).isEqualTo(actualUSer);
        Assertions.assertThat(testPW).isEqualTo(actualPW);

        SQLTest.disconnect();
    }

    //Test that a wrong PW does not grant access to an existing user
        @Test
    public void testUserWrongPW() throws SQLException {

        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();
        //creating user and wrong PW as Strings
        String DBDataUser = SQLTest.getData("TestUser",1);
        String DBDataPW = SQLTest.getData("TestUser",2);
        //comparing USer and PW
        Assertions.assertThat(actualUSer).isEqualTo(DBDataUser);
        Assertions.assertThat(testWrongPW).isNotEqualTo(DBDataPW);

            SQLTest.disconnect();
    }

    //Test return Username from DB
    @Test
    public void testRightUserName() throws SQLException {
        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();

        //test
        Assertions.assertThat(SQLTest.getData(actualUSer, 1)).isEqualTo(actualUSer);

        //close connection
        SQLTest.disconnect();
    }

    //Test return Username from DB is false with UserName that is not in DB
    @Test
    public void testWrongUserName() throws SQLException {
        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();
        testUSer = "fakeUser";
        Assertions.assertThat(SQLTest.getData(testUSer, 1)).isEqualTo(null);

        SQLTest.disconnect();
    }


    //test that the DB Data is correct if the user input is correct, otherwise it must be null
    @Test
    public void testPWReset() throws SQLException {
        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();

        //test that correct matrikelnummer is returned and false matrikelnummer returns null
        Assertions.assertThat(SQLTest.getData(actualMatrikel, 3)).isEqualTo(actualMatrikel);
        Assertions.assertThat(SQLTest.getData("00000", 3)).isEqualTo(null);

        //test that correct ifwKennung is returned and false ifw Kennung returns null
        Assertions.assertThat(SQLTest.getData(actualifwKennung, 4)).isEqualTo(actualifwKennung);
        Assertions.assertThat(SQLTest.getData("00000", 4)).isNotEqualTo(actualifwKennung);

        //test that correct nachname is returned and false nachname returns null
        Assertions.assertThat(SQLTest.getData(actualLastName, 5)).isEqualTo(actualLastName);
        Assertions.assertThat(SQLTest.getData("sadkasd", 5)).isEqualTo(null);

        //test that correct First Name is returned and false FirstName returns null
        Assertions.assertThat(SQLTest.getData(actualFirstName, 6)).isEqualTo(actualFirstName);
        Assertions.assertThat(SQLTest.getData("Maxi", 6)).isEqualTo(null);

        //test that correct geburtsdatum is returned and false geburtsdatum returns null
        Assertions.assertThat(SQLTest.getData(actualBirthday, 7)).isEqualTo(actualBirthday);
        Assertions.assertThat(SQLTest.getData("2016-06-06", 7)).isEqualTo(null);


        //test that correct straße is returned and false straße returns null
        Assertions.assertThat(SQLTest.getData(actualStreet, 8)).isEqualTo(actualStreet);
        Assertions.assertThat(SQLTest.getData("Eine Straße", 8)).isEqualTo(null);

        //test that correct email is returned and false email returns null
        Assertions.assertThat(SQLTest.getData(actualEmail, 9)).isEqualTo(actualEmail);
        Assertions.assertThat(SQLTest.getData("Eine Straße", 9)).isEqualTo(null);

        //test that correct immaStatus is returned and false Immastatus returns null
        Assertions.assertThat(SQLTest.getData(actualImmaStatus, 10)).isEqualTo(actualImmaStatus);
        Assertions.assertThat(SQLTest.getData("5", 10)).isEqualTo(null);

        //test that correct passwortReset is returned and false passwortReset returns null
        Assertions.assertThat(SQLTest.getData(actualPWReset, 11)).isEqualTo(actualPWReset);
        Assertions.assertThat(SQLTest.getData("2", 11)).isEqualTo(null);

        //test that null is returned if the option of Integer i is not in the getData()
        Assertions.assertThat(SQLTest.getData("2", 12)).isEqualTo(null);
        //close connection
        SQLTest.disconnect();
    }



    //test that the resultset returns the correct data
    @Test
    public void testResultSet() throws SQLException {
        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();
        rs = SQLTest.getRs(actualUSer, 1);
        //test

        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualUSer, 2);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualMatrikel, 3);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualifwKennung, 4);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualLastName, 5);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualFirstName, 6);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualBirthday, 7);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualStreet, 8);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualEmail, 9);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualImmaStatus, 10);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs(actualPWReset, 11);
        Assertions.assertThat(rs).isNotEqualTo(null);

        rs = SQLTest.getRs("", 12);
        Assertions.assertThat(rs).isNotEqualTo(null);


        rs = SQLTest.getRs(actualMatrikel, 13);
        Assertions.assertThat(rs).isEqualTo(null);

        //close connection
        SQLTest.disconnect();
    }

    //tests whether the status of a student can be checked with certainty
    @Test
    public void testCheckStatus() throws SQLException {
        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();

        //test that if the correct matrikelnummer (that is "matrikuliert") returns true

        Assertions.assertThat(SQLTest.checkStatus("", "99999999", 1)).isTrue();
        Assertions.assertThat(SQLTest.checkStatus("", "34234235", 1)).isFalse();

        Assertions.assertThat(SQLTest.checkStatus("0", "99999999", 2)).isTrue();
        Assertions.assertThat(SQLTest.checkStatus("2", "34234235", 2)).isFalse();

        //close connection
        SQLTest.disconnect();

    }


    //tests update function for username in registration
    @Test
    public void testExecuteSqlQueryUser() throws SQLException {
        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();

        //checks initial user name

        Assertions.assertThat(SQLTest.getData(actualUSer, 1)).isEqualTo(actualUSer);

        //test that if the username is updated, then it will be different than before

        //update
        SQLTest.executeSqlQuery("testuserNEW", "99999999", "99999", 1);

        Assertions.assertThat(SQLTest.getData("testuserNEW", 1)).isEqualTo("testuserNEW");

        //Reset to initial state and test that UserName of test user has benn reset to TestUser

        SQLTest.executeSqlQuery("TestUser", "99999999", "99999", 1);

        Assertions.assertThat(SQLTest.getData("TestUser", 1)).isEqualTo(actualUSer);


        //close connection
        SQLTest.disconnect();

    }


    //tests update function for password in registration
    @Test
    public void testExecuteSqlQueryPW() throws SQLException {
        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();

        //checks initial user name

        Assertions.assertThat(SQLTest.getData(actualUSer, 2)).isEqualTo(actualPW);

        //test that if the username is updated, then it will be different than before

        //update
        SQLTest.executeSqlQuery(testWrongPW, "99999999", "99999", 2);

        Assertions.assertThat(SQLTest.getData(actualUSer, 2)).isEqualTo(testWrongPW);

        //Reset to initial state and test that UserName of test user has benn reset to TestUser

        SQLTest.executeSqlQuery(actualPW, "99999999", "99999", 2);

        Assertions.assertThat(SQLTest.getData(actualUSer, 2)).isEqualTo(actualPW);


        //close connection
        SQLTest.disconnect();

    }

    //tests that the update method does not work if a wrong integer is given
    @Test
    public void testExecuteSqlQueryWrongInt() throws SQLException {
        //connection establishment
        DBCon SQLTest = new DBCon();
        SQLTest.connect_DB();

        //checks initial user name

        Assertions.assertThat(SQLTest.getData(actualUSer, 2)).isEqualTo(actualPW);

        //test that executeSQLQuery returns false if integer is >2

        Assertions.assertThat(SQLTest.executeSqlQuery("something", "something", "something", 3)).isFalse();



        //close connection
        SQLTest.disconnect();

    }




}
