package boardAdvance.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardAdvance.dao.BoardAdvanceDao;
import boardAdvance.dto.BoardAdvanceDto;

@WebServlet("/boardMakeDummyData")
public class MakeDummyData extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Random ran = new Random();
		
		ArrayList<BoardAdvanceDto> dummyDataList = new ArrayList<BoardAdvanceDto>();
	
		String[] word = {"가","나","다","라","마","바","사","아","자","차","카","타","파","하"};
		
		BoardAdvanceDto temp = null;
		String writer;
		String password;
		String subject;
		String email;
		String content;
		
		for (int i = 1; i < 201; i++) {
			
			writer    = "";
			password  = "1111";
			subject   = "";
			email     = "";
			content   = "";
			for (int j = 0; j < 7; j++) {
				writer  += word[ran.nextInt(word.length)];
				subject += word[ran.nextInt(word.length)];
				content += word[ran.nextInt(word.length)];
				if (j < 4) {
					email += word[ran.nextInt(word.length)];
				}
			}
			email += "@gmail.com";
			
			temp = new BoardAdvanceDto();
			temp.setNum(i);		
			temp.setWriter(writer);
			temp.setEmail(email);
			temp.setSubject(subject);
			temp.setPassword(password);
			temp.setRef(i);
			temp.setReLevel(1);
			temp.setReStep(1);
			temp.setReadCount(0);
			temp.setContent(content);
			
			dummyDataList.add(temp);
			
		}
		
		BoardAdvanceDao.getInstance().makeDummyData(dummyDataList);
		
		String jsScript = "<script>";
			   jsScript += "alert('테스트 데이터가 생성되었습니다.');";
			   jsScript += "location.href='boardList';";
			   jsScript += "</script>";
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();	
		out.print(jsScript);
		
	}


}
