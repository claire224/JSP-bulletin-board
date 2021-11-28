package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

import com.saeyan.dto.BoardVO;
import com.saeyan.dto.SearchVO;

public class BoardDAO {
	private BoardDAO() {
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	public List<BoardVO> selectAllBoards() {
		String sql = "select * from board order by num desc";
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// conn = DBManager.getConnection();
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				list.add(bVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	public List<BoardVO> selectBoardsByPaging(int page, int limit) {
		/*
		SELECT *  
		FROM (SELECT ROWNUM,  
		             m.*,  
		             FLOOR((ROWNUM - 1) / 10 + 1) page  
		      FROM (
		             SELECT * FROM board
		             ORDER BY num ASC
		           ) m  
		      )  
		WHERE page = 1; 
		 */
		
		String sql = "SELECT *  " + 
					 "FROM (SELECT ROWNUM,  " + 
					 "             m.*,  " + 
					 "             FLOOR((ROWNUM - 1) / ? + 1) page  " + 
					 "      FROM (" + 
					 "             SELECT * FROM board " + 
					 "             ORDER BY num ASC " + 
					 "           ) m  " + 
					 "      )  " + 
					 "WHERE page = ?";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// conn = DBManager.getConnection();
			conn = DBManager.getConnectionJDBC();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, page);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				list.add(bVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	/**
	 * 게시판 검색 (페이징) : 유사 검색
	 * 
	 * @param searchKind 검색 구분 ex) 작성자, 글제목, 글내용
	 * @param searchWord 검색어
	 * @param page 페이지
	 * @param limit 페이지 당 게시글 수
	 * @return 검색된 게시글 목록
	 */
	public List<BoardVO> selectBoardsBySearchPaging(String searchKind, 
													String searchWord, 
													int page, int limit) {
		/*
		--- 작성자
		SELECT *  
		FROM (SELECT ROWNUM,  
		             m.*,  
		             FLOOR((ROWNUM - 1) / 10 + 1) page  
		      FROM (
		             SELECT * FROM board
		             WHERE name like '%수%'
		             ORDER BY num ASC
		           ) m  
		      )  
		WHERE page = 1;  
		 */
		String sql = "SELECT *  " + 
					 "FROM (SELECT ROWNUM,  " + 
					 "             m.*,  " + 
					 "             FLOOR((ROWNUM - 1) / ? + 1) page  " + 
					 "      FROM (" + 
					 "             SELECT * FROM board " + 
					 "             WHERE " + searchKind + " like '%" + searchWord + "%' " +    
					 "             ORDER BY num ASC " + 
					 "           ) m  " + 
					 "      )  " + 
					 "WHERE page = ?";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// conn = DBManager.getConnection();
			conn = DBManager.getConnectionJDBC();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, page);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				list.add(bVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	/**
	 * 게시글 목록의 마지막 페이지 계산 
	 * 
	 * @return 마지막 페이지
	 */
	public int selectOneLastPage(int limit) {
		
		// SELECT CEIL(COUNT(*)/10) FROM board;
		
		String sql = "SELECT CEIL(COUNT(*)/?) FROM board";
		Connection conn = null;
		int lastPage = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, limit);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lastPage = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return lastPage;
	}
	
	
	/**
	 * 게시글 목록의 검색 마지막 페이지 계산 
	 * 
	 * @param searchVO 검색 VO(값 객체) : 검색구분, 검색어
	 * @return 마지막 페이지
	 */
	public int selectOneSearchLastPage(SearchVO searchVO) {
		
//		SELECT CEIL(COUNT(*)/10) FROM board
//		WHERE title like '%Spring%';
//
//		SELECT CEIL(COUNT(*)/10) FROM board
//		WHERE content like '%과목%';
		
		String sql = "SELECT CEIL(COUNT(*)/?) FROM board "
				   + "WHERE " + searchVO.getSearchKind() + " "   
				   + "LIKE '%" + searchVO.getSearchWord() + "%'";
		
		Connection conn = null;
		int lastPage = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, searchVO.getLimit());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lastPage = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return lastPage;
	}
 
	public void insertBoard(BoardVO bVo) {
		String sql = "insert into board("
				+ "num, name, email, pass, title, content) "
				+ "values(board_seq.nextval, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void updateReadCount(String num) {
		String sql = "update board set readcount=readcount+1 where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 게시판 글 상세 내용 보기 :글번호로 찾아온다. : 실패 null,
	public BoardVO selectOneBoardByNum(String num) {
		String sql = "select * from board where num = ?";
		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setPass(rs.getString("pass"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setReadcount(rs.getInt("readcount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bVo;
	}

	public void updateBoard(BoardVO bVo) {
		String sql = "update board set name=?, email=?, pass=?, "
				+ "title=?, content=? where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setInt(6, bVo.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public BoardVO checkPassWord(String pass, String num) {
		String sql = "select * from board where pass=? and num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO bVo = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bVo;
	}

	public void deleteBoard(String num) {
		String sql = "delete board where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
