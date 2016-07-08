

import org.assertj.core.api.Assertions;
import org.junit.*;
import org.hamcrest.*;

import java.sql.SQLException;


public class DBConTest {




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
        String actualUSer = SQLTest.getData("TestUser",1);
        String actualPW = SQLTest.getData("TestUser",2);
        String testUSer = "TestUser";
        String testPW = "test12345";
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
        String testUSer = "TestUser";
        String testWrongPW = "wrongPW";
        //comparing USer and PW

        Assertions.assertThat(testUSer).isEqualTo(actualUSer);
        Assertions.assertThat(testWrongPW).isNotEqualTo(actualPW);
    }

}
