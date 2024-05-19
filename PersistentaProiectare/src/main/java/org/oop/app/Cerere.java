package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
import metamodel.AbstractEntity;

import java.util.Date;
import java.util.List;
import static jakarta.persistence.TemporalType.DATE;
@Entity
@Data

@RequiredArgsConstructor
public class Cerere extends AbstractEntity {


    @Temporal(DATE)
    private Date dataDepunere;
    @Enumerated(EnumType.STRING)
    private Status status;


@ManyToOne
@JoinColumn(name = "id_angajat")
private Angajat angajat;


    public enum Status {
        Aprobat,
        Neaprobat
    }
}
