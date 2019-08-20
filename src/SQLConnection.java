import java.sql.*;
import javax.swing.JOptionPane;


public class SQLConnection {
	
	Connection con = null;
	
	public  Connection dbconnect() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fireu\\Documents\\School\\Java\\sql\\Test1.db");
			return con;			
		}//end of try
		
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}//end of catch
		
	}//end of dbconnect
	
}//end of SQLConnection
