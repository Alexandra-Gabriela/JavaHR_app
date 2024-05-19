package org.oop.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static jakarta.persistence.TemporalType.DATE;
import java.util.Date;

@Entity @Setter
@Getter @NoArgsConstructor

public class CerereDemisie extends Cerere {
private Integer zilePreaviz;
    @Temporal(DATE)
    private Date dataIncetare;

}
