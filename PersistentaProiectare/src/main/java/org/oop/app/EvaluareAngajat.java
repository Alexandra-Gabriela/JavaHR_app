package org.oop.app;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.TemporalType.DATE;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@RequiredArgsConstructor
public class EvaluareAngajat {
    @Id private Integer id;
    @Temporal(DATE)
    private Date dataEvaluare;
    private String rezultat;

    public EvaluareAngajat(Integer id, Date dataEvaluare, String rezultat) {
        this.id = id;
        this.dataEvaluare = dataEvaluare;
        this.rezultat = rezultat;
    }

    @Override
    public String toString() {
        return "EvaluareAngajat{" +
                "id=" + id +
                ", dataEvaluare=" + dataEvaluare +
                ", rezultat='" + rezultat + '\'' +
                '}';
    }
    @OneToMany(mappedBy = "evaluareAngajat", cascade = CascadeType.ALL)
    private List<Angajat> angajati;
    @OneToMany(mappedBy = "evaluareAngajat", cascade = CascadeType.ALL)
    private List<Feedback> feedback;
    @OneToMany(mappedBy = "evaluareAngajat", cascade = CascadeType.ALL)
    private List<CriteriuEvaluare> criteriiEvaluare;
}
