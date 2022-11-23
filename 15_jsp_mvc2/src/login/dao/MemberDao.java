package login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	

}
