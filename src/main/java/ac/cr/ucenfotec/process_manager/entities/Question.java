package ac.cr.ucenfotec.process_manager.entities;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="questions")
public class Question {

	private String QuestionText;
	private String Type;
	private ArrayList<String> AnswersToSelect;
	private String UserAnswer;
	
	public String getQuestionText() {
		return QuestionText;
	}
	public void setQuestionText(String questionText) {
		QuestionText = questionText;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
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
