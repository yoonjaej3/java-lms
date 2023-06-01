package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;

public class Session {
    private String title;

    private Period period;

    private Capacity capacity;

    private CoverImage coverImage;

    private SessionType sessionType;

    private SessionStatus sessionStatus;

    private BaseEntity baseEntity;


    public Session(BaseEntity baseEntity, String title, Period period, Capacity capacity, CoverImage coverImage
            , SessionType sessionType, SessionStatus sessionStatus) {

        this.baseEntity = baseEntity;
        this.title = title;
        this.period = period;
        this.capacity = capacity;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;

    }

    public void apply() {
        if (this.sessionStatus == SessionStatus.RECRUTING) {
            capacity.add();
            return;
        }

        throw new IllegalArgumentException("모집 중일때만 수강신청이 가능합니다.");
    }

    public Capacity getCapacity() {
        return capacity;
    }

}
