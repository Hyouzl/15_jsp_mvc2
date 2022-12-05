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


@WebServlet("/boardRe")
public class BoardRe extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BoardAdvanceDto boardAdvanceDto = BoardAdvanceDao.getInstance().getOneBoard(Integer.parseInt(request.getParameter("num")));
		
		request.setAttribute("ref"     , boardAdvanceDto.getRef());
		request.setAttribute("reStep"  , boardAdvanceDto.getReStep());
		request.setAttribute("reLevel" , boardAdvanceDto.getReLevel());
		
		RequestDispatcher dis = request.getRequestDispatcher("step_03_boardAdvanceEx/boardRe.jsp");
		dis.forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		BoardAdvanceDto boardAdvanceDto = new BoardAdvanceDto();
		boardAdvanceDto.setWriter(request.getParameter("writer"));
		boardAdvanceDto.setSubject(request.getParameter("subject"));
		boardAdvanceDto.setEmail(request.getParameter("email"));
		boardAdvanceDto.setPassword(request.getParameter("password"));
		boardAdvanceDto.setContent(request.getParameter("content"));
		boardAdvanceDto.setRef(Integer.parseInt(request.getParameter("ref")));
		boardAdvanceDto.setReStep(Integer.parseInt(request.getParameter("reStep")));
		boardAdvanceDto.setReLevel(Integer.parseInt(request.getParameter("reLevel")));
		
		BoardAdvanceDao.getInstance().reWriteBoard(boardAdvanceDto);
		
		String jsScript = "<script>";
			   jsScript += "alert('댓글이 등록되었습니다.');";
			   jsScript += "location.href='boardList';";
			   jsScript += "</script>";
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();	
		out.print(jsScript);
		
	}
	
	
}
