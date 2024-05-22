package repository;

import jakarta.persistence.TypedQuery;
import metamodel.AbstractRepository;
import org.oop.app.*;

import java.util.Date;
import java.util.List;

public class AplicatieRepository extends AbstractRepository {

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
    public Candidat getCandidatById(Integer id) {
        return getEm().find(Candidat.class,id);
    }
    public Candidatura getCandidaturaById(Integer id) {
        return getEm().find(Candidatura.class, id);
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
    public void saveModificariPozitie(Candidat candidat) {
        beginTransaction();
        try {
            update(candidat);
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
        }

}
    public void setInterviuData(Integer candidaturaId, Date dataInterviu) {
        beginTransaction();
        try {
            Candidatura candidatura = getCandidaturaById(candidaturaId);
            if (candidatura != null) {
                Interviu interviu = candidatura.getInterviu();
                if (interviu == null) {
                    interviu = new Interviu();
                    candidatura.setInterviu(interviu);
                }
                interviu.setDataInterviu(dataInterviu);
                update(interviu);
                update(candidatura);
                commitTransaction();
            } else {
                rollbackTransaction();
                throw new IllegalArgumentException("Candidatura not found with id: " + candidaturaId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        }
    }
    public void saveInterviu(Interviu interviu) {
        beginTransaction();
        try {
            if (interviu.getId() == null) {
                create(interviu);
            } else {
                update(interviu);
            }
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
        }
    }

    public Interviu findInterviuById(Integer id) {
        return getEm().find(Interviu.class, id);
    }

    public List<Interviu> getAllInterviuri() {
        TypedQuery<Interviu> query = getEm().createQuery("SELECT i FROM Interviu i", Interviu.class);
        return query.getResultList();
    }

}



