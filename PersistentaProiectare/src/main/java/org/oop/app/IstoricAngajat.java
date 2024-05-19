package org.oop.app;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.TemporalType.DATE;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class IstoricAngajat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NonNull
    @Id
    private Integer id;
    @NonNull private String nume;
    @Temporal(DATE)
    private Date dataAngajare;
    @Temporal(DATE)
    private Date dataIncetare;
    private String pozitiiOcupate;
    @OneToMany(mappedBy = "istoricAngajat", cascade = CascadeType.ALL)
    private List<Angajat> angajati;

    public IstoricAngajat(Integer id, String nume, Date dataAngajare, Date dataIncetare) {
        this.id = id;
        this.nume = nume;
        this.dataAngajare = dataAngajare;
        this.dataIncetare = dataIncetare;

    }

    @Override
    public String toString() {
        return "IstoricAngajat{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", dataAngajare=" + dataAngajare +
                ", dataIncetare=" + dataIncetare +
                '}';
    }
}
