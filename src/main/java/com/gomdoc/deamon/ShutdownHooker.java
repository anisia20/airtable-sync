package com.gomdoc.deamon;

import com.gomdoc.utils.SignalUtil;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class ShutdownHooker {
    DaemonG process = null;
    long max_waiting_time = 0L;

    public ShutdownHooker(DaemonG proc, int max_waiting_seconds) {
        this.process = proc;
        this.max_waiting_time = (long) (max_waiting_seconds * 1000);
        if (this.max_waiting_time < 0L) {
            this.max_waiting_time = 10000L;
        }
    }

    public void run() {
        try {
            log.debug(getClass().getSimpleName()+" running");

            long e = System.currentTimeMillis();
            this.process.nextStop();

            while (this.process != null && this.process.isFinish()==false) {
                SignalUtil.sleep(1000);
                if (System.currentTimeMillis() - e > this.max_waiting_time) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
