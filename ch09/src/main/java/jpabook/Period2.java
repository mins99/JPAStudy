package jpabook;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

// 예제 9.6 임베디드 타입과 연관관계
@Embeddable
public class Period2 {

    LocalDateTime startDate;

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
