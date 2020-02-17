package co.yedam.app.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.yedam.app.board.BoardDto;
import co.yedam.app.board.service.impl.BoardDao;
import co.yedam.app.common.LogAdvice;

@Service //이용시 반드시 등록
public class BoardService {
	@Autowired BoardDaoMybatis dao;
	
	
	//목록조회
	public List<BoardDto> selectList(BoardDto dto){
		//권한체크
		//트랜잭션 처리
		//로그 기록	
		//페이징 처리
		if(dto.getPage() == 0) {
			dto.setPage(1);
		}
		int p = dto.getPage();
		int pageunit = 4;
		int start = (p-1) * pageunit + 1; // 1 -> 1, 3
		int end = start + pageunit - 1;  // 2-> 4, 6
		dto.setStart(start);
		dto.setEnd(end);

		//전체 페이지 수
		int totalrecord = dao.boardCount(dto);
		int lastPage = totalrecord / pageunit + ((totalrecord % pageunit > 0) ? 1: 0);
		dto.setLastPage(lastPage);
		
		return dao.selectList(dto);
	}
	//등록
	public int insert(BoardDto dto) {
		//권한체크
		//트랜잭션 처리
		//로그 기록
		//autocommit -> false
		//dao.insert(dto); // commit
		//int r = dao.insert(dto); // 에러 생성 (트랜잭션 실습)
		return dao.insert(dto);
	}
	
	public BoardDto boardOneSelect(int no) {
		return dao.boardOneSelect(no);
	}
}
