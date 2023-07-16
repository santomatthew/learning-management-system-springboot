package com.lawencon.lmssanto.dao.impl.hql;

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
@org.springframework.context.annotation.Profile("hql-query")
public class ReviewDaoHQLImpl implements ReviewDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Review insert(Review review) {
		em.persist(review);
		return review;
	}

	@Override
	public List<Review> getByTaskId(Long taskId) {
		final String sql = "SELECT tr.id, tr.createdBy ,tr.student.id, tr.student.profile.fullName FROM Review tr "
				+ " WHERE tr.task.id = :taskId";

		final List<?> reviewObjs = this.em.createQuery(sql).setParameter("taskId", taskId).getResultList();

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
