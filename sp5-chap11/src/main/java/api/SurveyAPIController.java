package api;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import survey.SurveyAnswerInfo;
import survey.SurveyQuestionInfo;

@Controller
@RequestMapping( "/survey" )
public class SurveyAPIController {

	@GetMapping
	public String form( Model model ) {
		
		List<SurveyQuestionInfo> questionList = makeQuestions();
		model.addAttribute( "questionList", questionList );
		
		return "survey/surveyForm";
	}
	
	@PostMapping
	public String submit( @ModelAttribute( "answerInfo" ) SurveyAnswerInfo data ) {
		return "survey/submitted";
	}
	
	private List<SurveyQuestionInfo> makeQuestions() {
		
		SurveyQuestionInfo question1 = new SurveyQuestionInfo( "당신의 역할은 무엇입니까", Arrays.asList( "서버", "풀스택", "프론트" ));
		
		SurveyQuestionInfo question2 = new SurveyQuestionInfo( "많이 사용하는 개발도구는?", Arrays.asList( "IntelliJ", "Eclipse", "Sublime" ));
		
		SurveyQuestionInfo question3 = new SurveyQuestionInfo( "하고 싶은 말을 적어주세요." );
		
		return Arrays.asList( question1, question2, question3 );
	}
	
}
