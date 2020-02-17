package co.yedam.app.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.yedam.app.board.BoardDto;
import co.yedam.app.board.service.impl.BoardService;

@Controller // bean등록, 디스패쳐 서블릿이 호출할 수 있도록 커맨드화 
public class TestController {
	@Autowired BoardService boardService;
	
	
	//하나의 값만 primitive(원시) string, int...
	@RequestMapping("/cont1")
	public String cont1(@RequestParam(required = false, defaultValue = "hong", name = "username") String name ) {

//		@RequestParam == request.getParameter("name")
		System.out.println("=====" + name);
		return "redirect:/";
	}
	//커맨드 객체
	@RequestMapping(value="/cont2", method= RequestMethod.POST)
	public String cont2(BoardDto dto) {
		System.out.println("cont22222222222====" + dto);
		return "redirect:/";
	}
	//경로변수 url?name=value&name2=value2
	@RequestMapping(value="/cont3/{id}/{username}")
	public String cont3(@PathVariable(name= "id") String userid, @PathVariable String username) {
		System.out.println("=========cont3=" + userid);
		System.out.println("=========cont3=" + username);
		return "redirect:/";
	}
	//ajax 응답 (자바객체 -> json String
	
	
	@RequestMapping(value="/cont4")
	@ResponseBody // 자동으로 json 구조로 변환 
	public List<BoardDto> cont4(BoardDto dto){
		List<BoardDto> list = boardService.selectList(dto);
		return list;
	}
	@RequestMapping(value="/getBoard") // 일반 구조일 경우 .jsp 를 찾아감
	@ResponseBody // ajax 일 경우 자동으로 json 구조로 변환해서 페이지 출력
	public BoardDto getBoard(@RequestParam(required=false) int no) {
		BoardDto dto = boardService.boardOneSelect(no);
		return dto;
	}
}
