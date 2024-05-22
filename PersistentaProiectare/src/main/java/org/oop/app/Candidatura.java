package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
import metamodel.AbstractEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.TemporalType.DATE;

@Entity
@Data

@RequiredArgsConstructor
public class Candidatura extends AbstractEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private StatusAplicare status;
    private String CV;
    private String scrisoareIntentie;
    @Temporal(DATE)
    private Date dataAplicare;

    public enum StatusAplicare{
        in_curs, aprobata, respinsa
    }


    @OneToMany(mappedBy = "candidatura", cascade = CascadeType.ALL)
    private List<Candidat> candidatList = new ArrayList<>();;

    @OneToMany(mappedBy = "candidatura", cascade = CascadeType.ALL)
    private List<Pozitie> pozitii;


    @ManyToOne
    @JoinColumn(name="id_interviu")
    private Interviu interviu;

    public boolean esteInCurs() {
        return this.status == StatusAplicare.in_curs;
    }
}
