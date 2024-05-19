package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
import metamodel.AbstractEntity;

import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Departament extends AbstractEntity {


    private String denumire;

    public Departament(Integer id, String denumire) {
        super(id);
        this.denumire = denumire;
    }

    @OneToMany (mappedBy = "departament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Angajat> AngajatiInDepartament;
    @OneToMany (mappedBy = "departament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pozitie> pozitii;


}
