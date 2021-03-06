package io.bet.betzilla.betcore;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

public class NonRunner {
    @Getter
    @Setter
    private Runner runner;

    @Getter
    @Setter
    private Date nonRunnerAt;

    public Long getNonRunnerAtTime() {
        return nonRunnerAt.getTime();
    }
}