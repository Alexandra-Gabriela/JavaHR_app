package org.oop.app;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity @Setter
@Getter @NoArgsConstructor
public class Angajat extends Persoana {
    private Double salariu;
    private String detalii;


    public Angajat(Integer id, String nume, String adresa, String numarTelefon, String email, Date dataNastere, Double salariu, String detalii) {
        super(id, nume, adresa, numarTelefon, email, dataNastere);
        this.salariu = salariu;
        this.detalii = detalii;
    }
    @OneToMany(mappedBy = "angajat", cascade = CascadeType.ALL)
    private List<Cerere> cereri;

    @OneToMany(mappedBy = "angajat", cascade = CascadeType.ALL)
    private List<PrezentaAngajat> prezente;
@ManyToOne
    @JoinColumn(name="id_Departament")
    private  Departament departament;
    @ManyToOne
    @JoinColumn(name = "id_pozitie")
    private Pozitie pozitie;
    @ManyToOne
    @JoinColumn(name = "id_istoric")
    private IstoricAngajat istoricAngajat;
    @ManyToOne
    @JoinColumn(name = "id_evaluareAngajat")
    private EvaluareAngajat evaluareAngajat;
}
