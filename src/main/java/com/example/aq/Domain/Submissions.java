package com.example.aq.Domain;

public class Submissions {
    private int uid;
    private int pid;
    private int times;
    private int status;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Submissions(int uid, int pid, int times, int status) {
        this.uid = uid;
        this.pid = pid;
        this.times = times;
        this.status = status;
    }

    public Submissions() {
    }
}
