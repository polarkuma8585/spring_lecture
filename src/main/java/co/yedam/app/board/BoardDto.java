package co.yedam.app.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BoardDto {
	private int no;
	private String subject;
	private String poster;
	private String contents;
	private Date lastpost;
	private int views;
//	@JsonIgnore private byte[] file;
	@JsonIgnore MultipartFile uploadfile; 	   // 업로드한 첨부파일
	@JsonIgnore private String uploadfilename; // 파일명
	@JsonIgnore private int page;
	@JsonIgnore private int start;
	@JsonIgnore private int end;
	@JsonIgnore private int lastPage;
	
	
	public MultipartFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}

	public int getLastPage() {
		return lastPage;
	}

	public String getUploadfilename() {
		return uploadfilename;
	}

	public void setUploadfilename(String uploadfilename) {
		this.uploadfilename = uploadfilename;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public BoardDto() {
		super();
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	

	public BoardDto(int no, String subject, String poster) {
		super();
		this.no = no;
		this.subject = subject;
		this.poster = poster;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getLastpost() {
		return lastpost;
	}
	public void setLastpost(Date lastpost) {
		this.lastpost = lastpost;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", subject=" + subject + ", poster=" + poster + ", contents=" + contents
				+ ", lastpost=" + lastpost + ", views=" + views + ", uploadfilename=" + uploadfilename + ", page="
				+ page + ", start=" + start + ", end=" + end + ", lastPage=" + lastPage + "]";
	}


		
}
