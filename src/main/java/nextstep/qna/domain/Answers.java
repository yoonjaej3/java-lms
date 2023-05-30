package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {

    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public DeleteHistories delete(String userId) throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();

        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(userId));
        }

        return deleteHistories;
    }
}
