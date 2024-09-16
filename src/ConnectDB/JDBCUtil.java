/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDB;

/**
 *
 * @author Kiet
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class JDBCUtil {
    public static Connection getConnectDB() {
        Connection result = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mySQL://localhost:3306/quanlilaptop";
            String name = "root";
            String pass = "";

            result = DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu !", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }
    
    public static void close(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }
}
