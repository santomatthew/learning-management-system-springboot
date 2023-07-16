package com.lawencon.lmssanto.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ReviewDao;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.model.Review;
import com.lawencon.lmssanto.model.User;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class ReviewDaoImpl implements ReviewDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Review insert(Review review) {
		em.persist(review);

		return review;
	}

	@Override
	public List<Review> getByTaskId(Long taskId) {
		final String sql = "SELECT tr.id, tr.created_by ,tr.student_id, tp.full_name, tr.ver FROM t_review tr "
				+ " INNER JOIN t_user tu ON tu.id = tr.student_id "
				+ " INNER JOIN t_profile tp ON tp.id = tu.profile_id " + " WHERE task_id = :taskId";

		final List<?> reviewObjs = this.em.createNativeQuery(sql).setParameter("taskId", taskId).getResultList();

		final List<Review> reviews = new ArrayList<>();

		if (reviewObjs.size() > 0) {
			for (Object reviewObj : reviewObjs) {
				final Object[] reviewArr = (Object[]) reviewObj;
				final Review getReview = new Review();
				getReview.setId(Long.valueOf(reviewArr[0].toString()));
				getReview.setCreatedBy(Long.valueOf(reviewArr[1].toString()));

				final User student = new User();
				student.setId(Long.valueOf(reviewArr[2].toString()));

				final Profile profile = new Profile();
				profile.setFullName(reviewArr[3].toString());
				student.setProfile(profile);

				getReview.setStudent(student);
				getReview.setVer(Integer.valueOf(reviewArr[4].toString()));

				reviews.add(getReview);
			}
		}

		return reviews;
	}

	@Override
	public Review getById(Long id) {
		final Review review = this.em.find(Review.class, id);
		return review;
	}

}
