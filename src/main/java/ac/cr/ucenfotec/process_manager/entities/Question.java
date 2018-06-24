package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Document(collection ="questions")
public class Question {

	@NotNull @NotBlank @NotEmpty
	private String questionText;
	@NotNull @NotBlank @NotEmpty
	private String type;
	@NotNull
	private ArrayList<String> AnswersToSelect;
	private String UserAnswer;
	
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<String> getAnswersToSelect() {
		return AnswersToSelect;
	}
	public void setAnswersToSelect(ArrayList<String> answersToSelect) {
		AnswersToSelect = answersToSelect;
	}
	public String getUserAnswer() {
		return UserAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		UserAnswer = userAnswer;
	}
	
	

}
