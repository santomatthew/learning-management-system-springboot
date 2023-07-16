package com.lawencon.lmssanto.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.lmssanto.constant.QuestionTypes;
import com.lawencon.lmssanto.dao.ClassRoomDao;
import com.lawencon.lmssanto.dao.ClassRoomEnrollDao;
import com.lawencon.lmssanto.dao.CommentDao;
import com.lawencon.lmssanto.dao.ElearningDao;
import com.lawencon.lmssanto.dao.FileDao;
import com.lawencon.lmssanto.dao.ForumDao;
import com.lawencon.lmssanto.dao.MaterialDao;
import com.lawencon.lmssanto.dao.MaterialDetailDao;
import com.lawencon.lmssanto.dao.ProfileDao;
import com.lawencon.lmssanto.dao.QuestionAnswerDao;
import com.lawencon.lmssanto.dao.QuestionAnswerDetailDao;
import com.lawencon.lmssanto.dao.QuestionDao;
import com.lawencon.lmssanto.dao.QuestionFileDao;
import com.lawencon.lmssanto.dao.ReviewDao;
import com.lawencon.lmssanto.dao.StudentAttendanceDao;
import com.lawencon.lmssanto.dao.TaskDao;
import com.lawencon.lmssanto.dao.UserDao;
import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.classroom.AllClassRoomGetResDto;
import com.lawencon.lmssanto.dto.classroom.ClassRoomGetResDto;
import com.lawencon.lmssanto.dto.classroomenroll.ClassRoomEnrollInsertReqDto;
import com.lawencon.lmssanto.dto.classroomenroll.EnrollsGetResDto;
import com.lawencon.lmssanto.dto.comment.CommentInsertReqDto;
import com.lawencon.lmssanto.dto.comment.CommentsGetResDto;
import com.lawencon.lmssanto.dto.elearning.ElearningsGetResDto;
import com.lawencon.lmssanto.dto.file.FilesInsertReqDto;
import com.lawencon.lmssanto.dto.file.QuestionFilesResDto;
import com.lawencon.lmssanto.dto.forum.ForumGetReqDto;
import com.lawencon.lmssanto.dto.forum.ForumGetResDto;
import com.lawencon.lmssanto.dto.material.MaterialFilesGetResDto;
import com.lawencon.lmssanto.dto.material.MaterialsGetReqDto;
import com.lawencon.lmssanto.dto.material.MaterialsGetResDto;
import com.lawencon.lmssanto.dto.question.QuestionsGetReqDto;
import com.lawencon.lmssanto.dto.question.QuestionsGetResDto;
import com.lawencon.lmssanto.dto.questionanswer.AnswerInsertReqDto;
import com.lawencon.lmssanto.dto.questionanswer.AnswersInsertReqDto;
import com.lawencon.lmssanto.dto.studentattendance.StudentAttInsertReqDto;
import com.lawencon.lmssanto.dto.task.TasksGetReqDto;
import com.lawencon.lmssanto.dto.task.TasksGetResDto;
import com.lawencon.lmssanto.model.ClassRoom;
import com.lawencon.lmssanto.model.ClassRoomEnroll;
import com.lawencon.lmssanto.model.Comment;
import com.lawencon.lmssanto.model.Elearning;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.Forum;
import com.lawencon.lmssanto.model.Material;
import com.lawencon.lmssanto.model.MaterialDetail;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.model.Question;
import com.lawencon.lmssanto.model.QuestionAnswer;
import com.lawencon.lmssanto.model.QuestionAnswerDetail;
import com.lawencon.lmssanto.model.QuestionFile;
import com.lawencon.lmssanto.model.Review;
import com.lawencon.lmssanto.model.StudentAttendance;
import com.lawencon.lmssanto.model.Task;
import com.lawencon.lmssanto.model.User;
import com.lawencon.lmssanto.service.PrincipalService;
import com.lawencon.lmssanto.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private final ClassRoomEnrollDao classRoomEnrollDao;
	private final ClassRoomDao classRoomDao;
	private final ElearningDao elearningDao;
	private final StudentAttendanceDao studentAttendanceDao;
	private final ForumDao forumDao;
	private final CommentDao commentDao;
	private final UserDao userDao;
	private final MaterialDao materialDao;
	private final MaterialDetailDao materialDetailDao;
	private final TaskDao taskDao;
	private final QuestionDao questionDao;
	private final QuestionAnswerDao questionAnswerDao;
	private final QuestionFileDao questionFileDao;
	private final QuestionAnswerDetailDao questionAnswerDetailDao;
	private final FileDao fileDao;
	private final ReviewDao reviewDao;
	private final ProfileDao profileDao;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public StudentServiceImpl(ClassRoomEnrollDao classRoomEnrollDao, ClassRoomDao classRoomDao,
			ElearningDao elearningDao, StudentAttendanceDao studentAttendanceDao, ForumDao forumDao,
			CommentDao commentDao, UserDao userDao, MaterialDao materialDao, MaterialDetailDao materialDetailDao,
			TaskDao taskDao, QuestionDao questionDao, QuestionAnswerDao questionAnswerDao,
			QuestionFileDao questionFileDao, ProfileDao profileDao, QuestionAnswerDetailDao questionAnswerDetailDao,
			FileDao fileDao, ReviewDao reviewDao, PrincipalService principalService) {
		this.classRoomEnrollDao = classRoomEnrollDao;
		this.classRoomDao = classRoomDao;
		this.elearningDao = elearningDao;
		this.studentAttendanceDao = studentAttendanceDao;
		this.forumDao = forumDao;
		this.commentDao = commentDao;
		this.userDao = userDao;
		this.materialDao = materialDao;
		this.materialDetailDao = materialDetailDao;
		this.taskDao = taskDao;
		this.questionDao = questionDao;
		this.questionAnswerDao = questionAnswerDao;
		this.questionFileDao = questionFileDao;
		this.questionAnswerDetailDao = questionAnswerDetailDao;
		this.fileDao = fileDao;
		this.reviewDao = reviewDao;
		this.profileDao = profileDao;
		this.principalService = principalService;
	}

	@Override
	public List<EnrollsGetResDto> getClassRoomEnroll() {

		final Long person = principalService.getPrincipal();
		final User student = userDao.getById(person);
		final ClassRoomEnroll classRoomEnroll = new ClassRoomEnroll();
		classRoomEnroll.setStudent(student);

		final List<ClassRoomEnroll> classRoomEnrolls = classRoomEnrollDao.getAllByStudentId(classRoomEnroll);
		final List<EnrollsGetResDto> enrolledClassrooms = new ArrayList<>();

		for (int i = 0; i < classRoomEnrolls.size(); i++) {
			final EnrollsGetResDto enrolledClassroom = new EnrollsGetResDto();
			enrolledClassroom.setId(classRoomEnrolls.get(i).getId());
			enrolledClassroom.setClassRoomName(classRoomEnrolls.get(i).getClassroom().getClassroomName());
			enrolledClassrooms.add(enrolledClassroom);
		}

		return enrolledClassrooms;
	}

	@Override
	public List<AllClassRoomGetResDto> getClassRooms() {
		final List<ClassRoom> classRooms = classRoomDao.getAll();
		final List<AllClassRoomGetResDto> classRoomsResDto = new ArrayList<>();

		for (int i = 0; i < classRooms.size(); i++) {
			final AllClassRoomGetResDto classRoom = new AllClassRoomGetResDto();
			classRoom.setId(classRooms.get(i).getId());
			classRoom.setClassRoomName(classRooms.get(i).getClassroomName());
			classRoom.setClassRoomCode(classRooms.get(i).getClassroomCode());
			classRoomsResDto.add(classRoom);
		}

		return classRoomsResDto;
	}

	@Override
	public Boolean checkClassRoom(ClassRoom classRoom, User student) {
		Boolean result = false;
		final ClassRoomEnroll classRoomEnroll = new ClassRoomEnroll();
		classRoomEnroll.setStudent(student);
		final List<ClassRoomEnroll> classRoomEnrolls = classRoomEnrollDao.getAllByStudentId(classRoomEnroll);

		for (int i = 0; i < classRoomEnrolls.size(); i++) {
			if (classRoomEnrolls.get(i).getClassroom().getId() == classRoom.getId()) {
				result = true;
			}
		}

		return result;

	}

	@Transactional
	@Override
	public InsertResDto createClassRoomEnroll(ClassRoomEnrollInsertReqDto classRoomEnrollInsertReqDto) {
		final InsertResDto insertResDto = new InsertResDto();
		final Long person = principalService.getPrincipal();
		final User student = userDao.getById(person);

		ClassRoomEnroll newClassRoomEnroll = new ClassRoomEnroll();
		final ClassRoom classRoom = classRoomDao.getById(classRoomEnrollInsertReqDto.getClassRoomId());
		newClassRoomEnroll.setClassroom(classRoom);
		newClassRoomEnroll.setStudent(student);
		newClassRoomEnroll.setCreatedBy(student.getId());

		newClassRoomEnroll = classRoomEnrollDao.insert(newClassRoomEnroll);

		insertResDto.setId(newClassRoomEnroll.getId());
		insertResDto.setMessage("Berhasil enroll ke Class Room " + classRoom.getClassroomName());

		return insertResDto;
	}

	@Override
	public ClassRoom getClassRoomsByClassRoomEnroll(ClassRoomEnroll classRoomEnroll) {
		final ClassRoom classRoom = classRoomDao.getById(classRoomEnroll.getClassroom().getId());
		return classRoom;
	}

	@Override
	public List<ElearningsGetResDto> getElearningsByClassRoom(Long classRoomId) {
		final List<Elearning> elearnings = elearningDao.getByClassRoom(classRoomId);
		final List<ElearningsGetResDto> elearningsGetResDto = new ArrayList<>();

		for (int i = 0; i < elearnings.size(); i++) {
			final ElearningsGetResDto elearningGetResDto = new ElearningsGetResDto();
			elearningGetResDto.setId(elearnings.get(i).getId());
			elearningGetResDto.setElearningName(elearnings.get(i).getElearningName());
			elearningGetResDto.setStartDate(elearnings.get(i).getStartDate());
			elearningsGetResDto.add(elearningGetResDto);
		}
		return elearningsGetResDto;
	}

	@Transactional
	@Override
	public InsertResDto createStudentAttendance(StudentAttInsertReqDto insertReqDto) {

		final InsertResDto insertResDto = new InsertResDto();
		final Long person = principalService.getPrincipal();
		final User student = userDao.getById(person);

		final Elearning elearning = elearningDao.getById(insertReqDto.getElearningId());
		final LocalDateTime timeNow = LocalDateTime.now();

		StudentAttendance newStudentAttendance = new StudentAttendance();
		newStudentAttendance.setStudent(student);
		newStudentAttendance.setElearning(elearning);
		newStudentAttendance.setAttendTime(timeNow);
		newStudentAttendance.setIsApproved(false);
		newStudentAttendance.setCreatedBy(student.getId());
		newStudentAttendance = studentAttendanceDao.insert(newStudentAttendance);

		insertResDto.setId(newStudentAttendance.getId());
		insertResDto.setMessage("Anda berhasil absen di Elearning " + elearning.getElearningName());

		return insertResDto;
	}

	@Override
	public StudentAttendance getStudentAttendance(Long studentId, Long elearningId) {
		final StudentAttendance getStudentAttendance = studentAttendanceDao.get(studentId, elearningId);
		return getStudentAttendance;
	}

	@Override
	public ForumGetResDto getForumByElearning(ForumGetReqDto forumGetReqDto) {
		final Forum forum = forumDao.getByElearningId(forumGetReqDto.getElearningId());
		final List<Comment> comments = commentDao.getAll(forum.getId());

		final ForumGetResDto forumResDto = new ForumGetResDto();
		forumResDto.setId(forum.getId());
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
	public List<CommentsGetResDto> getForumComments(Long forumId) {
		final List<Comment> comments = commentDao.getAll(forumId);
		final List<CommentsGetResDto> commentsResDto = new ArrayList<>();

		for (int i = 0; i < comments.size(); i++) {
			final CommentsGetResDto commentResDto = new CommentsGetResDto();
			commentResDto.setCreatedAt(comments.get(i).getCreatedAt());
			final User commentator = userDao.getById(comments.get(i).getUser().getId());
			commentResDto.setCommentator(commentator.getProfile().getFullName());
			commentResDto.setComment(comments.get(i).getUserComment());
			commentsResDto.add(commentResDto);
		}

		return commentsResDto;
	}

	@Override
	public User getById(User user) {
		final User getUser = userDao.getById(user.getId());
		return getUser;
	}

	@Transactional
	@Override
	public InsertResDto createComment(CommentInsertReqDto commentInsertReqDto) {
		final InsertResDto insertResDto = new InsertResDto();

		final Long person = principalService.getPrincipal();
		final User commentator = userDao.getById(person);
		final Forum forum = forumDao.getById(commentInsertReqDto.getForumId());

		Comment comment = new Comment();

		comment.setUser(commentator);
		comment.setUserComment(commentInsertReqDto.getComment());
		comment.setForum(forum);
		comment.setCreatedBy(commentator.getId());
		comment = commentDao.insert(comment);

		insertResDto.setId(comment.getId());
		insertResDto.setMessage("Berhasil comment");

		return insertResDto;
	}

	@Override
	public List<MaterialsGetResDto> getAllMaterial(MaterialsGetReqDto materialsGetReqDto) {
		final List<Material> materials = materialDao.getAll(materialsGetReqDto.getElearningId());
		final List<MaterialsGetResDto> materialsDto = new ArrayList<>();

		for (int i = 0; i < materials.size(); i++) {
			final MaterialsGetResDto materialDto = new MaterialsGetResDto();
			if (materials.get(i).getMaterialText() != null) {
				materialDto.setMaterialText(materials.get(i).getMaterialText());
			}
			final List<MaterialDetail> materialDetails = materialDetailDao.getAll(materials.get(i).getId());
			if (materialDetails.size() > 0) {
				final List<MaterialFilesGetResDto> materialFilesDto = new ArrayList<>();
				for (int j = 0; j < materialDetails.size(); j++) {
					final MaterialFilesGetResDto materialFileDto = new MaterialFilesGetResDto();
					materialFileDto.setId(materialDetails.get(j).getId());
					materialFileDto.setFileName(materialDetails.get(j).getMaterialFile().getFileName());
					materialFileDto.setExt(materialDetails.get(j).getMaterialFile().getExt());
					materialFilesDto.add(materialFileDto);
				}

				materialDto.setMaterialFiles(materialFilesDto);
			} else {
				materialDto.setMaterialFiles(null);
			}

			materialsDto.add(materialDto);
		}

		return materialsDto;
	}

	@Override
	public List<MaterialDetail> getAllMaterialDetail(Material material) {
		final MaterialDetail materialDetail = new MaterialDetail();
		materialDetail.setMaterial(material);
		final List<MaterialDetail> materialDetails = materialDetailDao.getAll(materialDetail.getId());
		return materialDetails;
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
	public List<QuestionsGetResDto> getQuestions(QuestionsGetReqDto questionsGetReq) {
		final List<Question> questions = questionDao.getByTaskId(questionsGetReq.getTaskId());
		final List<QuestionsGetResDto> questionsResDto = new ArrayList<>();

		for (int i = 0; i < questions.size(); i++) {
			final QuestionsGetResDto questionResDto = new QuestionsGetResDto();

			questionResDto.setTeacherId(questions.get(i).getCreatedBy());
			questionResDto.setQuestionId(questions.get(i).getId());
			if (questions.get(i).getQuestionType().getQuestionTypeCode().equals(QuestionTypes.ESSAY.questionTypeCode)) {
				questionResDto.setQuestionText(questions.get(i).getQuestion());
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
				questionResDto.setQuestionFiles(questionFilesResDto);
			}
			
			questionsResDto.add(questionResDto);
		}

		return questionsResDto;
	}

	@Transactional
	@Override
	public InsertResDto createAnswer(AnswersInsertReqDto answersInsertReqDto) {
		final InsertResDto insertResDto = new InsertResDto();
		final Long person = principalService.getPrincipal();
		final User student = userDao.getById(person);
		final User teacher = userDao.getById(answersInsertReqDto.getTeacherId());
		final Task task = taskDao.getById(answersInsertReqDto.getTaskId());

		// Create Review
		Review review = new Review();
		review.setTeacher(teacher);
		review.setStudent(student);
		review.setTask(task);
		review.setCreatedBy(student.getId());
		review = reviewDao.insert(review);

		final List<AnswerInsertReqDto> answers = answersInsertReqDto.getAnswers();

		// Insert Answer
		for (int i = 0; i < answers.size(); i++) {
			final Question question = questionDao.getById(answers.get(i).getQuestionId());

			QuestionAnswer questionAnswer = new QuestionAnswer();
			questionAnswer.setQuestion(question);
			questionAnswer.setCreatedBy(student.getId());

			if (answers.get(i).getEssayAnswer() != null) {
				questionAnswer.setEssayAnswer(answers.get(i).getEssayAnswer());
			}

			questionAnswer = questionAnswerDao.insert(questionAnswer);

			if (question.getQuestionType().getQuestionTypeCode().equals(QuestionTypes.FILE.questionTypeCode)) {
				final List<FilesInsertReqDto> answerFiles = answers.get(i).getAnswerFiles();
				for (int j = 0; j < answerFiles.size(); j++) {

					File answerFile = new File();
					answerFile.setFileName(answerFiles.get(j).getFileName());
					answerFile.setExt(answerFiles.get(j).getExt());
					answerFile.setCreatedBy(student.getId());
					answerFile = fileDao.insert(answerFile);

					QuestionAnswerDetail answerDetail = new QuestionAnswerDetail();
					answerDetail.setQuestionAnswer(questionAnswer);
					answerDetail.setQuestionAnswerFile(answerFile);
					answerDetail.setCreatedBy(student.getId());
					questionAnswerDetailDao.insert(answerDetail);
				}
			}
		}

		insertResDto.setMessage("Berhasil menjawab soal dari " + task.getTaskName());

		return insertResDto;
	}

	@Override
	public QuestionAnswer getQuestionAnswers(QuestionAnswer questionAnswer) {
		final QuestionAnswer getQuestionAnswer = questionAnswerDao
				.getByQuestionIdAndCreatedBy(questionAnswer.getQuestion().getId(), questionAnswer.getCreatedBy());
		return getQuestionAnswer;
	}

	@Override
	public List<QuestionFile> getQuestionFiles(Question question) {
		final QuestionFile questionFile = new QuestionFile();
		questionFile.setQuestion(question);
		final List<QuestionFile> questionFiles = questionFileDao.getByQuestionId(questionFile.getQuestion().getId());
		return questionFiles;
	}

	@Override
	public QuestionAnswerDetail createAnswerFile(QuestionAnswerDetail questionAnswerDetail) {
		QuestionAnswerDetail questionAnswerDetailDb = null;

		questionAnswerDetailDb = questionAnswerDetailDao.insert(questionAnswerDetail);

		return questionAnswerDetailDb;
	}

	@Override
	public File createFile(File file) {
		File newFile = null;

		newFile = fileDao.insert(file);

		return newFile;
	}

	@Override
	public Review createReview(Review review) {
		Review newReview = null;

		newReview = reviewDao.insert(review);

		return newReview;
	}

	@Override
	public Profile getProfileById(Long profileId) {
		final Profile getProfile = profileDao.getById(profileId);
		return getProfile;
	}

	@Override
	public List<ClassRoomGetResDto> getEnrolledClassRoom() {
		final Long person = principalService.getPrincipal();
		final User student = userDao.getById(person);
		final List<ClassRoom> enrolledClassRooms = classRoomDao.getByClassRoomEnroll(student.getId());

		final List<ClassRoomGetResDto> enrolledClassRoomsResDto = new ArrayList<>();

		for (int i = 0; i < enrolledClassRooms.size(); i++) {
			final ClassRoomGetResDto enrolledClassRoom = new ClassRoomGetResDto();
			enrolledClassRoom.setId(enrolledClassRooms.get(i).getId());
			enrolledClassRoom.setClassRoomName(enrolledClassRooms.get(i).getClassroomName());
			enrolledClassRoomsResDto.add(enrolledClassRoom);
		}

		return enrolledClassRoomsResDto;
	}

}
