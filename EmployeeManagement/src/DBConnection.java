import java.sql.*;
public class DBConnection 
{
   public static Connection getConnection() throws SQLException
   {
	 String url = "jdbc:mysql://localhost:3306/employee_db";
     String userName = "root";
   	 String pwd = "";
   	 Connection con = DriverManager.getConnection(url, userName, pwd);
     return con;
   }
}