package org.oop.app;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.TemporalType.DATE;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class PrezentaAngajat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NonNull
    @Id
    private Integer id;
    private String oraIntrare;
    private String oraIesire;
    private String MotivAbsenta;
    @Temporal(DATE)
    @NonNull private Date dataRaportata;

    public PrezentaAngajat(Integer id, String oraIntrare, String oraIesire, String motivAbsenta, Date dataRaportata) {
        this.id = id;
        this.oraIntrare = oraIntrare;
        this.oraIesire = oraIesire;
        MotivAbsenta = motivAbsenta;
        this.dataRaportata = dataRaportata;
    }

    @Override
    public String toString() {
        return "PrezentaAngajat{" +
                "id=" + id +
                ", oraIntrare='" + oraIntrare + '\'' +
                ", oraIesire='" + oraIesire + '\'' +
                ", MotivAbsenta='" + MotivAbsenta + '\'' +
                ", dataRaportata=" + dataRaportata +
                '}';
    }
    @ManyToOne
    @JoinColumn(name = "id_Angajat")
    private Angajat angajat;
}
