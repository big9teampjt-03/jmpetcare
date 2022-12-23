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
import hello.model.BoardQuestion;
import hello.model.CommentQuestion;
import hello.service.CommentQuestionService;

@RestController
@RequestMapping("/reply/*")
public class CommentQuestionController {
	@Autowired
	private CommentQuestionService qcommentservice;
  
  //자유게시판댓글추가(인증된 회원만)
  @PostMapping("qcommentInsert/{questionID}") 
  public ResponseEntity<String> qcommentInsert(@PathVariable Long questionID,
		  @RequestBody CommentQuestion qcomment,
		  @AuthenticationPrincipal PrincipalUser principal) {
  BoardQuestion b = new BoardQuestion(); 
  b.setQuestionID(questionID);
  qcomment.setBQuestion(b);
  qcomment.setUser(principal.getUser());
  qcommentservice.insert(qcomment);
  return new ResponseEntity<String>("success", HttpStatus.OK); }
 

  //자유게시판댓글전체보기
	@GetMapping("qclist/{questionID}")
	@ResponseBody
	public List<CommentQuestion> qlist(@PathVariable Long questionID) {
		List<CommentQuestion> qclist = qcommentservice.list(questionID);
		return qclist;
		 //return null;
	}
 
	
  //자유게시판댓글 삭제
  @DeleteMapping("qdelete/{comquestionID}") 
  public Long delete(@PathVariable Long comquestionID,CommentQuestion qcomment) {
	  qcommentservice.delete(comquestionID,qcomment); 
  return comquestionID; 
  }
  
}
