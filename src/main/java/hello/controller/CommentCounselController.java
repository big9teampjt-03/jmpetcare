package hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hello.config.auth.PrincipalUser;
import hello.model.BoardCounsel;
import hello.model.CommentCounsel;
import hello.service.CommentCounselService;

@RestController
@RequestMapping("/reply/*")
public class CommentCounselController {
	@Autowired
	CommentCounselService commentcsService;
	 //상담게시판댓글추가(인증된 회원만)
	  @PostMapping("commentInsert/{counselID}") 
	  public ResponseEntity<String> commentInsert(@PathVariable Long counselID,
			  @RequestBody CommentCounsel cscomment,
			  @AuthenticationPrincipal PrincipalUser principal) {
	  BoardCounsel b = new BoardCounsel(); 
	  b.setCounselID(counselID);
	  cscomment.setBCounsel(b);
	  cscomment.setUser(principal.getUser());
	  commentcsService.insert(cscomment);
	  return new ResponseEntity<String>("success", HttpStatus.OK); }
	 

	
	  //상담게시판댓글전체보기
	  
	  @GetMapping("cslist/{counselID}")
	  
	  @ResponseBody 
	  public List<CommentCounsel> cslist(@PathVariable Long counselID) { 
		  List<CommentCounsel> cslist = commentcsService.cslist(counselID); 
		  return cslist; //return null; }
	  }
	 
//	  @GetMapping("cslist/{counselID}")
//	  public CommentCounselListVO cslist(@PathVariable Long counselID) {
//	  	List<CommentCounsel> csarr = commentcsService.cslist(counselID);
//	  	System.out.println(csarr.get(0).getUser().getNickname());
//	  	int cscount = commentcsService.getCscount(counselID);
//	  	CommentCounselListVO cslist = new CommentCounselListVO(csarr, cscount);
//	  	return cslist;
//	  }
		
	  //상담게시판댓글 삭제
	  @DeleteMapping("cdelete/{comcounselID}") 
	  public Long cdelete(@PathVariable Long comcounselID,CommentCounsel cscomment) {
	  commentcsService.cdelete(comcounselID,cscomment); 
	  return comcounselID; 
	  }
}
