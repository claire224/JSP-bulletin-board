package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardListPagingAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("### paging_action");
		
		String url = "/board/boardList.jsp";
		
		// 인자 처리 : page(현재 페이지)
		int page = request.getParameter("page")==null || 
				   request.getParameter("page").trim().equals("") ? 1 :
				   Integer.parseInt(request.getParameter("page"));	
		
		int limit = 10;
		
		System.out.println("현재 페이지 : " + page);
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		List<BoardVO> boardList = bDao.selectBoardsByPaging(page, limit);
		int lastPage = bDao.selectOneLastPage(limit); // 마지막 페이지

		request.setAttribute("boardList", boardList);
		request.setAttribute("page", page);
		request.setAttribute("last_page", lastPage);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}