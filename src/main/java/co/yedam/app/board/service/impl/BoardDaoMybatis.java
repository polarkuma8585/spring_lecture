package co.yedam.app.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.yedam.app.board.BoardDto;

@Repository //중요
public class BoardDaoMybatis {

	@Autowired
	private SqlSession session;
	
	public int boardCount(BoardDto dto) {
		System.out.println("BoardDaoMybatis boardCount()");
		return session.selectOne("BoardDao.boardCount", dto);
	}
	
	
	public List<BoardDto> selectList(BoardDto dto){
		System.out.println("BoardDaoMybatis boardList()");
		return session.selectList("BoardDao.boardList", dto);
	}
	public int insert(BoardDto dto) {
		System.out.println("BoardDaoMybatis insertBoard()");
		return session.insert("BoardDao.insertBoard", dto);
	}
	
	public BoardDto boardOneSelect(int no) { 
		return session.selectOne("BoardDao.boardOneSelect", no); 
	}
		
}
