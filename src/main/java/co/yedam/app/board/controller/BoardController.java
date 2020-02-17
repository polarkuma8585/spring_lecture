package co.yedam.app.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.yedam.app.board.BoardDto;
import co.yedam.app.board.service.impl.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;

//	@Autowired BoardDao dao;
	// 목록 조회
	@RequestMapping("/boardList")
	public String boardList(HttpServletRequest request, BoardDto dto) {

		// DAO 목록 조회
		List<BoardDto> list = boardService.selectList(dto);
		// request 속성 추가
		request.setAttribute("list", list);
		// 뷰페이지 포워드
		return "board/boardList";
	}

	// 게시글 등록
	@RequestMapping("/boardInsert")
	public String boardInsert(BoardDto dto, MultipartFile attachfile) throws IllegalStateException, IOException { // 객체에
																													// parameter가
																													// 자동으로
																													// 들어옴.
		// DTO에 parameter 값을 자동으로 받기 위한 조건은 parameter이름과 dto의 변수 이름이 같아야 함.
		// 첨부파일 업로드 처리
//		MultipartFile uploadFile = dto.getUploadfile();
		String fileName = null;
		if (attachfile != null && !attachfile.isEmpty() && attachfile.getSize() > 0) {
			fileName = attachfile.getOriginalFilename();
			
			// 첨부파일명 VO에 지정
			dto.setUploadfilename(fileName); // file-name string
			dto.setUploadfile(attachfile.getBytes()); // lob			
			
			attachfile.transferTo(new File("e:/upload/" + fileName)); // 임시 폴더에서 지정경로로 파일 이전			
		}
		
		boardService.insert(dto);
		return "redirect:boardList";
	}

	// 게시글 등록 FORM 이동
	@RequestMapping("/boardInsertForm")
	public String boardInsertForm() {
		return "board/boardInsertForm";
	}

	@RequestMapping(value = "/filedown")
	public void cvplFileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String atchFileId = (String) commandMap.get("atchFileId");
		File uFile = new File("e:/upload", atchFileId);
		long fSize = uFile.length();
		if (fSize > 0) {
			String mimetype = "application/x-msdownload";
			response.setContentType(mimetype);
			 response.setHeader("Content-Disposition", 
					 			"attachment;filename=\"" + URLEncoder.encode(atchFileId, "utf-8") + "\"");
			//setDisposition(atchFileId, request, response);
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} else {
			response.setContentType("application/x-msdownload");
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<h2>Could not get file name:<br>" + atchFileId + "</h2>");
			printwriter.println("<center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}
	
	@RequestMapping(value="/getByteImage.do")
	public void getByteImage(BoardDto dto, HttpServletResponse response) throws SerialException,
	Exception {
	dto = boardService.boardOneSelect(dto.getNo());
	SerialBlob blob = new SerialBlob(dto.getUploadfile());
	ServletOutputStream outputStream = response.getOutputStream();
	IOUtils.copy(blob.getBinaryStream(), outputStream);
	outputStream.flush();
	outputStream.close();
	}
	
}
