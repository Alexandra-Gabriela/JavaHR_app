package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
import metamodel.AbstractEntity;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Pozitie extends AbstractEntity {

    private String titlu;
    private Stare stare;
    private String Descriere;

    public enum Stare{
        ocupata, vacanta
    }

    public Pozitie(Integer id, String titlu, Stare stare, String descriere) {
        this.id = id;
        this.titlu = titlu;
        this.stare = stare;
        Descriere = descriere;
    }
    public boolean esteVacanta() {
        return this.stare == Stare.vacanta;
    }
    public boolean esteOcupata() {
        return this.stare == Stare.ocupata;
    }

    @Override
    public String toString() {
        return "Pozitie{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", stare=" + stare +
                ", Descriere='" + Descriere + '\'' +
                '}';
    }
    @OneToMany (mappedBy = "pozitie", cascade = CascadeType.ALL)
    private List<Angajat> angajati;
    @ManyToOne
    @JoinColumn(name="id_departament")
    private Departament departament;
    @ManyToOne
    @JoinColumn(name="id_candidatura")
    private Candidatura candidatura;
}
