package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
import metamodel.AbstractEntity;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Concediu extends AbstractEntity {


    private String tip;




    @OneToOne(mappedBy = "cerere", cascade = CascadeType.ALL)
    private List<CerereConcediu> cereriConcediu;

}

