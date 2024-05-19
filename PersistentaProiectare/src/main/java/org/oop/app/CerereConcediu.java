package org.oop.app;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.TemporalType.DATE;

@Entity @Setter
@Getter @NoArgsConstructor

public class CerereConcediu extends Cerere {
private TipConcediu tip;
    @Temporal(DATE)
    private Date dataInceput;
    @Temporal(DATE)
    private Date dataSfarsit;
    private String motiv;

    public enum TipConcediu{
    Medical, Maternitate, Odihna, Studiu, Plata, Fara_Plata
}


    @Override
    public String toString() {
        return "CerereConcediu{" +
                "tip=" + tip +
                ", dataInceput=" + dataInceput +
                ", dataSfarsit=" + dataSfarsit +
                ", motiv='" + motiv + '\'' +
                ", concediu=" + concediu +
                '}';
    }

    @OneToOne
    @JoinColumn(name = "id_concediu")
    private Concediu concediu ;
}
