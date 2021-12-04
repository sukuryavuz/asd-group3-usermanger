package it.fh.campus;

import java.util.Timer;
import java.util.TimerTask;

public class CheckTimer {
    private Timer timer;
    public boolean isCheckValid = false;

    public CheckTimer(int seconds) {
        isCheckValid = true;
        startTimer(seconds);
    }

    private void startTimer(int seconds) {
        this.timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isCheckValid = false;
            }
        }, seconds * 1000L);
    }

    public void restartTimer(int seconds) {
        this.timer.cancel();
        isCheckValid = true;
        startTimer(seconds);
    }
}
