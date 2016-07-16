import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class JTableview extends JFrame {
    JTable SQLTable = null;
    DBCon con = null;

    // 1. create JTable view based on a generic SQL query
    public JTableview(String str, Integer i) throws SQLException {
        SQLTable = genSQLTable(str,i);
    }

    public JTable getSQLTable() {
        return SQLTable;
    }

    // 1. based on the result set of the SQL query a JTable is created
    private JTable genSQLTable(String str, Integer i) throws SQLException {

        // 1.1 create JTable
        int columnCount = 0;
        int cnt = 1;
        // initialize local JTable view
        JTable tableview = new JTable();
        tableview.enableInputMethods(false);
        tableview.setDragEnabled(false);
        tableview.setColumnSelectionAllowed(false);
        // selection of exact one field value
        // tableview.setColumnSelectionAllowed(true);
        tableview.setRowSelectionAllowed(true);
        tableview.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 1.2 create model and initialize model with reference to JTable view
        DefaultTableModel model = (DefaultTableModel) tableview.getModel();
        try {
            // initialize DB connection
            DBCon ZPA = new DBCon();
            ZPA.connect_DB();
            ResultSet rs = ZPA.getRs(str , i);
            if (rs!= null)
            {
                // 1.3 fill model: identify column size of the result set
                ResultSetMetaData rsmd = rs.getMetaData();
                columnCount = rsmd.getColumnCount();

                // add column labels to the model
                for (int column = 1; column <= columnCount; column++) {
                    model.addColumn(rsmd.getColumnLabel(column));
                }

                // add rows content to the model
                Object[] objects = new Object[columnCount];
                while (rs.next()) {
                    cnt = 0;
                    // add column content of next row to the model
                    while (cnt < columnCount) {
                        objects[cnt] = rs.getObject(cnt + 1);
                        cnt++;
                    }
                    model.addRow(objects);
                }
                ZPA.disconnect();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }

        // 1.4 set model content to JTable view and return JTable view
        tableview.setModel(model);
        return tableview;
    }

    // 5. update JTable view based on a generic SQL query
    public void updateSQLTable(String str,Integer i)
    {
        int columnCount = 0;
        int rowCount = 0;
        int cnt = 0;

        // 5.1 get model from reference to JTable view and initialize model
        DefaultTableModel model = (DefaultTableModel) SQLTable.getModel();
        rowCount = model.getRowCount();
        while (rowCount != 0)
        {
            model.removeRow(rowCount-1);
            rowCount--;
        }

        try {
            // get current DB connection
            DBCon con = new DBCon();
            con.connect_DB();
            ResultSet rs = con.getRs(str, i);
            if (rs!= null)
            {
                // 5.2 fill model: identify column size of the result set
                ResultSetMetaData rsmd = rs.getMetaData();
                columnCount = rsmd.getColumnCount();
            }
            // add rows content to the model
            Object[] objects = new Object[columnCount];
            while (rs.next()) {
                cnt = 0;
                // add column content of next row to the model
                while (cnt < columnCount) {
                    objects[cnt] = rs.getObject(cnt + 1);
                    cnt++;
                }
                model.addRow(objects);
            }
            con.disconnect();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
        // 5.3 refresh model content to JTable view
        SQLTable.revalidate();
    }
}
