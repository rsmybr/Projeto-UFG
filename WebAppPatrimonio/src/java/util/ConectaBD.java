package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ConectaBD {
	public static Connection getConnection() throws ConectaException {
		try
		{
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/patrimonio", "postgres", "postgres");
		}
		catch (Exception e) {
			throw new ConectaException(e.getMessage());
		}
	}
	public static void closeConnection(Connection con, Statement stmt, ResultSet rs) throws ConectaException {
		close(con, stmt, rs);
	}
	
	public static void closeConnection(Connection con, Statement stmt) throws ConectaException {
		close(con, stmt, null);
	}
	
	public static void closeConnection(Connection con) throws ConectaException {
		close(con, null, null);
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs) throws ConectaException {
		try
		{
			if (rs != null){
                            rs.close();
                            rs = null;
                        }
			if(stmt != null){
                            stmt.close();
                            stmt = null;
                        }
			if(con != null){
                            con.close();
                            con = null;
                        }
		}
		catch (Exception e){
			throw new ConectaException(e.getMessage());
		}
	}

}
