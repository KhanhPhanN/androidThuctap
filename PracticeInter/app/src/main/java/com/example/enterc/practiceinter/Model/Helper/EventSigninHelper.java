package com.example.enterc.practiceinter.Model.Helper;

public class EventSigninHelper {
    private StateChange stateChange;

    public EventSigninHelper(StateChange stateChange) {
        this.stateChange = stateChange;
    }

    public StateChange getStateChange() {
        return stateChange;
    }

    public enum StateChange {
        SIGNIN, REGISTER, SIGNOUT , LIKE, PREMIUM
    }
}
