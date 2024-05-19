package repository;

import jakarta.persistence.TypedQuery;
import metamodel.AbstractRepository;
import org.oop.app.Candidat;
import org.oop.app.Candidatura;
import org.oop.app.Departament;
import org.oop.app.Pozitie;

import java.util.List;

public class DepPozRepository extends AbstractRepository {

    public List<Departament> getAllDepartments() {
        TypedQuery<Departament> query = getEm().createQuery("SELECT d FROM Departament d", Departament.class);
        return query.getResultList();
    }

    public List<Pozitie> getPozByDepartament(Departament departament) {
        TypedQuery<Pozitie> query = getEm().createQuery("SELECT p FROM Pozitie p WHERE p.departament = :departament AND p.stare = :stare", Pozitie.class);
        query.setParameter("departament", departament);
        query.setParameter("stare", Pozitie.Stare.vacanta);
        return query.getResultList();
    }

    public Candidat getCandidatById(Integer id) {
        return getEm().find(Candidat.class, id);
    }

    public Candidatura getCandidaturaById(Integer id) {
        return getEm().find(Candidatura.class, id);
    }

    public List<Candidatura> getCandidaturiByCandidat(Candidat candidat) {
        TypedQuery<Candidatura> query = getEm().createQuery("SELECT c FROM Candidatura c WHERE c.candidatList = :candidat", Candidatura.class);
        query.setParameter("candidat", candidat);
        return query.getResultList();
    }

    public void saveCandidat(Candidat candidat) {
        beginTransaction();
        try {
            if (candidat.getId() == null) {
                create(candidat);
            } else {
                update(candidat);
            }
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
        }
    }

    public void saveCandidatura(Candidatura candidatura) {
        beginTransaction();
        try {
            if (candidatura.getId() == null) {
                create(candidatura);
            } else {
                update(candidatura);
            }
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
        }
    }

    public void deleteCandidatura(Candidatura candidatura) {
        beginTransaction();
        try {
            delete(candidatura);
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
        }
    }
}
