package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories() {

    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    public DeleteHistories of(DeleteHistory deleteHistory, DeleteHistories deleteHistoriesAnswer) {

        DeleteHistories histories = new DeleteHistories();
        histories.add(deleteHistory);
        histories.addAll(deleteHistoriesAnswer);

        return histories;
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public void addAll(DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.getDeleteHistories());
    }


}
