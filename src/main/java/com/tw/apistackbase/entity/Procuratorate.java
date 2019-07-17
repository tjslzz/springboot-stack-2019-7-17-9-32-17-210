package com.tw.apistackbase.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Procuratorate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",length = 50,nullable = false)
    private String name;

    @OneToMany
    private List<Prosecutor> prosecutors;

    public Procuratorate() {
    }

    public Procuratorate(String name) {
        this.name = name;
    }

    public Procuratorate(String name, List<Prosecutor> prosecutors) {
        this.name = name;
        this.prosecutors = prosecutors;
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
        sb.append(",\"prosecutors\":")
                .append(prosecutors);
        sb.append('}');
        return sb.toString();
    }
}
