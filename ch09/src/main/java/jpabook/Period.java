package jpabook;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

// 예제 9.3 값 타입 적용 회원 엔티티
@Embeddable
public class Period {

    @Temporal(TemporalType.DATE)
    LocalDateTime startDate;

    @Temporal(TemporalType.DATE)
    LocalDateTime endDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isWork(LocalDateTime date) {
        // 값 타입을 위한 메소드 정의
        if(date.isAfter(startDate) && date.isBefore(endDate))
            return true;
        else
            return false;
    }
}
