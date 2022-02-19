package br.com.compass.pb.avaliacao2.questao10.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tb_moods1")
public class EmployeeMood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Mood mood;
    private LocalDateTime instant;


    public EmployeeMood() {}

    public EmployeeMood(String name, Mood mood) {
        this.name = name;
        this.mood = mood;
        this.instant = LocalDateTime.now();
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

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    @Override
    public String toString() {
        String instant = this.instant.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return String.format("%s %s: %s", instant, this.name, this.mood.toString());
    }
}
