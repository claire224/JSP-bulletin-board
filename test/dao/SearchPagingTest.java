/**
 * 
 */
package dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

/**
 * 게시판 페이징 조회 테스트
 * 
 * @author javateam
 *
 */
public class SearchPagingTest {
	
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
	public void testSelectBoardsByPaging() {

		System.out.println("검색 페이징 테스트");
		
		for (BoardVO b : dao.selectBoardsBySearchPaging(searchKind, searchWord, page, limit)) {
			System.out.println(b);
		}
	} //

}