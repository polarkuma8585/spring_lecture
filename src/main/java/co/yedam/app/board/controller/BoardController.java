package co.yedam.app.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import co.yedam.app.board.BoardDto;
import co.yedam.app.board.service.impl.BoardService;

@Controller
public class BoardController {
	@Autowired BoardService boardService;
//	@Autowired BoardDao dao;
	//목록 조회
	@RequestMapping("/boardList")
	public String boardList(HttpServletRequest request,BoardDto dto) {
		
		//DAO 목록 조회
		List<BoardDto> list = boardService.selectList(dto);
		//request 속성 추가
		request.setAttribute("list", list);
		//뷰페이지 포워드 
		return "board/boardList";
	}
	//게시글 등록
	@RequestMapping("/boardInsert")
	public String boardInsert(BoardDto dto) throws IllegalStateException, IOException { //객체에 parameter가 자동으로 들어옴.
    // DTO에 parameter 값을 자동으로 받기 위한 조건은 parameter이름과 dto의 변수 이름이 같아야 함.
		//첨부파일 업로드 처리
		MultipartFile uploadFile = dto.getUploadfile();
		String fileName = null;
		if(uploadFile !=null && !uploadFile.isEmpty() && uploadFile.getSize()>0) {
		fileName = uploadFile.getOriginalFilename();
		uploadFile.transferTo(new File("e:/upload/"+fileName)); //임시 폴더에서 지정경로로 파일 이전 
		}
		//첨부파일명 VO에 지정
		dto.setUploadfilename(fileName);
		boardService.insert(dto);
		return "redirect:boardList";
	}
	//게시글 등록 FORM 이동
	@RequestMapping("/boardInsertForm")
	public String boardInsertForm() {
		return "board/boardInsertForm";
	}
	
}
