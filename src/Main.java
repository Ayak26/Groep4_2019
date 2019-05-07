public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Drive");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Database.openConnection();


    }
}
