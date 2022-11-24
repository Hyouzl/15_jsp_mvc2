package login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.dao.MemberDao;
import login.dto.MemberDto;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dis = request.getRequestDispatcher("step_01_loginEx/04_login.jsp");
			dis.forward(request, response);
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		MemberDto memberDto = new MemberDto();
		memberDto.setId(request.getParameter("id"));
		memberDto.setPw(request.getParameter("pw"));
		
		if(MemberDao.getInstance().loginMember(memberDto)) {
			HttpSession session = request.getSession();
			session.setAttribute("id", request.getParameter("id"));
		}
		
	
		RequestDispatcher dis = request.getRequestDispatcher("step_01_loginEx/05_loginAction.jsp");
		dis.forward(request, response);
	}

}
