import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbScreenTest extends JFrame implements ActionListener {

    private JTable table;
    private ResultSet rs;

    public DbScreenTest(String[][] data, String[] columnNames) {
        setTitle("Database screen test");
        setSize(250, 200);
        setLayout(new FlowLayout());

        setVisible(true);
    }

    public DbScreenTest(ResultSet rs) {
        this.rs = rs;
        JTable table = new JTable();

        setTitle("Database screen test");
        setSize(250, 200);
        setLayout(new FlowLayout());

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int row_count = rsmd.getColumnCount();
            System.out.println(rsmd.getColumnName(1));
            String[] column_names = {rsmd.getColumnName(1), "progressie", "Lijn"};
            ArrayList<ArrayList> arrayList = this.convertRsToTableData(rs);
            String[][] data = new String[arrayList.size()][];
            for (int i = 0; i < arrayList.size(); i++) {
                ArrayList<String> row = arrayList.get(i);
                data[i] = row.toArray(new String[row.size()]);
            }
            table = new JTable(data, column_names);
        } catch (SQLException e){
            e.printStackTrace();
        }



        table.setBounds(30, 40, 100, 100);

        JScrollPane sp = new JScrollPane(table);
        add(sp);


        setVisible(true);
    }

    public DbScreenTest() {
        setTitle("Database screen test");
        setSize(250, 200);
        setLayout(new FlowLayout());

        String[][] data = {
                {"Kundan Kumar Jha", "4031", "CSE"},
                {"Anand Jha", "6014", "IT"}
        };

        String[] columnNames = {"Name", "Roll Number", "Department"};

//        this(data, columnNames);
        table = new JTable(data, columnNames);
        table.setBounds(30, 40, 100, 100);

        JScrollPane sp = new JScrollPane(table);
        add(sp);


        setVisible(true);
    }

    //    public String[][] convertToTable(){
//        String[][] data = new
//        return data;
//    };
    public ArrayList<ArrayList> convertRsToTableData(ResultSet rs) {
        ArrayList<ArrayList> data = new ArrayList<ArrayList>();
        try {
            while (rs.next()) {
                ArrayList<String> sub_data = new ArrayList<String>();
                sub_data.add(Integer.toString(rs.getInt(1)));
                sub_data.add("");
                sub_data.add("");
                data.add(sub_data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}