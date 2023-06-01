package nextstep.courses.domain;

public class Period {
    private String startDate;

    private String endDate;

    public Period(String startDate, String endDate) {
        validDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    private static void validDate(String startDate, String endDate) {
        if (startDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException("종료날짜는 시작날짜보다 작을 수 없습니다.");
        }
    }

}
