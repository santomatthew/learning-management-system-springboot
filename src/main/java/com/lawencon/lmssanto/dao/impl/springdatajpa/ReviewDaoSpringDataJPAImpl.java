package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ReviewDao;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.model.Review;
import com.lawencon.lmssanto.model.User;
import com.lawencon.lmssanto.repo.ReviewRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class ReviewDaoSpringDataJPAImpl implements ReviewDao {

	@PersistenceContext
	private EntityManager em;
	private ReviewRepo reviewRepo;

	public ReviewDaoSpringDataJPAImpl(ReviewRepo reviewRepo) {
		this.reviewRepo = reviewRepo;
	}

	@Override
	public Review insert(Review review) {
		reviewRepo.save(review);

		return review;
	}

	@Override
	public List<Review> getByTaskId(Long taskId) {

		final List<?> reviewObjs = reviewRepo.getByTaskId(taskId);

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
		final Review review = reviewRepo.getById(id);
		return review;
	}

}
