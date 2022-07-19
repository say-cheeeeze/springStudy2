package survey;

import java.util.Collections;
import java.util.List;

public class SurveyQuestionInfo {

	private String questionTitle;
	private List<String> questionOptions;
	
	public SurveyQuestionInfo( String questionTitle, List<String> questionOptions ) {
		this.questionTitle = questionTitle;
		this.questionOptions = questionOptions;
	}
	
	public SurveyQuestionInfo( String questionTitle ) {
		this( questionTitle, Collections.emptyList() );
	}
	
	public String getQuestionTitle() {
		return questionTitle;
	}
	
	public List<String> getQuestionOptions() {
		return questionOptions;
	}
	
	public void setQuestionTitle( String questionTitle ) {
		this.questionTitle = questionTitle;
	}
	public void setQuestionOptions( List<String> questionOptions ) {
		this.questionOptions = questionOptions;
	}
	
	public boolean isChoice() {
		return questionOptions != null && !questionOptions.isEmpty();
	}
	
	
}
