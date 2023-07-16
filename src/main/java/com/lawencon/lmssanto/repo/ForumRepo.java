package com.lawencon.lmssanto.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.Forum;

@Repository
public interface ForumRepo extends JpaRepository<Forum, Long>{
	
	@Query(value="SELECT tf.id, tf.forumBody, tf.forumCode, tf.forumTitle, tf.elearning.elearningName "
			+ " FROM Forum tf "
			+ "	WHERE tf.elearning.id = :elearningId",nativeQuery = true)
	Object getByElearningId(Long elearningId);
	
	Forum getById(Long id);
}
