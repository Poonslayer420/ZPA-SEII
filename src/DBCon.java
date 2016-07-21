import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DBCon {

    // implement singleton pattern with static elements

    private static Connection cn = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private String sDbDriver = null, sDbUrl = null, sUsr = "", sPwd = "";

    {
        sDbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        sDbUrl = "jdbc:sqlserver://192.168.7.21:1433;" + "databaseName=ZPA;";
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
            while (rs.next()) { //while is a must for creating the Index of the ResultSet.
                returnValue = rs.getString(i);
            }
        } catch (SQLServerException se) {
            se.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public Boolean executeSqlQuery(String str, String matrikel, String ifw, Integer i) {
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

    public boolean checkStatus(String given, String mkr, Integer i) {
        String SQL = null;
        Boolean booli = false;
        try {
            if (i == 1) {
                SQL = "SELECT * FROM studierende WHERE matrikelnummer=" + mkr + " AND immatrikulationsstatus=" + 1; // Returns Imma Status 1 = In | 0 = Out
            } else if (i == 2) {
                SQL = "SELECT * FROM studierende WHERE matrikelnummer=" + mkr + " AND passwortreset=" + "'" + given + "'"; //Returns PasswortReset counter
            }
            st = cn.createStatement();
            rs = st.executeQuery(SQL);

            while (rs.next()) {
                booli = rs.getInt(10) == 1;
            }

            /*
            if (rs.getString(10).equals("1") && i == 1) {
                return true;
            } else if (rs.getInt(11) < 10 && i == 2) {
                return true;
            }
            */

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booli;

    }

    public boolean checkForAdminStatus(String str) throws SQLException {
        String SQL = null;
        Boolean booli = false;

        SQL = "SELECT * FROM studierende WHERE benutzer=" + "'" + str + "'"; // Returns Username

        st = cn.createStatement();
        rs = st.executeQuery(SQL);

        while (rs.next()) {
            booli = rs.getInt(10) == 2;
        }

        return booli;

    }




    public ResultSet getRs(String str, Integer i) {
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
            } else if (i == 12) {
                SQL = "SELECT * FROM studierende";
            } else {
                return null;
            }
            // Execute an SQL statement that returns data.
            st = cn.createStatement();
            rs = st.executeQuery(SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // save closing SQL query
    public void disconnect() throws SQLException {
        if (st != null) st.close();
        if (rs != null) rs.close();
        if (cn != null) cn.close();
    }

}




