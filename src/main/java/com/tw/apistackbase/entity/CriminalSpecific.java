package com.tw.apistackbase.entity;

import javax.persistence.*;

@Entity
public class CriminalSpecific {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String objective;

    @Column(nullable = false)
    private String subjective;

    public CriminalSpecific(String objective, String subjective) {
        this.objective = objective;
        this.subjective = subjective;
    }

    public CriminalSpecific() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getSubjective() {
        return subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"objective\":\"")
                .append(objective).append('\"');
        sb.append(",\"subjective\":\"")
                .append(subjective).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
