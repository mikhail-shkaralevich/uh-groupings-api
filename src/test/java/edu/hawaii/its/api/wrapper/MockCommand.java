package edu.hawaii.its.api.wrapper;

import java.util.Objects;

public class MockCommand extends GrouperCommand<MockCommand> implements Command<MockResults> {

    private String executionStatus = null;

    /**
     * Set the execution status for the command.
     * Either "SUCCESS" or "FAILURE".
     */
    public MockCommand setResultCode(String status) {
        this.executionStatus = status;
        return this;
    }

    @Override
    public MockCommand self() {
        return this;
    }

    @Override
    public MockResults execute() {
        return new MockResults(Objects.requireNonNullElse(executionStatus, "SUCCESS"));
    }

}
