package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
import metamodel.AbstractEntity;

import java.util.Date;
import java.util.List;
import static jakarta.persistence.TemporalType.DATE;
@Entity @Data
 @RequiredArgsConstructor
public class Persoana extends AbstractEntity {

     private String nume;
    private String adresa;
    private String numarTelefon;
    private String email;
    @Temporal(DATE)
    private Date dataNastere;

    public Persoana( Integer id, String nume, String adresa, String numarTelefon, String email, Date dataNastere) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.numarTelefon = numarTelefon;
        this.email = email;
        this.dataNastere = dataNastere;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", numarTelefon='" + numarTelefon + '\'' +
                ", email='" + email + '\'' +
                ", dataNastere=" + dataNastere +
                '}';
    }
}
