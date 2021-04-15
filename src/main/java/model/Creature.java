package model;

/**
 * A base creature will have a given set of questions and answers
 * @author baoph
 *
 */
public class Creature {
	
	private String question;
	private String[] answers = new String[4];
	private int correctIdx;
	private boolean isAnsweredCorrectly;
	
	public Creature(String question, String a1, String a2, String a3, String a4, int correctIdx) {
		this.question = question;
		this.answers[0] = a1;
		this.answers[1] = a2;
		this.answers[2] = a3;
		this.answers[3] = a4;
		this.correctIdx = correctIdx;
		this.isAnsweredCorrectly = false;
	}
	
	
	public String getQuestion() {
		return this.question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getAnswer(int idx) {
		return this.answers[idx];
	}
	
	public int getCorrectAnswerIdx() {
		return this.correctIdx;
	}

	public boolean isAnsweredCorrectly() {
		return this.isAnsweredCorrectly;
	}

	public void setAnsweredCorrectly(boolean isAnsweredCorrectly) {
		this.isAnsweredCorrectly = isAnsweredCorrectly;
	}
	
	
	
}
