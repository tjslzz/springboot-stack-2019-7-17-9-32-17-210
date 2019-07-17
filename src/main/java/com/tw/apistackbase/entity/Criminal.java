package com.tw.apistackbase.entity;

import javax.persistence.*;

@Entity
public class Criminal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    //default length 255
    private String name;

    @Column(nullable = false)
    private Long time;

    @OneToOne
    private CriminalSpecific criminalSpecific;

    @OneToOne
    private Procuratorate procuratorate;

    public Criminal(String name, Long time, CriminalSpecific criminalSpecific, Procuratorate procuratorate) {
        this.name = name;
        this.time = time;
        this.criminalSpecific = criminalSpecific;
        this.procuratorate = procuratorate;
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

    public CriminalSpecific getCriminalSpecific() {
        return criminalSpecific;
    }

    public void setCriminalSpecific(CriminalSpecific criminalSpecific) {
        this.criminalSpecific = criminalSpecific;
    }

    public Procuratorate getProcuratorate() {
        return procuratorate;
    }

    public void setProcuratorate(Procuratorate procuratorate) {
        this.procuratorate = procuratorate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"time\":")
                .append(time);
        sb.append(",\"criminalSpecific\":")
                .append(criminalSpecific);
        sb.append(",\"procuratorate\":")
                .append(procuratorate);
        sb.append('}');
        return sb.toString();
    }
}
