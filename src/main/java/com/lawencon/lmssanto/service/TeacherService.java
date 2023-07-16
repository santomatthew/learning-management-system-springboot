package com.lawencon.lmssanto.service;

import java.util.List;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.UpdateResDto;
import com.lawencon.lmssanto.dto.classroom.ClassRoomGetResDto;
import com.lawencon.lmssanto.dto.comment.CommentInsertReqDto;
import com.lawencon.lmssanto.dto.elearning.ElearningInsertReqDto;
import com.lawencon.lmssanto.dto.elearning.ElearningsGetResDto;
import com.lawencon.lmssanto.dto.forum.ForumGetReqDto;
import com.lawencon.lmssanto.dto.forum.ForumGetResDto;
import com.lawencon.lmssanto.dto.material.MaterialsInsertReqDto;
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
import com.lawencon.lmssanto.model.Comment;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.Forum;
import com.lawencon.lmssanto.model.MaterialDetail;
import com.lawencon.lmssanto.model.Question;
import com.lawencon.lmssanto.model.QuestionAnswer;
import com.lawencon.lmssanto.model.QuestionAnswerDetail;
import com.lawencon.lmssanto.model.QuestionFile;
import com.lawencon.lmssanto.model.Task;
import com.lawencon.lmssanto.model.User;

public interface TeacherService {

	List<ClassRoomGetResDto> getClassRoom();

	Forum createForum(Forum forum);

	InsertResDto createElearning(ElearningInsertReqDto elearningInsertReqDto);

	InsertResDto createMaterial(MaterialsInsertReqDto materialsInsertReqDto);

	Boolean createMaterialDetail(MaterialDetail newMaterialDetail);

	List<ElearningsGetResDto> getElearningsByClassRoom(Long classRoomId);

	File createFile(File file);

	InsertResDto createTask(TaskInsertReqDto taskInsertReqDto);

	List<QuestionTypeGetResDto> getAllQuestionTypes();

	Question createQuestion(Question newQuestion);

	Boolean updateQuestion(Question updatedQuestion);

	Boolean createQuestionFile(QuestionFile questionFile);

	List<StudentAttsGetResDto> getStudentAttendance(Long elearningId);

	UpdateResDto updateStudentAttendance(StudentAttUpdateReqDto studentAttUpdateReqDto);

	ForumGetResDto getForumByElearning(ForumGetReqDto forumGetReqDto);

	List<Comment> getForumComments(Long forumId);

	User getById(User user);

	InsertResDto createComment(CommentInsertReqDto commentInsertReqDto);

	List<TasksGetResDto> getTask(TasksGetReqDto tasksGetReqDto);

	List<Question> getQuestions(Task task);

	List<ReviewsGetResDto> getReview(ReviewsGetReqDto reviewsGetReqDto);

	List<QuestionFile> getQuestionFiles(Question question);

	List<QuestionAnswerDetail> getAll(QuestionAnswer questionAnswer);

	List<AnswersGetResDto> getQuestionAnswers(AnswersGetReqDto answersGetReqDto);

	UpdateResDto updateReview(ReviewUpdateReqDto reviewUpdateReqDto);

}
