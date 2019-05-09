import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
//        DBtest test = new DBtest();
//        Order o1 = new Order(2);
//        o1.print();
        Database.openConnection();
        Artikel a = new Artikel(2);
        System.out.println(a);
        Database.closeConnection();
    }
}
