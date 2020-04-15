package com.example.aq.Domain;

public class UserInfo {
    private int account;
    private String username;
    private String password;
    private String name;
    private int tea_account;
    private int total_submit;
    private int accepted;
    private float accuracy;
    private float level;
    private float score;

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTea_account() {
        return tea_account;
    }

    public void setTea_account(int tea_account) {
        this.tea_account = tea_account;
    }

    public int getTotal_submit() {
        return total_submit;
    }

    public void setTotal_submit(int total_submit) {
        this.total_submit = total_submit;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public UserInfo() {
    }

    public UserInfo(int account, String username, String password, String name, int tea_account, int total_submit, int accepted, float accuracy, float level, float score) {
        this.account = account;
        this.username = username;
        this.password = password;
        this.name = name;
        this.tea_account = tea_account;
        this.total_submit = total_submit;
        this.accepted = accepted;
        this.accuracy = accuracy;
        this.level = level;
        this.score = score;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"account\":")
                .append(account);
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"tea_account\":")
                .append(tea_account);
        sb.append(",\"total_submit\":")
                .append(total_submit);
        sb.append(",\"accepted\":")
                .append(accepted);
        sb.append(",\"accuracy\":")
                .append(accuracy);
        sb.append(",\"level\":")
                .append(level);
        sb.append(",\"score\":")
                .append(score);
        sb.append('}');
        return sb.toString();
    }
}
