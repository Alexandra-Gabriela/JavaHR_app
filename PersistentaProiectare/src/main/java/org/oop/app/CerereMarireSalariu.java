package org.oop.app;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity @Setter
@Getter @NoArgsConstructor

public class CerereMarireSalariu extends Cerere {
    private Double contributii;
    private String motiv;
    private String performanta;


}
