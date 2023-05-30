package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final NsUser loginUser01 = NsUserTest.JAVAJIGI;

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우, 질문 삭제가 가능하다.")
    void delete01() {
        //given when
        assertDoesNotThrow(() -> {
            Q1.delete(loginUser01.getUserId());
        });

        //then
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("답변이 없는 경우 삭제가 가능하다.")
    void delete02() {
        //given when
        assertDoesNotThrow(() -> {
            Q1.delete(loginUser01.getUserId());
        });

        //then
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("답변이 있는 경우, 질문자와 답변자가 같은 경우 삭제가 가능하다.")
    void delete03() {
        //given when
        assertDoesNotThrow(() -> {
            Q1.addAnswer(A1);
            Q1.delete(A1.getWriter().getUserId());
        });

        //then
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("답변이 있는 경우, 질문자와 답변자가 다른 경우 삭제가 불가능하다.")
    void delete04() {
        CannotDeleteException exception = assertThrows(CannotDeleteException.class, () -> {
            Q1.addAnswer(A2);
            Q1.delete(A1.getWriter().getUserId());
        });

        String expected = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
        String actual = exception.getMessage();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("질문과 답변 삭제시, 이력에 대한 정보를 남긴다.")
    void delete05() throws CannotDeleteException {
        // given
        DeleteHistories deleteHistories;

        // when
        Q1.addAnswer(A1);
        deleteHistories = Q1.delete(loginUser01.getUserId());

        // then
        Assertions.assertThat(deleteHistories.getDeleteHistories()).hasSize(2);
    }
    
}
