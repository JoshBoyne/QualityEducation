/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Josh
 */
public class QuestionRepository {
    private static QuestionRepository instance;
    private Map<String, List<Question>> topicQuestions;

    private QuestionRepository() {
        topicQuestions = new HashMap<>();
    }

    public static synchronized QuestionRepository getInstance() {
        if (instance == null) {
            instance = new QuestionRepository();
        }
        return instance;
    }

    public List<Question> getQuestionsByTopic(String topic) {
        if (topicQuestions.containsKey(topic)) {
            return topicQuestions.get(topic);
        }

       
        List<Question> questions = Question.getQuestionsByTopic(topic);
        topicQuestions.put(topic, questions);
        return questions;
    }

    public void updateQuestionsForTopic(String topic, List<Question> questions) {
        topicQuestions.put(topic, questions);
    }
}