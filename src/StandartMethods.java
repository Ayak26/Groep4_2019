import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class StandartMethods {


    /**
     * @param rs
     *
     * Converts a resultset to arrayList(s) inside another arrayList so that it can be used with initialising a JTable
     *
     * @return String[][]
     */
    public static String[][] convertRsToTableData(ResultSet rs) {
        ArrayList<ArrayList> data = new ArrayList<ArrayList>();
        String[][] return_data;
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

        return_data = convertMArrayListToMArray(data);

        return return_data;
    }

    /**
     * @param array_list
     *
     * Converts a multidimensional arrayList to a multidimensional Array
     *
     * @return String[][]
     */
    public static String[][] convertMArrayListToMArray(ArrayList<ArrayList> array_list){
        String[][] data = new String[array_list.size()][];
        for (int i = 0; i < array_list.size(); i++) {
            ArrayList<String> row = array_list.get(i);
            data[i] = row.toArray(new String[row.size()]);
        }
        return data;
    }
}
