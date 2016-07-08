import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DBCon {

    // implement singleton pattern with static elements
    private static dbConnect instance;
    private static Connection cn = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private String sDbDriver = null, sDbUrl = null, sUsr = "", sPwd = "";

    {
        sDbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        sDbUrl = "jdbc:sqlserver://nanoloop.ddns.net:1433;" + "databaseName=ZPA;";
        sUsr = "sql-member";
        sPwd = "Swordfish2016";
    }

    // default constructor:
    public DBCon() throws SQLException {
    }

    // connect to database
    public void connect_DB() {
        try {    // select fitting database driver and connect
            Class.forName(sDbDriver);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
        try {
            cn = DriverManager.getConnection(sDbUrl, sUsr, sPwd);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
    }

    public String getData(String str, Integer i) {
        String returnValue = null;
        String SQL = null;
        try {
            // Create an SQL statement that returns data.
            if (i == 1) {
                SQL = "SELECT * FROM studierende WHERE benutzer=" + "'" + str + "'"; // Returns Username
            } else if (i == 2) {
                SQL = "SELECT * FROM studierende WHERE benutzer=" + "'" + str + "'" + "OR passwort=" + "'" + str + "'"; //Returns PW
            } else if (i == 3) {
                SQL = "SELECT * FROM studierende WHERE matrikelnummer=" + str;
            } else if (i == 4) {
                SQL = "SELECT * FROM studierende WHERE ifwkennnung=" + str;
            } else if (i == 5) {
                SQL = "SELECT * FROM studierende WHERE nachname=" + "'" + str + "'";
            } else if (i == 6) {
                SQL = "SELECT * FROM studierende WHERE vorname=" + "'" + str + "'";
            } else if (i == 7) {
                SQL = "SELECT * FROM studierende WHERE geburtsdatum=" + "'" + str + "'";
            } else if (i == 8) {
                SQL = "SELECT * FROM studierende WHERE straße=" + "'" + str + "'";
            } else if (i == 9) {
                SQL = "SELECT * FROM studierende WHERE email=" + "'" + str + "'";
            } else if (i == 10) {
                SQL = "SELECT * FROM studierende WHERE immatrikulationsstatus=" + str;
            } else if (i == 11) {
                SQL = "SELECT * FROM studierende WHERE passwortreset=" + str;
            } else {
                return null;
            }
            // Execute an SQL statement that returns data.
            st = cn.createStatement();
            rs = st.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                returnValue = rs.getString(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public Boolean executeSqlQuery(String str, Integer matrikel, Integer ifw, Integer i) {
        String SQL = null;
        try {
            // Create an SQL statement that updates data.
            if (i == 1) {
                SQL = "Update Studierende SET Benutzer =" + "'" + str + "'" + "where MatrikelNummer = " + matrikel + "AND ifwKennnung =" + ifw + "";  // Sets Username
            } else if (i == 2) {
                SQL = "Update Studierende SET Passwort =" + "'" + str + "'" + "where MatrikelNummer = " + matrikel + "AND ifwKennnung =" + ifw + "";  // Sets Password
            } else {
                return false;
            }
            // Execute an SQL statement that returns data.
            st = cn.createStatement();
            st.execute(SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // save closing SQL query
    public void disconnect() throws SQLException {
        if (st != null) st.close();
        if (rs != null) rs.close();
    }


    // if transaction concept 2 Phase Commit is applied
    public void commit() throws SQLException {
        cn.commit();
        disconnect();
    }

    // if transaction concept 2 Phase Commit is applied
    public void rollback() throws SQLException {
        cn.rollback();
        disconnect();
    }

}




