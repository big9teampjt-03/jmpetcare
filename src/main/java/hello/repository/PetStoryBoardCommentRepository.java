package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hello.model.CommentPetStory;

public interface PetStoryBoardCommentRepository extends JpaRepository<CommentPetStory, Long>{
	@Modifying
	@Query(value = "insert into comment_pet_story(content, regdate, bpetstorynum, username, wuser) values(?1,now(), ?2, ?3, ?4)",nativeQuery = true)
	public void insert(String content, Long bum, String username, Long wuser);
	
	 //@Query("select cps from comment_pet_story cps where petstoryID =?1") public
	 @Query("select cps from comment_pet_story cps join fetch cps.bPetstoryl where bpetstorynum=?1")
	 List<CommentPetStory> findByPetStoryId(Long petstoryID);

}