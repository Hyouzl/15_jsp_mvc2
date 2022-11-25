package login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.dao.MemberDao;
import login.dto.MemberDto;

@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		MemberDto memberDto = MemberDao.getInstance().getOneMemberInfo((String)session.getAttribute("id"));
		
		if(memberDto.getField() != null) {
			request.setAttribute("isFirstApply", false);
			request.setAttribute("memberDto", memberDto);
			
			String[] skills = memberDto.getSkill().split(",");
			for (String skill : skills) {
				if(skill.equals("html")) request.setAttribute("html", true);
				if(skill.equals("css")) request.setAttribute("css", true);
				if(skill.equals("javascript")) request.setAttribute("javascript", true);
				if(skill.equals("java")) request.setAttribute("java", true);
				if(skill.equals("jsp")) request.setAttribute("jsp", true);
				if(skill.equals("spring")) request.setAttribute("spring", true);
			}
		} 
		else {
			request.setAttribute("isFirstApply", true);
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("step_01_loginEx/10_update.jsp"); 
		dis.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		MemberDto memberDto = new MemberDto();
		memberDto.setId(request.getParameter("id"));
		memberDto.setPw(request.getParameter("pw"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setTel(request.getParameter("tel"));
		memberDto.setField(request.getParameter("field"));
		memberDto.setMajor(request.getParameter("major"));
		
		String[] temp = request.getParameterValues("skill"); 
		String skill = "";
		
		for(int i = 0; i < temp.length; i++) {		
			skill += temp[i];
			if (i != temp.length - 1) {
				skill += ",";
			}
		}
		
		memberDto.setSkill(skill);
		MemberDao.getInstance().updateMember(memberDto);
		

		RequestDispatcher dis = request.getRequestDispatcher("step_01_loginEx/11_updateAction.jsp"); 
		dis.forward(request, response);
	}



}
