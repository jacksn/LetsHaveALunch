package by.jackson.letshavealunch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class AppConfig {

    private LocalTime votingEndTime;

    public AppConfig(@Value("${votes.voting-end-time}") String time) {
        this.votingEndTime = LocalTime.parse(time);
    }

    public LocalTime getVotingEndTime() {
        return votingEndTime;
    }

    public void setVotingEndTime(LocalTime votingEndTime) {
        this.votingEndTime = votingEndTime;
    }


}
