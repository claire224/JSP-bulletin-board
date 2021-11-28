package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("demo action");
		
		request.setAttribute("demo", "jsp");
		
		RequestDispatcher rd = request.getRequestDispatcher("/demo/demo.jsp");
		rd.forward(request, response);
	} //

}
