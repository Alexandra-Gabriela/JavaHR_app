package org.oop.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.TemporalType.DATE;

@Entity @Setter
@Getter @NoArgsConstructor

public class CerereInvoire extends Cerere {
    @Temporal(DATE)
    private Date data;
    private String motiv;
    private String suplinitor;


}
