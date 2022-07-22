package survey;

import java.util.List;

/**
 * 설문 응답 정보 객체
 * @author cheeeeze
 *
 */
public class SurveyAnswerInfo {
	
	private List<String> answerList;			// 설문 답변 목록
	private SurveyUserInfo surveyUserInfo;		// 설문 응답자 정보 

	public List<String> getAnswerList() {
		return answerList;
	}
	
	public SurveyUserInfo getSurveyUserInfo() {
		return surveyUserInfo;
	}
	
	public void setAnswerList( List<String> answerList ) {
		this.answerList = answerList;
	}
	
	public void setSurveyUserInfo( SurveyUserInfo surveyUserInfo ) {
		this.surveyUserInfo = surveyUserInfo;
	}
}
