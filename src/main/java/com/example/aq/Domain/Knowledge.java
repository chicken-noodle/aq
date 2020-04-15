package com.example.aq.Domain;

public class Knowledge {
    private int knowledge_id;
    private String knowledge_name;
    private float knowledge_level;

    public Knowledge() {
    }

    public Knowledge(int knowledge_id, String knowledge_name, float knowledge_level) {
        this.knowledge_id = knowledge_id;
        this.knowledge_name = knowledge_name;
        this.knowledge_level = knowledge_level;
    }

    public int getKnowledge_id() {
        return knowledge_id;
    }

    public void setKnowledge_id(int knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public String getKnowledge_name() {
        return knowledge_name;
    }

    public void setKnowledge_name(String knowledge_name) {
        this.knowledge_name = knowledge_name;
    }

    public float getKnowledge_level() {
        return knowledge_level;
    }

    public void setKnowledge_level(float knowledge_level) {
        this.knowledge_level = knowledge_level;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "knowledge_id=" + knowledge_id +
                ", knowledge_name='" + knowledge_name + '\'' +
                ", knowledge_level=" + knowledge_level +
                '}';
    }
}
