import java.sql.*;

public class ShowSqlTable {
    // Extend String to length of 14 characters
    private static final String extendStringTo14(String s) {
        final String sFillStrWithWantLen = "                         ";
        final int iWantLen = sFillStrWithWantLen.length();
        if (s.length() < iWantLen)
            s = (s + sFillStrWithWantLen).substring(0, iWantLen);
        return s;
    }

    public static void main(String[] argv) {
        String sDbDriver = null, sDbUrl = null, sTable = null, sUsr = "", sPwd = "";
        if (3 <= argv.length) {
            sDbDriver = argv[0];
            sDbUrl = argv[1];
            sTable = argv[2];
            if (4 <= argv.length) sUsr = argv[3];
            if (5 <= argv.length) sPwd = argv[4];
        }

        sDbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        sDbUrl = "jdbc:sqlserver://192.168.7.21:1433;\" + \"databaseName=ZPA;";

        // Name der Tabelle eingeben (z.B. student)
        sTable = "studierende";

        // Benutzername (z.B. root)
        sUsr = "sql-member";

        // Passwort (z.B. init)
        sPwd = "Swordfish2016";

        if (null != sDbDriver && 0 < sDbDriver.length() &&
                null != sDbUrl && 0 < sDbUrl.length() &&
                null != sTable && 0 < sTable.length()) {
            try {
                // Select fitting database driver and connect:
                Class.forName(sDbDriver);
                Connection cn = DriverManager.getConnection(sDbUrl, sUsr, sPwd);
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("select * from " + sTable);
                ResultSetMetaData rsmd = rs.getMetaData();
                int i, n = rsmd.getColumnCount();
                for (i = 0; i < n; i++)
                    System.out.print("+-------------------------");
                System.out.println("+");
                for (i = 1; i <= n; i++) {
                    // Attention: first column with 1 instead of 0
                    if (i == 1) {
                        System.out.print("|");
                    }
                    System.out.print(extendStringTo14(rsmd.getColumnName(i)));
                    System.out.print("|");
                }
                System.out.println("");
                for (i = 0; i < n; i++) {
                    System.out.print("+-------------------------");
                }
                System.out.println("+");

                while (rs.next()) {

                    Integer counter = 1;
                    for (i = 1; i <= n; i++) {
                        if (counter == 1) {
                            System.out.print("|");
                        }
                        if (rs.getString(counter) == null) {

                            counter++;
                            System.out.print(extendStringTo14("NULL"));
                            System.out.print("|");
                        } else {

                            System.out.print(extendStringTo14(rs.getString(counter)));
                            System.out.print("|");
                            counter++;
                        }

                    }
                    System.out.println("");
                }
                for (i = 0; i < n; i++) {
                    System.out.print("+-------------------------");
                }
                System.out.println("+");
                rs.close();
                st.close();
                cn.close();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}

