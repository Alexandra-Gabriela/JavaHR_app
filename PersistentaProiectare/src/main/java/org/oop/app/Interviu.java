package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
import metamodel.AbstractEntity;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.TemporalType.DATE;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Interviu extends AbstractEntity {

    @Temporal(DATE)
    private Date dataInterviu;
    private Rezultat rezultat;

    public enum Rezultat{
        admis, respins
    }

    public Interviu(Integer id, Date dataInterviu, Rezultat rezultat) {
        this.id = id;
        this.dataInterviu = dataInterviu;
        this.rezultat = rezultat;
    }

    @Override
    public String toString() {
        return "Interviu{" +
                "id=" + id +
                ", dataInterviu=" + dataInterviu +
                ", rezultat=" + rezultat +
                '}';
    }
    @OneToMany(mappedBy = "interviu", cascade = CascadeType.ALL)
    private List<Candidatura> candidaturi;
}
