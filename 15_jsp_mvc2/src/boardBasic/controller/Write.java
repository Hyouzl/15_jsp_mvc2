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

@WebServlet("/bWrite")
public class Write extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("step_02_boardBasicEx/write.jsp");
		dis.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardBasicDto boardDto = new BoardBasicDto();
		boardDto.setWriter(request.getParameter("writer"));
		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setEmail(request.getParameter("email"));
		boardDto.setPassword(request.getParameter("password"));
		boardDto.setContent(request.getParameter("content"));
		
		BoardBasicDao.getInstance().insertBoard(boardDto);
		
		// 반환되는 데이터의 encoding지정
		response.setContentType("text/html; charset=utf-8");
		
		// out.print()메서드를 통해서 ajax로 데이터를 전송한다.
		PrintWriter out = response.getWriter();	
		
		String jsScript = "<script>";
			   jsScript += "alert('등록되었습니다.');";
			   jsScript += "location.href='bList';";
			   jsScript += "</script>";
	
		out.print(jsScript);
		
	}
	
	
}
