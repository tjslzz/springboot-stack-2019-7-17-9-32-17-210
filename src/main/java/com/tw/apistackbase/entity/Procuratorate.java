package com.tw.apistackbase.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Procuratorate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",length = 50,nullable = false)
    private String name;

    public Procuratorate() {
    }

    public Procuratorate(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
