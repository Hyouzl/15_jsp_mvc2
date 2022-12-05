package boardAdvance.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardAdvance.dao.BoardAdvanceDao;
import boardAdvance.dto.BoardAdvanceDto;

@WebServlet("/boardDelete")
public class BoardDelete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("boardAdvanceDto", BoardAdvanceDao.getInstance().getOneUpdateBoard(Integer.parseInt(request.getParameter("num"))));
		
		RequestDispatcher dis = request.getRequestDispatcher("step_03_boardAdvanceEx/boardDelete.jsp");
		dis.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardAdvanceDto boardAdvanceDto = new BoardAdvanceDto();

		boardAdvanceDto.setNum(Integer.parseInt(request.getParameter("num")));
		boardAdvanceDto.setPassword(request.getParameter("password"));
		
		String jsScript = "";
		if (BoardAdvanceDao.getInstance().deleteBoard(boardAdvanceDto)) {
			 jsScript = "<script>";
			 jsScript += "alert('삭제되었습니다.');";
			 jsScript += "location.href='boardList';";
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
