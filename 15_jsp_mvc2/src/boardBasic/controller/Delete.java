package boardBasic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardBasic.dao.BoardBasicDao;
import boardBasic.dto.BoardBasicDto;

@WebServlet("/bDelete")
public class Delete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardBasicDto boardDto = BoardBasicDao.getInstance().getOneBoard(Integer.parseInt(request.getParameter("num")));
		request.setAttribute("boardDto" , boardDto);
		
		RequestDispatcher dis = request.getRequestDispatcher("step_02_boardBasicEx/delete.jsp");
		dis.forward(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardBasicDto boardDto = new BoardBasicDto();
		boardDto.setNum(Integer.parseInt(request.getParameter("num")));
		boardDto.setPassword(request.getParameter("password"));
		
		String jsScript = "";
		if (BoardBasicDao.getInstance().deleteBoard(boardDto)) {
			 jsScript = "<script>";
			 jsScript += "alert('삭제되었습니다.');";
			 jsScript += "location.href='bList';";
			 jsScript += "</script>";
		}
		else {
			 jsScript = "<script>";
			 jsScript += "alert('패스워드가 일치하지 않습니다.');";
			 jsScript += "history.go(-1);";
			 jsScript += "</script>";
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();	
		out.print(jsScript);
		
	}
	
	
}
