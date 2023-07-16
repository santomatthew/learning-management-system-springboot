package com.lawencon.lmssanto.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.lawencon.lmssanto.constant.QuestionTypes;
import com.lawencon.lmssanto.dao.ClassRoomDao;
import com.lawencon.lmssanto.dao.CommentDao;
import com.lawencon.lmssanto.dao.ElearningDao;
import com.lawencon.lmssanto.dao.FileDao;
import com.lawencon.lmssanto.dao.ForumDao;
import com.lawencon.lmssanto.dao.MaterialDao;
import com.lawencon.lmssanto.dao.MaterialDetailDao;
import com.lawencon.lmssanto.dao.QuestionAnswerDao;
import com.lawencon.lmssanto.dao.QuestionAnswerDetailDao;
import com.lawencon.lmssanto.dao.QuestionDao;
import com.lawencon.lmssanto.dao.QuestionFileDao;
import com.lawencon.lmssanto.dao.QuestionTypeDao;
import com.lawencon.lmssanto.dao.ReviewDao;
import com.lawencon.lmssanto.dao.StudentAttendanceDao;
import com.lawencon.lmssanto.dao.TaskDao;
import com.lawencon.lmssanto.dao.UserDao;
import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.UpdateResDto;
import com.lawencon.lmssanto.dto.classroom.ClassRoomGetResDto;
import com.lawencon.lmssanto.dto.comment.CommentInsertReqDto;
import com.lawencon.lmssanto.dto.comment.CommentsGetResDto;
import com.lawencon.lmssanto.dto.elearning.ElearningInsertReqDto;
import com.lawencon.lmssanto.dto.elearning.ElearningsGetResDto;
import com.lawencon.lmssanto.dto.file.AnswerFilesGetResDto;
import com.lawencon.lmssanto.dto.file.FilesInsertReqDto;
import com.lawencon.lmssanto.dto.file.QuestionFilesResDto;
import com.lawencon.lmssanto.dto.forum.ForumGetReqDto;
import com.lawencon.lmssanto.dto.forum.ForumGetResDto;
import com.lawencon.lmssanto.dto.material.MaterialsInsertReqDto;
import com.lawencon.lmssanto.dto.question.QuestionInsertReqDto;
import com.lawencon.lmssanto.dto.questionanswer.AnswerGetResDto;
import com.lawencon.lmssanto.dto.questionanswer.AnswersGetReqDto;
import com.lawencon.lmssanto.dto.questionanswer.AnswersGetResDto;
import com.lawencon.lmssanto.dto.questiontype.QuestionTypeGetResDto;
import com.lawencon.lmssanto.dto.review.ReviewUpdateReqDto;
import com.lawencon.lmssanto.dto.review.ReviewsGetReqDto;
import com.lawencon.lmssanto.dto.review.ReviewsGetResDto;
import com.lawencon.lmssanto.dto.studentattendance.StudentAttUpdateReqDto;
import com.lawencon.lmssanto.dto.studentattendance.StudentAttsGetResDto;
import com.lawencon.lmssanto.dto.task.TaskInsertReqDto;
import com.lawencon.lmssanto.dto.task.TasksGetReqDto;
import com.lawencon.lmssanto.dto.task.TasksGetResDto;
import com.lawencon.lmssanto.model.ClassRoom;
import com.lawencon.lmssanto.model.Comment;
import com.lawencon.lmssanto.model.Elearning;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.Forum;
import com.lawencon.lmssanto.model.Material;
import com.lawencon.lmssanto.model.MaterialDetail;
import com.lawencon.lmssanto.model.Question;
import com.lawencon.lmssanto.model.QuestionAnswer;
import com.lawencon.lmssanto.model.QuestionAnswerDetail;
import com.lawencon.lmssanto.model.QuestionFile;
import com.lawencon.lmssanto.model.QuestionType;
import com.lawencon.lmssanto.model.Review;
import com.lawencon.lmssanto.model.StudentAttendance;
import com.lawencon.lmssanto.model.Task;
import com.lawencon.lmssanto.model.User;
import com.lawencon.lmssanto.service.PrincipalService;
import com.lawencon.lmssanto.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	private final ClassRoomDao classRoomDao;
	private final ElearningDao elearningDao;
	private final ForumDao forumDao;
	private final MaterialDao materialDao;
	private final MaterialDetailDao materialDetailDao;
	private final TaskDao taskDao;
	private final FileDao fileDao;
	private final QuestionTypeDao questionTypeDao;
	private final QuestionDao questionDao;
	private final QuestionFileDao questionFileDao;
	private final StudentAttendanceDao studentAttendanceDao;
	private final CommentDao commentDao;
	private final UserDao userDao;
	private final ReviewDao reviewDao;
	private final QuestionAnswerDetailDao questionAnswerDetailDao;
	private final QuestionAnswerDao questionAnswerDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public TeacherServiceImpl(ClassRoomDao classRoomDao, ElearningDao elearningDao, ForumDao forumDao,
			MaterialDao materialDao, MaterialDetailDao materialDetailDao, TaskDao taskDao, FileDao fileDao,
			QuestionTypeDao questionTypeDao, QuestionDao questionDao, QuestionFileDao questionFileDao,
			StudentAttendanceDao studentAttendanceDao, CommentDao commentDao, UserDao userDao, ReviewDao reviewDao,
			QuestionAnswerDetailDao questionAnswerDetailDao, QuestionAnswerDao questionAnswerDao,
			PrincipalService principalService) {
		this.classRoomDao = classRoomDao;
		this.elearningDao = elearningDao;
		this.forumDao = forumDao;
		this.materialDao = materialDao;
		this.materialDetailDao = materialDetailDao;
		this.taskDao = taskDao;
		this.fileDao = fileDao;
		this.questionTypeDao = questionTypeDao;
		this.questionDao = questionDao;
		this.questionFileDao = questionFileDao;
		this.studentAttendanceDao = studentAttendanceDao;
		this.commentDao = commentDao;
		this.userDao = userDao;
		this.reviewDao = reviewDao;
		this.questionAnswerDetailDao = questionAnswerDetailDao;
		this.questionAnswerDao = questionAnswerDao;
		this.principalService = principalService;
	}

	@Override
	public List<ClassRoomGetResDto> getClassRoom() {
		final Long person = principalService.getPrincipal();
		final User teacher = userDao.getById(person);

		final List<ClassRoom> classRooms = classRoomDao.getByTeacherId(teacher.getId());
		final List<ClassRoomGetResDto> classRoomsDto = new ArrayList<>();

		for (int i = 0; i < classRooms.size(); i++) {
			final ClassRoomGetResDto classRoom = new ClassRoomGetResDto();
			classRoom.setId(classRooms.get(i).getId());
			classRoom.setClassRoomName(classRooms.get(i).getClassroomName());
			classRoomsDto.add(classRoom);
		}

		return classRoomsDto;
	}

	@Override
	public InsertResDto createElearning(ElearningInsertReqDto elearningInsertReqDto) {
		final InsertResDto insertResDto = new InsertResDto();
		final Long person = principalService.getPrincipal();
		final User teacher = userDao.getById(person);
		final ClassRoom classRoom = classRoomDao.getById(elearningInsertReqDto.getClassRoomId());

		// Insert Elearning Photo
		File elearningPhoto = new File();
		elearningPhoto.setFileName(elearningInsertReqDto.getFileName());
		elearningPhoto.setExt(elearningInsertReqDto.getExt());
		elearningPhoto.setCreatedBy(teacher.getId());
		elearningPhoto = fileDao.insert(elearningPhoto);

		// Insert elearning
		Elearning elearning = new Elearning();
		elearning.setClassroom(classRoom);
		elearning.setElearningPhoto(elearningPhoto);
		elearning.setStartDate(elearningInsertReqDto.getStartDate());
		elearning.setEndDate(elearningInsertReqDto.getEndDate());
		elearning.setCreatedBy(teacher.getId());

		elearning = elearningDao.insert(elearning);

		Forum newForum = new Forum();
		newForum.setForumTitle(elearningInsertReqDto.getForumTitle());
		newForum.setForumCode(elearningInsertReqDto.getForumCode());
		newForum.setForumBody(elearningInsertReqDto.getForumBody());
		newForum = forumDao.insert(newForum);

		insertResDto.setId(elearning.getId());
		insertResDto.setMessage("Berhasil membuat elearning baru dengan forumnya");

		return insertResDto;
	}

	@Override
	public Forum createForum(Forum forum) {
		Forum forumDb = null;

		forumDb = forumDao.insert(forum);

		return forumDb;
	}

	@Override
	public InsertResDto createMaterial(MaterialsInsertReqDto materialsInsertReqDto) {
		final InsertResDto insertResDto = new InsertResDto();
		final Long person = principalService.getPrincipal();
		final User teacher = userDao.getById(person);
		final Elearning elearning = elearningDao.getById(materialsInsertReqDto.getElearningId());

		Material material = new Material();
		if (materialsInsertReqDto.getMaterialText() != null) {
			material.setMaterialText(materialsInsertReqDto.getMaterialText());
		}
		material.setElearning(elearning);
		material.setCreatedBy(teacher.getId());

		material = materialDao.insert(material);

		if (materialsInsertReqDto.getMaterialFiles().size() > 0) {
			final List<FilesInsertReqDto> materialFiles = materialsInsertReqDto.getMaterialFiles();
			for (int i = 0; i < materialFiles.size(); i++) {
				File materialFile = new File();
				materialFile.setFileName(materialFiles.get(i).getFileName());
				materialFile.setExt(materialFiles.get(i).getExt());
				materialFile.setCreatedBy(teacher.getId());

				materialFile = fileDao.insert(materialFile);

				final MaterialDetail materialDetail = new MaterialDetail();
				materialDetail.setMaterial(material);
				materialDetail.setMaterialFile(materialFile);
				materialDetail.setCreatedBy(teacher.getId());
				materialDetailDao.insert(materialDetail);

			}
		}

		insertResDto.setId(material.getId());
		insertResDto.setMessage("Berhasil membuat material");

		return insertResDto;
	}

	@Override
	public Boolean createMaterialDetail(MaterialDetail newMaterialDetail) {
		Boolean result = false;

		newMaterialDetail = materialDetailDao.insert(newMaterialDetail);

		return result;

	}

	@Override
	public InsertResDto createTask(TaskInsertReqDto taskInsertReqDto) {
		final InsertResDto insertResDto = new InsertResDto();
		final Long person = principalService.getPrincipal();
		final User teacher = userDao.getById(person);
		final Elearning elearning = elearningDao.getById(taskInsertReqDto.getElearningId());

		Task task = new Task();
		task.setTaskName(taskInsertReqDto.getTaskName());
		task.setElearning(elearning);
		task.setTaskStartTime(taskInsertReqDto.getTaskStartTime());
		task.setTaskEndTime(taskInsertReqDto.getTaskEndTime());
		task.setCreatedBy(teacher.getId());
		task = taskDao.insert(task);

		final List<QuestionInsertReqDto> questions = taskInsertReqDto.getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			Question question = new Question();
			final QuestionType questionType = questionTypeDao.getById(questions.get(i).getQuestionTypeId());
			question.setTask(task);
			question.setQuestionType(questionType);

			if (questions.get(i).getQuestionText() != null) {
				question.setQuestion(questions.get(i).getQuestionText());
			} else {
				question.setQuestion(null);

			}

			question = questionDao.insert(question);

			if (questionType.getQuestionTypeCode().equals(QuestionTypes.FILE.questionTypeCode)) {
				final List<FilesInsertReqDto> questionFiles = questions.get(i).getQuestionFiles();
				for (int j = 0; j < questionFiles.size(); j++) {
					File questionFile = new File();
					questionFile.setFileName(questionFiles.get(j).getFileName());
					questionFile.setExt(questionFiles.get(j).getExt());
					questionFile.setCreatedBy(teacher.getId());

					questionFile = fileDao.insert(questionFile);

					final QuestionFile newQuestionFile = new QuestionFile();
					newQuestionFile.setQuestion(question);
					newQuestionFile.setQuestionFile(questionFile);
					newQuestionFile.setCreatedBy(teacher.getId());
					questionFileDao.insert(newQuestionFile);
				}
			}

		}

		insertResDto.setMessage("Berhasil membuat task baru");
		return insertResDto;
	}

	@Override
	public File createFile(File file) {
		File newFile = null;

		newFile = fileDao.insert(file);

		return newFile;
	}

	@Override
	public List<QuestionTypeGetResDto> getAllQuestionTypes() {
		final List<QuestionType> questionTypes = questionTypeDao.getAll();
		final List<QuestionTypeGetResDto> questionTypesResDto = new ArrayList<>();
		for (int i = 0; i < questionTypes.size(); i++) {
			final QuestionTypeGetResDto questionType = new QuestionTypeGetResDto();
			questionType.setId(questionTypes.get(i).getId());
			questionType.setQuestionTypeName(questionTypes.get(i).getQuestionTypeName());
			questionTypesResDto.add(questionType);
		}
		return questionTypesResDto;
	}

	@Override
	public Question createQuestion(Question newQuestion) {
		Question question = null;

		question = questionDao.insert(newQuestion);

		return question;
	}

	@Override
	public Boolean createQuestionFile(QuestionFile questionFile) {
		Boolean result = false;

		questionFile = questionFileDao.insert(questionFile);
		return result;
	}

	@Override
	public Boolean updateQuestion(Question updatedQuestion) {
		Boolean result = false;

		final Question questionDb = questionDao.getById(updatedQuestion.getId());

		questionDb.setQuestion(updatedQuestion.getQuestion());
		questionDb.setUpdatedBy(updatedQuestion.getUpdatedBy());
		questionDb.setUpdatedAt(updatedQuestion.getUpdatedAt());
		questionDb.setIsActive(updatedQuestion.getIsActive());
		questionDb.setVer(updatedQuestion.getVer());

		return result;
	}

	@Override
	public List<ElearningsGetResDto> getElearningsByClassRoom(Long classRoomId) {
		final List<Elearning> elearnings = elearningDao.getByClassRoom(classRoomId);
		final List<ElearningsGetResDto> elearningResDto = new ArrayList<>();

		for (int i = 0; i < elearnings.size(); i++) {
			final ElearningsGetResDto elearning = new ElearningsGetResDto();
			elearning.setId(elearnings.get(i).getId());
			elearning.setElearningName(elearnings.get(i).getElearningName());
			elearning.setStartDate(elearnings.get(i).getStartDate());
			elearningResDto.add(elearning);
		}

		return elearningResDto;
	}

	@Override
	public List<StudentAttsGetResDto> getStudentAttendance(Long elearningId) {
		final List<StudentAttendance> studentAttendances = studentAttendanceDao.getAll(elearningId);
		final List<StudentAttsGetResDto> studentAttendancesResDto = new ArrayList<>();

		for (int i = 0; i < studentAttendances.size(); i++) {
			final StudentAttsGetResDto studentAttendance = new StudentAttsGetResDto();
			studentAttendance.setId(studentAttendances.get(i).getId());
			studentAttendance.setFullName(studentAttendances.get(i).getStudent().getProfile().getFullName());
			studentAttendance.setAttendTime(studentAttendances.get(i).getAttendTime());
			studentAttendance.setIsApproved(studentAttendances.get(i).getIsApproved());
			studentAttendancesResDto.add(studentAttendance);
		}

		return studentAttendancesResDto;
	}

	@Override
	public UpdateResDto updateStudentAttendance(StudentAttUpdateReqDto studentAttUpdateReqDto) {
		final UpdateResDto updateResDto = new UpdateResDto();
		final Long person = principalService.getPrincipal();
		final User teacher = userDao.getById(person);

		final StudentAttendance studentAttendanceDb = studentAttendanceDao
				.getById(studentAttUpdateReqDto.getStudentAttendanceId());
		studentAttendanceDb.setIsApproved(studentAttUpdateReqDto.getIsApproved());
		studentAttendanceDb.setUpdatedBy(teacher.getId());
		em.flush();

		updateResDto.setVer(studentAttendanceDb.getVer());
		updateResDto.setMessage("Berhasil update student attendance");

		return updateResDto;
	}

	@Override
	public ForumGetResDto getForumByElearning(ForumGetReqDto forumGetReqDto) {
		final Forum forum = forumDao.getByElearningId(forumGetReqDto.getElearningId());
		final List<Comment> comments = commentDao.getAll(forum.getId());

		final ForumGetResDto forumResDto = new ForumGetResDto();
		forumResDto.setForumTitle(forum.getForumTitle());
		forumResDto.setForumCode(forum.getForumCode());
		forumResDto.setForumBody(forum.getForumBody());

		final List<CommentsGetResDto> commentsResDto = new ArrayList<>();
		for (int i = 0; i < comments.size(); i++) {
			final CommentsGetResDto comment = new CommentsGetResDto();
			comment.setCreatedAt(comments.get(i).getCreatedAt());
			comment.setCommentator(comments.get(i).getUser().getProfile().getFullName());
			comment.setComment(comments.get(i).getUserComment());
			commentsResDto.add(comment);
		}
		forumResDto.setComments(commentsResDto);

		return forumResDto;
	}

	@Override
	public List<Comment> getForumComments(Long forumId) {
		final List<Comment> comments = commentDao.getAll(forumId);
		return comments;
	}

	@Override
	public User getById(User user) {
		final User getUser = userDao.getById(user.getId());
		return getUser;
	}

	@Override
	public InsertResDto createComment(CommentInsertReqDto commentInsertReqDto) {
		final InsertResDto insertResDto = new InsertResDto();

		final Long person = principalService.getPrincipal();
		final User commentator = userDao.getById(person);
		final LocalDateTime timeNow = LocalDateTime.now();
		final Forum forum = forumDao.getById(commentInsertReqDto.getForumId());

		Comment comment = new Comment();

		comment.setCreatedAt(timeNow);
		comment.setUser(commentator);
		comment.setForum(forum);

		comment = commentDao.insert(comment);

		insertResDto.setId(comment.getId());
		insertResDto.setMessage("Berhasil comment");

		return insertResDto;
	}

	@Override
	public List<TasksGetResDto> getTask(TasksGetReqDto tasksGetReqDto) {
		final List<Task> tasks = taskDao.getByElearningId(tasksGetReqDto.getElearningId());
		final List<TasksGetResDto> tasksResDto = new ArrayList<>();

		for (int i = 0; i < tasks.size(); i++) {
			final TasksGetResDto taskResDto = new TasksGetResDto();
			taskResDto.setId(tasks.get(i).getId());
			taskResDto.setTaskName(tasks.get(i).getTaskName());
			taskResDto.setTaskStartTime(tasks.get(i).getTaskStartTime());
			taskResDto.setTaskEndTime(tasks.get(i).getTaskEndTime());
			tasksResDto.add(taskResDto);
		}
		return tasksResDto;
	}

	@Override
	public List<Question> getQuestions(Task task) {
		final Question question = new Question();
		question.setTask(task);
		final List<Question> questions = questionDao.getByTaskId(question.getTask().getId());

		return questions;
	}

	@Override
	public List<ReviewsGetResDto> getReview(ReviewsGetReqDto reviewsGetReqDto) {
		final List<Review> getReviews = reviewDao.getByTaskId(reviewsGetReqDto.getTaskId());
		final List<ReviewsGetResDto> reviewsResDto = new ArrayList<>();

		for (int i = 0; i < getReviews.size(); i++) {
			final ReviewsGetResDto reviewResDto = new ReviewsGetResDto();
			reviewResDto.setId(getReviews.get(i).getId());
			reviewResDto.setFullName(getReviews.get(i).getStudent().getProfile().getFullName());
			reviewsResDto.add(reviewResDto);
		}

		return reviewsResDto;
	}

	@Override
	public List<QuestionFile> getQuestionFiles(Question question) {
		final QuestionFile questionFile = new QuestionFile();
		questionFile.setQuestion(question);
		final List<QuestionFile> questionFiles = questionFileDao.getByQuestionId(questionFile.getQuestion().getId());
		return questionFiles;
	}

	@Override
	public List<QuestionAnswerDetail> getAll(QuestionAnswer questionAnswer) {
		final QuestionAnswerDetail questionAnswerDetail = new QuestionAnswerDetail();
		questionAnswerDetail.setQuestionAnswer(questionAnswer);
		final List<QuestionAnswerDetail> questionAnswerDetails = questionAnswerDetailDao
				.getAllByQuestionAnswerId(questionAnswerDetail.getQuestionAnswer().getId());
		return questionAnswerDetails;
	}

	@Override
	public List<AnswersGetResDto> getQuestionAnswers(AnswersGetReqDto answersGetReqDto) {
		final Review review = reviewDao.getById(answersGetReqDto.getReviewId());
		final User student = userDao.getById(review.getStudent().getId());
		final List<Question> questions = questionDao.getByTaskId(review.getTask().getId());
		final List<AnswersGetResDto> answers = new ArrayList<>();
		for (int i = 0; i < questions.size(); i++) {
			final AnswersGetResDto answer = new AnswersGetResDto();
			if (questions.get(i).getQuestion() != null) {
				answer.setQuestionText(questions.get(i).getQuestion());
			} else {
				final List<QuestionFile> questionFiles = questionFileDao.getByQuestionId(questions.get(i).getId());
				final List<QuestionFilesResDto> questionFilesResDto = new ArrayList<>();

				for (int j = 0; j < questionFiles.size(); j++) {
					final QuestionFilesResDto questionFileResDto = new QuestionFilesResDto();
					questionFileResDto.setId(questionFiles.get(j).getId());
					questionFileResDto.setFileName(questionFiles.get(j).getQuestionFile().getFileName());
					questionFileResDto.setExt(questionFiles.get(j).getQuestionFile().getExt());
					questionFilesResDto.add(questionFileResDto);
				}

				answer.setQuestionFiles(questionFilesResDto);
			}

			final QuestionAnswer getQuestionAnswer = questionAnswerDao
					.getByQuestionIdAndCreatedBy(questions.get(i).getId(), student.getId());

			final AnswerGetResDto answerGetResDto = new AnswerGetResDto();
			if (getQuestionAnswer.getEssayAnswer() != null) {
				answerGetResDto.setEssayAnswer(getQuestionAnswer.getEssayAnswer());
			} else {
				final List<QuestionAnswerDetail> answerFiles = questionAnswerDetailDao
						.getAllByQuestionAnswerId(getQuestionAnswer.getId());
				final List<AnswerFilesGetResDto> answerFilesDto = new ArrayList<>();
				for (int j = 0; j < answerFiles.size(); j++) {
					final AnswerFilesGetResDto answerFileDto = new AnswerFilesGetResDto();
					answerFileDto.setId(answerFiles.get(j).getId());
					answerFileDto.setFileName(answerFiles.get(j).getQuestionAnswerFile().getFileName());
					answerFileDto.setExt(answerFiles.get(j).getQuestionAnswerFile().getExt());
					answerFilesDto.add(answerFileDto);
				}
			}

			answer.setAnswer(answerGetResDto);
			answers.add(answer);
		}

		return answers;

	}

	@Override
	public UpdateResDto updateReview(ReviewUpdateReqDto reviewUpdateReqDto) {
		final UpdateResDto updateResDto = new UpdateResDto();
		final Long person = principalService.getPrincipal();
		final User teacher = userDao.getById(person);
		
		final Review reviewDb = reviewDao.getById(reviewUpdateReqDto.getReviewId());
		reviewDb.setGrade(reviewUpdateReqDto.getGrade());
		reviewDb.setNotes(reviewUpdateReqDto.getNotes());
		reviewDb.setUpdatedBy(teacher.getId());
		em.flush();
		
		updateResDto.setVer(reviewDb.getVer());
		updateResDto.setMessage("Berhasil update review");

		return updateResDto;
	}

}
