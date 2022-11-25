package login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import login.dto.MemberDto;

public class MemberDao {
	
	private MemberDao() {}
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
    public void getConnection() {
        
    	try {
    		
    		Context initCtx = new InitialContext();
    		Context envCtx = (Context)initCtx.lookup("java:comp/env"); // lookup 메서드를 통해 context.xml 파일에 접근하여 자바환경 코드를 검색
    		DataSource ds = (DataSource)envCtx.lookup("jdbc/pool1");    // <Context>태그안의 <Resource> 환경설정의 name이 jdbc/pool1인 것을 검색
    		conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
    public void getClose() {
    	if (rs != null)    {try {rs.close();}   catch (SQLException e) {}}
    	if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
        if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
    }
    
    public boolean joinMember(MemberDto memberDto) {
    	
    	boolean isJoin = false;
    	
    	try {
    		getConnection();
    		pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
    		pstmt.setString(1, memberDto.getId());
    		rs = pstmt.executeQuery();
    		
    		if(!rs.next()) {
    			pstmt = conn.prepareStatement("INSERT INTO MEMBER(ID,PW,NAME,TEL,EMAIL) VALUES(?,?,?,?,?)");
    		
    			pstmt.setString(1,memberDto.getId());
    			pstmt.setString(2,memberDto.getPw());
    			pstmt.setString(3,memberDto.getName());
    			pstmt.setString(4,memberDto.getTel());
    			pstmt.setString(5,memberDto.getEmail());
    			
    			pstmt.executeUpdate();
    			isJoin = true;  
    		}
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	} finally {
			getClose();
		}
    	
    	return isJoin;
    	
    }
    
    public boolean loginMember(MemberDto memberDto) {
    	
    	boolean isLogin = false;
    	
    	try {
    		getConnection();
    		
    		pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ? AND PW = ?");
    		pstmt.setString(1, memberDto.getId());
    		pstmt.setString(2, memberDto.getPw());
    		
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			isLogin =true;
    		}
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	} finally{
    		getClose();
		}
    	
    	return isLogin;
    
    }
    
    
    public MemberDto getOneMemberInfo(String id) {
    	
    	MemberDto memberDto = new MemberDto();
    	
    	try {
    		getConnection();
    		pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
    		pstmt.setString(1, id);
    		
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
   
    			memberDto.setId(rs.getString("ID"));
    			memberDto.setPw(rs.getString("PW"));
    			memberDto.setName(rs.getString("NAME"));
    			memberDto.setTel(rs.getString("TEL"));
    			memberDto.setEmail(rs.getString("EMAIL"));
    			memberDto.setField(rs.getString("Field"));
    			memberDto.setSkill(rs.getString("SKILL"));
    			memberDto.setMajor(rs.getString("MAJOR"));
    		
    		}
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	} finally{
    		getClose();
		}
		
 
    	
    	return memberDto;
    }
	
    
    public void apply(MemberDto memberDto) {
    	
    	// System.out.println(memberDto); // 오류 나는 곳 전,후 어디가 문제인지 파악하기 위해 찍어보기
    	try {
    		getConnection();
    		pstmt = conn.prepareStatement("UPDATE MEMBER SET NAME=? , TEL=? , EMAIL=? , FIELD=? , SKILL=? , MAJOR=? WHERE ID=?");
    		pstmt.setString(1, memberDto.getName());
    		pstmt.setString(2, memberDto.getTel());
    		pstmt.setString(3, memberDto.getEmail());
    		pstmt.setString(4, memberDto.getField());
    		pstmt.setString(5, memberDto.getSkill());
    		pstmt.setString(6, memberDto.getMajor());
    		pstmt.setString(7, memberDto.getId());
    	
    		pstmt.executeUpdate();
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	} finally{
    		getClose();
		}
    }
    
    
    public void deleteMember(String id) {
    	
    	try {
    		getConnection();
			pstmt = conn.prepareStatement("DELETE FROM MEMBER WHERE ID = ?");
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
    	
    }
    
    public void updateMember(MemberDto memberDto) {
    	try {
    		getConnection();
    		pstmt = conn.prepareStatement("UPDATE MEMBER SET NAME=? , PW = ?, TEL=? , EMAIL=? , FIELD=? , SKILL=? , MAJOR=? WHERE ID=?");
    		pstmt.setString(1, memberDto.getName());
    		pstmt.setString(2, memberDto.getPw());
    		pstmt.setString(3, memberDto.getTel());
    		pstmt.setString(4, memberDto.getEmail());
    		pstmt.setString(5, memberDto.getField());
    		pstmt.setString(6, memberDto.getSkill());
    		pstmt.setString(7, memberDto.getMajor());
    		pstmt.setString(8, memberDto.getId());
    	
    		pstmt.executeUpdate();
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	} finally{
    		getClose();
		}
    }

}
