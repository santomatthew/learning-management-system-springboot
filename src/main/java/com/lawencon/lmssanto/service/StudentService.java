package com.lawencon.lmssanto.service;

import java.util.List;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.classroom.AllClassRoomGetResDto;
import com.lawencon.lmssanto.dto.classroom.ClassRoomGetResDto;
import com.lawencon.lmssanto.dto.classroomenroll.ClassRoomEnrollInsertReqDto;
import com.lawencon.lmssanto.dto.classroomenroll.EnrollsGetResDto;
import com.lawencon.lmssanto.dto.comment.CommentInsertReqDto;
import com.lawencon.lmssanto.dto.comment.CommentsGetResDto;
import com.lawencon.lmssanto.dto.elearning.ElearningsGetResDto;
import com.lawencon.lmssanto.dto.forum.ForumGetReqDto;
import com.lawencon.lmssanto.dto.forum.ForumGetResDto;
import com.lawencon.lmssanto.dto.material.MaterialsGetReqDto;
import com.lawencon.lmssanto.dto.material.MaterialsGetResDto;
import com.lawencon.lmssanto.dto.question.QuestionsGetReqDto;
import com.lawencon.lmssanto.dto.question.QuestionsGetResDto;
import com.lawencon.lmssanto.dto.questionanswer.AnswersInsertReqDto;
import com.lawencon.lmssanto.dto.studentattendance.StudentAttInsertReqDto;
import com.lawencon.lmssanto.dto.task.TasksGetReqDto;
import com.lawencon.lmssanto.dto.task.TasksGetResDto;
import com.lawencon.lmssanto.model.ClassRoom;
import com.lawencon.lmssanto.model.ClassRoomEnroll;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.Material;
import com.lawencon.lmssanto.model.MaterialDetail;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.model.Question;
import com.lawencon.lmssanto.model.QuestionAnswer;
import com.lawencon.lmssanto.model.QuestionAnswerDetail;
import com.lawencon.lmssanto.model.QuestionFile;
import com.lawencon.lmssanto.model.Review;
import com.lawencon.lmssanto.model.StudentAttendance;
import com.lawencon.lmssanto.model.User;

public interface StudentService {

	List<EnrollsGetResDto> getClassRoomEnroll();

	List<AllClassRoomGetResDto> getClassRooms();

	Boolean checkClassRoom(ClassRoom classRoom, User student);

	InsertResDto createClassRoomEnroll(ClassRoomEnrollInsertReqDto classRoomEnrollInsertReqDto);

	ClassRoom getClassRoomsByClassRoomEnroll(ClassRoomEnroll classRoomEnroll);

	List<ElearningsGetResDto> getElearningsByClassRoom(Long classRoomId);

	InsertResDto createStudentAttendance(StudentAttInsertReqDto insertReqDto);

	StudentAttendance getStudentAttendance(Long studentId, Long elearningId);

	ForumGetResDto getForumByElearning(ForumGetReqDto forumGetReqDto) ;

	List<CommentsGetResDto> getForumComments(Long forumId);

	User getById(User user);

	InsertResDto createComment(CommentInsertReqDto commentInsertReqDto);
	
	List<MaterialsGetResDto> getAllMaterial(MaterialsGetReqDto materialsGetReqDto);

	List<MaterialDetail> getAllMaterialDetail(Material material);

	List<TasksGetResDto> getTask(TasksGetReqDto tasksGetReqDto);

	List<QuestionsGetResDto> getQuestions(QuestionsGetReqDto questionsGetReq);

	InsertResDto createAnswer(AnswersInsertReqDto answersInsertReqDto);

	QuestionAnswer getQuestionAnswers(QuestionAnswer questionAnswer);

	List<QuestionFile> getQuestionFiles(Question question);

	QuestionAnswerDetail createAnswerFile(QuestionAnswerDetail questionAnswerDetail);

	File createFile(File file);

	Review createReview(Review review);

	Profile getProfileById(Long profileId);
	
	List<ClassRoomGetResDto> getEnrolledClassRoom();
}
