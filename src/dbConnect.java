import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class dbConnect {

    // implement singleton pattern with static elements
    private static dbConnect instance;
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

    // default constructor: Establishes Connection if instance is called!
    public dbConnect() throws SQLException {
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

    // create or return a database connection(singleton pattern)
    public static dbConnect getDbConnection() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new dbConnect();
            instance.connect_DB();
        }
        return instance;
    }

    public void getStudents() {
        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(sDbUrl, sUsr, sPwd);

            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT * FROM student WHERE name='Tobias'";
            st = cn.createStatement();
            rs = st.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                //System.out.println(rs.getString(1) + " " + rs.getString(2));
                System.out.println(rs.getString(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAllStudents() {
        return "SELECT * FROM student";
    }

    // connect and execute input query
    public boolean executequery(String SQLquery) throws SQLException {    // insert resp. delete entry
        if (cn == null) throw new SQLException("Keine gültige Verbindung zur Datenbank");
        if (SQLquery == null) throw new SQLException("Ungültiger SQL-Befehl");
        Statement st = cn.createStatement();
        st.execute(SQLquery);
        return true;
    }

    // connect and execute input query
    public ResultSet executequery_rs(String SQLquery) throws SQLException, ClassNotFoundException {
        // select entries
        if (cn == null) throw new SQLException("Keine gültige Verbindung zur Datenbank");
        if (SQLquery == null) throw new SQLException("Ungültiger SQL-Befehl");
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(SQLquery);
        return rs;
    }

    // save closing SQL query
    public void disconnect() throws SQLException {
        if (st != null) st.close();
        if (rs != null) rs.close();
    }

    // if transaction concept 2 Phase Commit is applied
    private void connect_DB_2PC() {
        try {
            // select fitting database driver and connect
            Class.forName(sDbDriver);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
        try {
            cn = DriverManager.getConnection(sDbUrl, sUsr, sPwd);
            cn.setAutoCommit(false);
            cn.setTransactionIsolation(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
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

    // help method for debugging: read buffered entries from a table
    void readData(String s, Statement st, String dbTbl) throws SQLException {
        System.out.println(s);
        ResultSet rs = st.executeQuery("SELECT * FROM " + dbTbl);
        if (rs != null)
            while (rs.next())
                System.out.println("  " + dbTbl + "  " + rs.getString(1) + "  " + rs.getString(2));
        else
            System.out.println(dbTbl + "  " + "  no data in database available");
        if (st != null) st.close();
    }
}




