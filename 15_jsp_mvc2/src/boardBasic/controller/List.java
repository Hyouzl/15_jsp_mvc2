package boardBasic.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardBasic.dao.BoardBasicDao;
import boardBasic.dto.BoardBasicDto;

@WebServlet("/bList")
public class List extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<BoardBasicDto> boardList = BoardBasicDao.getInstance().getAllBoard();
		
		request.setAttribute("boardList", boardList);
		
		RequestDispatcher dis = request.getRequestDispatcher("step_02_boardBasicEx/list.jsp");
		dis.forward(request, response);
		
	}

}
