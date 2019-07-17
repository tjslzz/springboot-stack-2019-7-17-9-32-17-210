package com.tw.apistackbase.entity;

import javax.persistence.*;

@Entity
public class Criminal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    //default length 255
    private String name;

    @Column(nullable = false)
    private Long time;

    public Criminal(String name, Long time) {
        this.name = name;
        this.time = time;
    }

    public Criminal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
