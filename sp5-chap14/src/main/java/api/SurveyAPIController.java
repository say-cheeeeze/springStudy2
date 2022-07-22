package api;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import survey.SurveyAnswerInfo;
import survey.SurveyQuestionInfo;

/**
 * 설문 API 컨트롤러 객체
 * 
 * @author cheeeeze
 *
 */
@Controller
@RequestMapping( "/survey" )
public class SurveyAPIController {

	@GetMapping
	public ModelAndView form( Model model ) {
		
		List<SurveyQuestionInfo> questionList = makeQuestions();
		
		// model 과 view 를 같이 처리할 수 있다.
		ModelAndView mv = new ModelAndView();
		
		// model 에 값 추가
		mv.addObject( "questionList", questionList );
		
		// 반환할 View 객체 설정
		mv.setViewName( "survey/surveyForm" );
		
		return mv;
	}
	
	@PostMapping
	public String submit( @ModelAttribute( "answerInfo" ) SurveyAnswerInfo data ) {
		return "survey/submitted";
	}
	
	/**
	 * 기본문항을 설정합니다.
	 * @return
	 */
	private List<SurveyQuestionInfo> makeQuestions() {
		
		SurveyQuestionInfo question1 = new SurveyQuestionInfo( "당신의 역할은 무엇입니까", Arrays.asList( "서버", "풀스택", "프론트" ));
		
		SurveyQuestionInfo question2 = new SurveyQuestionInfo( "많이 사용하는 개발도구는?", Arrays.asList( "IntelliJ", "Eclipse", "Sublime" ));
		
		SurveyQuestionInfo question3 = new SurveyQuestionInfo( "하고 싶은 말을 적어주세요." );
		
		return Arrays.asList( question1, question2, question3 );
	}
	
}
