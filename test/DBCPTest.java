import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCPTest {

	// DB 연결(connection) : DBCP
	public Connection getConnection() {
		
		DataSource ds;
		Connection con = null;
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/myoracle");
			con = ds.getConnection();
		} catch (NamingException e) {
			System.err.println("getConnection DBCP Naming Exception");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("getConnection DBCP SQL Exception");
			e.printStackTrace();
		}

		return con;
	}

	public void getTable() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member2"; // SQL

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		new DBCPTest().getTable();
	}

}
