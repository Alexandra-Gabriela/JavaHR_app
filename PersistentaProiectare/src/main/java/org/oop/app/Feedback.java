package org.oop.app;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Feedback {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NonNull
    @Id private Integer id;
    private String Detalii;
    private String Nota;

    public Feedback(Integer id, String detalii, String nota) {
        this.id = id;
        Detalii = detalii;
        Nota = nota;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", Detalii='" + Detalii + '\'' +
                ", Nota='" + Nota + '\'' +
                '}';
    }
    @ManyToOne
    @JoinColumn(name = "id_Evaluare")
    private EvaluareAngajat evaluareAngajat;
}
