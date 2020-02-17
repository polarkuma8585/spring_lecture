package co.yedam.app.board.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import co.yedam.app.board.BoardDto;

@Component
public class BoardDao {
	
	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	protected DataSource ds; //커넥션 pool 사용을 위한 데이터 연결 생성 객체
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.120.129:1521:orcl";
	private String user = "hr";
	private String pw = "hr";
	
	//추가
	public int insert(BoardDto dto) {		
		int n = 0;
		//DB연결 
		try {
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			String sql = "insert into board(no,poster,subject,contents,lastpost,views) "
					+ "values((select max(no)+1 from board),?,?,?,sysdate,1)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPoster());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContents());
			n = pstmt.executeUpdate();
		}catch(Exception e){
			e.getStackTrace();
		}
		return n;
	}
	//수정
	public int update(BoardDto dto) {
		int n = 0;
		try {
			String sql = "update board set subject=?, contents=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContents());
			pstmt.setInt(3, dto.getNo());
			n = pstmt.executeUpdate();
		}catch(Exception e){
			e.getStackTrace();
		}
		return n;
	}
	//삭제
	public int delete(BoardDto dto) {
		int n = 0;
		try {
			String sql = "delete board where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			n = pstmt.executeUpdate();
		}catch(Exception e){
			e.getStackTrace();
		}
		return n;
	}
	//단건조회
	public BoardDto selectOne(int no) {
		BoardDto dto = new BoardDto();
		try {
			String sql = "select * from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			dto.setNo(rs.getInt("no"));
			dto.setPoster(rs.getString("poster"));
			dto.setSubject(rs.getString("subject"));
			dto.setContents(rs.getString("contents"));
//			dto.setLastpost(rs.getDate("lastpost"));
			dto.setViews(rs.getInt("views"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	//목록조회
	public List<BoardDto> selectList(){
		List<BoardDto> list = new ArrayList<BoardDto>();
		//1. DB연결
		try {
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			//2. 쿼리 실행
			String sql = "select * from board order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			//3. 결과 저장
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setNo(rs.getInt("no"));
				dto.setPoster(rs.getString("poster"));
				dto.setSubject(rs.getString("subject"));
				dto.setContents(rs.getString("contents"));
//				dto.setLastpost(rs.getDate("lastpost"));
				dto.setViews(rs.getInt("views"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
}
