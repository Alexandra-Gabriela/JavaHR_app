package org.oop.app;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter @NoArgsConstructor
public class Candidat extends Persoana{
    public Candidat(Integer id, String nume, String adresa, String numarTelefon, String email, Date dataNastere) {
        super(id, nume, adresa, numarTelefon, email, dataNastere);
    }

    @ManyToOne
    @JoinColumn(name="id_Candidatura")
    private Candidatura candidatura;
}
