package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;
import com.saeyan.dto.PageDTO;
import com.saeyan.dto.SearchVO;


public class BoardSearchListPagingAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("### paging_action");
		
		String url = "/board/boardSearchList.jsp";
		
		/** 페이지 당 출력 게시글 수 */
		int limit = 10;
		
		/**검색 구분 : 기본값(글제목) */
		String searchKind = request.getParameter("search_kind");
		
		/**검색어 */
		String searchWord = request.getParameter("search_word").trim(); //공백제거
		
		/**검색 페이지 번호 */
		int searchPage = request.getParameter("search_page")==null ||
						 request.getParameter("search_page").trim().equals("") ? 1 :
						 Integer.parseInt(request.getParameter("search_page"));
		
		SearchVO searchVO = new SearchVO();
		searchVO.setLimit(limit);
		searchVO.setPage(searchPage);
		searchVO.setSearchKind(searchKind);
		searchVO.setSearchWord(searchWord); 
		
		System.out.println("현재 페이지 : " + searchPage);
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		List<BoardVO> boardList 
		= bDao.selectBoardsBySearchPaging(searchKind, searchWord, searchPage, limit);
		
		/** 마지막(총) 페이지 */
		int lastPage = bDao.selectOneSearchLastPage(searchVO);  // 마지막 페이지

		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(searchPage);
		pageDTO.setLimit(limit);
		pageDTO.setLastPage(lastPage);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("pageDTO", pageDTO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}