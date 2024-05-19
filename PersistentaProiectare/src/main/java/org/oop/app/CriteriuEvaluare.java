package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class CriteriuEvaluare {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NonNull
    @Id private Integer id;
    private String denumireCriteriu;
    private String descriere;
    private Double nota;

    public CriteriuEvaluare(Integer id, String denumireCriteriu, String descriere, Double nota) {
        this.id = id;
        this.denumireCriteriu = denumireCriteriu;
        this.descriere = descriere;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "CriteriuEvaluare{" +
                "id=" + id +
                ", denumireCriteriu='" + denumireCriteriu + '\'' +
                ", descriere='" + descriere + '\'' +
                ", nota=" + nota +
                '}';
    }
    @ManyToOne
    @JoinColumn(name = "id_Evaluare")
    private EvaluareAngajat evaluareAngajat;

}
