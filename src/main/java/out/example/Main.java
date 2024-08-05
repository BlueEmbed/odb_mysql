package out.example;
import java.awt.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://******";
        String username = "*****";
        String passWord = "*********";
        for(int i=0;i<10;i++) {
            Connection coon = DriverManager.getConnection(url, username, passWord);
            String sql = "INSERT INTO t_book (name, author,price) VALUES ('John', 'msb',190)";
            Statement statement = coon.createStatement();
            int count = statement.executeUpdate(sql);
            statement.close();
            coon.close();
        }
        System.out.println("end");
    }
}