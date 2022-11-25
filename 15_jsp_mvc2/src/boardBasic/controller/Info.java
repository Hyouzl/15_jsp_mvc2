package boardBasic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardBasic.dao.BoardBasicDao;
import boardBasic.dto.BoardBasicDto;


@WebServlet("/bInfo")
public class Info extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardBasicDto boardDto = BoardBasicDao.getInstance().getOneBoard(Integer.parseInt(request.getParameter("num")));
		request.setAttribute("boardDto", boardDto);
		
		RequestDispatcher dis = request.getRequestDispatcher("step_02_boardBasicEx/info.jsp");
		dis.forward(request, response);
		
	}


}
