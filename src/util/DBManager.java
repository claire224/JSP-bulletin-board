package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// jdbc/myoracle이란 이름을 객체를 찾아서 DataSource가 받는다.
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			// ds가 생성되었으므로 Connection을 구합니다.
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnectionJDBC() {
		
		Connection conn = null;
		
		 // oracle spec
	    final String oracleDriver = "oracle.jdbc.OracleDriver"; // 드라이버
	    final String oracleURL = "jdbc:oracle:thin:@localhost:1521:xe"; // URL
	    final String oracleID = "java"; // 계정 아이디
	    final String oraclePW = "java"; // 패쓰워드
	    
		try {
            Class.forName(oracleDriver);
            conn = DriverManager.getConnection(oracleURL, oracleID, oraclePW);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return conn;
	}
	
	
	
	// select을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DML(insert, update, delete)을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
