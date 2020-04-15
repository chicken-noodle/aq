package com.example.aq.Domain;

public class Problem {
    private int problem_id;
    private float difficulty_level;
    private float knowledge_level;
    private String title;
    private String description;

    public Problem() {
    }

    public int getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
    }

    public float getDifficulty_level() {
        return difficulty_level;
    }

    public void setDifficulty_level(float difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public float getKnowledge_level() {
        return knowledge_level;
    }

    public void setKnowledge_level(float knowledge_level) {
        this.knowledge_level = knowledge_level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
