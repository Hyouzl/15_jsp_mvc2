package boardBasic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import boardBasic.dto.BoardBasicDto;

public class BoardBasicDao {

	private BoardBasicDao() {}
	private static BoardBasicDao instance = new BoardBasicDao();
	public static BoardBasicDao getInstance() {
		return instance;
	}

	private Connection conn         = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs            = null;

	public void getConnection() {
		
		try {
			
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool2");
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
	

	public ArrayList<BoardBasicDto> getAllBoard() {

		ArrayList<BoardBasicDto> boardList = new ArrayList<BoardBasicDto>();
		BoardBasicDto boardBasicDto = null;
		
		try {
			
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {

				boardBasicDto = new BoardBasicDto();
				boardBasicDto.setNum(rs.getInt("NUM"));
				boardBasicDto.setWriter(rs.getString("WRITER"));
				boardBasicDto.setEmail(rs.getString("EMAIL"));
				boardBasicDto.setSubject(rs.getString("SUBJECT"));
				boardBasicDto.setPassword(rs.getString("PASSWORD"));
				boardBasicDto.setRegDate(rs.getDate("REG_DATE"));
				boardBasicDto.setReadCount(rs.getInt("READ_COUNT"));
				boardBasicDto.setContent(rs.getString("CONTENT"));

				boardList.add(boardBasicDto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return boardList;
		
	}
	
	
	public BoardBasicDto getOneBoard(int num) {

		BoardBasicDto boardBasicDto = new BoardBasicDto();

		try {
			
			getConnection();

			pstmt = conn.prepareStatement("UPDATE BOARD SET READ_COUNT = READ_COUNT + 1 WHERE NUM = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				boardBasicDto.setNum(rs.getInt("NUM"));
				boardBasicDto.setWriter(rs.getString("WRITER"));
				boardBasicDto.setEmail(rs.getString("EMAIL"));
				boardBasicDto.setSubject(rs.getString("SUBJECT"));
				boardBasicDto.setPassword(rs.getString("PASSWORD"));
				boardBasicDto.setRegDate(rs.getDate("REG_DATE"));
				boardBasicDto.setReadCount(rs.getInt("READ_COUNT"));
				boardBasicDto.setContent(rs.getString("CONTENT"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return boardBasicDto;
		
	}

	
	public BoardBasicDto getOneUpdateBoard(int num) {

		BoardBasicDto boardBasicDto = new BoardBasicDto();

		try {
			
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				boardBasicDto.setNum(rs.getInt("NUM"));
				boardBasicDto.setWriter(rs.getString("WRITER"));
				boardBasicDto.setEmail(rs.getString("EMAIL"));
				boardBasicDto.setSubject(rs.getString("SUBJECT"));
				boardBasicDto.setPassword(rs.getString("PASSWORD"));
				boardBasicDto.setRegDate(rs.getDate("REG_DATE"));
				boardBasicDto.setReadCount(rs.getInt("READ_COUNT"));
				boardBasicDto.setContent(rs.getString("CONTENT"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return boardBasicDto;
		
	}

	
	public boolean validMemberCheck(BoardBasicDto boardBasicDto) {

		boolean isValidMember = false;
		
		try {
			
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM = ? AND PASSWORD = ?");
			pstmt.setInt(1, boardBasicDto.getNum());
			pstmt.setString(2, boardBasicDto.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) 	isValidMember = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}

		return isValidMember;
		
	}
	
	
	public void insertBoard(BoardBasicDto boardDto) {

		try {
			
				getConnection();
				String sql = "INSERT INTO BOARD(WRITER , EMAIL , SUBJECT , PASSWORD , REG_DATE , READ_COUNT , CONTENT)";
					   sql += "VALUES(? ,  ? ,  ? , ? , NOW() , 0 , ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardDto.getWriter());
				pstmt.setString(2, boardDto.getEmail());
				pstmt.setString(3, boardDto.getSubject());
				pstmt.setString(4, boardDto.getPassword());
				pstmt.setString(5, boardDto.getContent());
				pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}	
		
	}
	
	
	public boolean updateBoard(BoardBasicDto boardDto) {

		boolean isUpdate = false;
		
		try {
			
			if (validMemberCheck(boardDto)) {
				getConnection();
				pstmt = conn.prepareStatement("UPDATE BOARD SET SUBJECT = ? , CONTENT = ? WHERE NUM = ?");
				pstmt.setString(1, boardDto.getSubject());
				pstmt.setString(2, boardDto.getContent());
				pstmt.setInt(3, boardDto.getNum());
				pstmt.executeUpdate();
				isUpdate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return isUpdate;
		
	}

	
	public boolean deleteBoard(BoardBasicDto boardBasicDto) {

		boolean isDelete = false;
		
		try {
			
			if (validMemberCheck(boardBasicDto)) {
				
				getConnection();
				pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM = ?");
				pstmt.setInt(1, boardBasicDto.getNum());
				pstmt.executeUpdate();
				
				isDelete = true;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return isDelete;
		
	}
	
	
}
