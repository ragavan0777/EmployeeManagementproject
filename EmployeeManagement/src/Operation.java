import java.sql.*;
import java.util.Scanner;
public class Operations 
{
    public static Scanner in;
	public static Connection con;
	static
	{
		in = new Scanner(System.in);
		try {
			con = DBConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int insert() throws SQLException
	{
		//getting input from user
		String emp_name , joined_date , designation;
		int salary;
		System.out.println("----- Inserting ------");
		System.out.print("Employee Name : ");
		emp_name = in.nextLine();
		System.out.print("Joining Date ( yyyy-mm-dd ) : ");
		joined_date = in.nextLine();
		System.out.print("Designation : ");
		designation = in.nextLine();
		System.out.print("Salary : ");
		salary = in.nextInt();
		
		//executing query
		String query = "INSERT INTO employees_details(emp_name,designation,salary,joined_date) VALUES (?,?,?,?)";
		PreparedStatement pt = con.prepareStatement(query);
		pt.setString(1, emp_name);
		pt.setString(2, designation);
		pt.setString(4, joined_date);
		pt.setInt(3, salary);
		int rowsAffected = pt.executeUpdate();
		
		return rowsAffected;
	}
	public static int delete() throws SQLException
	{
		System.out.println("----- Delete ------");
		int id , rowsAffected;
		System.out.print("Enter Employee Id : ");
		id = in.nextInt();
	    
		//executing query
		String query = "DELETE FROM employees_details WHERE id="+id+";";
		Statement st = con.createStatement();
		rowsAffected = st.executeUpdate(query);
		
		return rowsAffected;
	}
	public static void show() throws SQLException
	{
		
		String query = "SELECT * FROM employees_details;";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		//printing table
		String line = " _____________________________________________________________________________________________ ";
		line=line.replace("_", "-");
		System.out.printf("%s\n| %-4s | %-30s | %-25s | %-10s | %-10s |\n%s\n",line,"Id","Employee Name","Designation","Join Date","Salary",line);
	    while( rs.next() )
	    	System.out.printf("| %-4d | %-30s | %-25s | %-10s | %-10d |\n%s\n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getInt(4),line);
	    
	}
	public static int update() throws SQLException
	{
		//1.get employee id
		//2.which column to update
		//3.updated column value
		//4.execute query
		System.out.println("----- Update ------");
		int id , rowsAffected,option;
		String query;
		String[] columns = {"emp_name","designation","joined_date","salary"};
		
		//getting employee id
		System.out.print("Enter Employee Id : ");
		id = in.nextInt();
		
		//getting which column to update
		System.out.println("Choose which column to update : \n1.Employee Name\n2.Designation\n3.Joined Date\n4.Salary");
		option = in.nextInt();
		if( option > 4 || option < 1)
		{
			System.out.println("Invalid Column!");
			return 0;
		}
		
		//updated value
		System.out.print("Enter "+columns[option-1]+" updated value : ");
		if(option != 4) {
		in.nextLine();
		String val = in.nextLine();
		query = "UPDATE employees_details SET "+columns[option-1]+"="+"'"+val+"' WHERE id="+id;
		}
		else
		{
			int val = in.nextInt();
			query = "UPDATE employees_details SET "+columns[option-1]+"="+val+" WHERE id="+id;
		    in.nextLine();
		}
		
	
	    //execute query
		Statement st = con.createStatement();
		rowsAffected = st.executeUpdate(query);
		return rowsAffected;
	}
	
}