package metamodel;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = -4803471783122679780L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    protected Integer id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "entity_type")
    private String entityType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreated")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateUpdated")
    private Date dateUpdated;

    @PrePersist
    protected void onCreate() {
        dateCreated = dateUpdated = Calendar.getInstance().getTime();
        entityType = this.getClass().getSimpleName();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = Calendar.getInstance().getTime();
    }

    public AbstractEntity(Integer id) {
        this.id = id;
        this.dateCreated = this.dateUpdated = Calendar.getInstance().getTime();
        this.entityType = this.getClass().getSimpleName();
    }
}
