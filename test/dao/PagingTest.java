/**
 * 
 */
package dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

/**
 * @author javateam
 *
 */
class PagingTest {
	
	BoardDAO dao = BoardDAO.getInstance();
	int page;
	String searchKind;
	String searchWord;
	int limit;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		page = 2;
//		searchKind = "content";
//		searchWord = "과목";
		
		searchKind = "title";
		searchWord = "Spring";
		limit = 10;
	}
	
	/**
	 * Test method for {@link com.saeyan.dao.BoardDAO#selectBoardsByPaging(int, int)}.
	 */
	@Test
	void testSelectBoardsByPaging() {
		System.out.println("페이징 테스트");
		
		for (BoardVO b : dao.selectBoardsByPaging(page, 10)) {
			System.out.println(b);
		}
	} //

}