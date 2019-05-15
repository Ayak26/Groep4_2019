import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DbScreenTest extends JFrame implements ActionListener {

    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private int row_count;


    public DbScreenTest(ResultSet rs) {
        this.rs = rs;
        try {
            this.rsmd = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setTitle("Database screen test");
        setSize(800, 480);
        setLayout(new FlowLayout());

        JTable table = this.CreateTableFromResultset();
        table.setBounds(30, 40, 100, 100);
        JScrollPane sp = new JScrollPane(table);
        add(sp);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private JTable CreateTableFromResultset() {
        JTable table = new JTable();

        try {
            int row_count = rsmd.getColumnCount();
            String[] column_names = setColumnNamesForTable();

            String[][] data = StandartMethods.convertRsToTableData(rs);
            DefaultTableModel tableModel = new DefaultTableModel(data, column_names) {
                Class[] types = {Integer.class, Integer.class, Integer.class};

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };
            table.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return table;
    }

    private String[] setColumnNamesForTable() {
        String[] column_names = new String[3];

        try {
            column_names[0] = rsmd.getColumnName(1);
            column_names[1] = "progressie";
            column_names[2] = "Lijn";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return column_names;
    }
}