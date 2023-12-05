import java.sql.*;

public class test 
{
	public static void main(String args[])
		{ System.out.println("OK");
			try{ 
				Class.forName("com.mysql.cj.jdbc.Driver"); 
				Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.237.3:3308/MusicStreamDB",
					"root","1234"); 
				System.out.println("OK");
				Statement stmt=con.createStatement(); 
				ResultSet rs=stmt.executeQuery("SELECT * FROM Music"); 
				while(rs.next()) 
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+
					" "+rs.getString(3)); 
			con.close(); 
		}catch(Exception e){ System.out.println(e);} 
	} 
}
