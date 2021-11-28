import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBCPServletTest
 */
@WebServlet("/DBCPServletTest")
public class DBCPServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBCPTest dbcp = new DBCPTest();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("DBCPServletTest doGet");
		System.out.println(dbcp.getConnection());
		dbcp.getTable();
		
	} //

}
