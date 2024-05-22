package FormCandidatura;
import com.vaadin.flow.component.notification.Notification;
import org.oop.app.Candidat;
import org.oop.app.Candidatura;
import org.oop.app.Departament;
import org.oop.app.Pozitie;
import repository.AplicatieRepository;

import java.util.List;

public class FormCandidaturaController {
    private AplicatieRepository repository;
    private FormCandidaturaView form;
    public FormCandidaturaController() {
        repository = new AplicatieRepository();
    }

    public void saveModificari(Candidat candidat) {
        try {
            repository.saveCandidat(candidat);

            Candidatura candidatura = new Candidatura();
            candidatura.setStatus(Candidatura.StatusAplicare.in_curs);
            candidatura.getCandidatList().add(candidat);
            repository.saveCandidatura(candidatura);

            Notification.show("Candidatura a fost salvată cu succes!");
        } catch (Exception e) {
            Notification.show("Eroare la salvarea candidaturii: " + e.getMessage());
        }
    }

    public void modificaCandidatura(Candidatura candidatura) {
        try {
            repository.update(candidatura);
            Notification.show("Candidatura a fost actualizată cu succes!");
        } catch (Exception e) {
            Notification.show("Eroare la actualizarea candidaturii: " + e.getMessage());
        }
    }

    public List<Departament> selectDepartamente() {
        return repository.getAllDepartments();
    }

    public List<Pozitie> selectPozitii(Departament departament) {
        return repository.getPozByDepartament(departament);
    }

    /*public void alegeModalitateCv(Candidatura candidatura, String option, String value) {
        switch (option) {
            case "Website personal":
                candidatura.setCvUrl(value);
                candidatura.setLinkedinUrl(null);
                candidatura.setCvAttachment(null);
                break;
            case "Link LinkedIn":
                candidatura.setLinkedinUrl(value);
                candidatura.setCvUrl(null);
                candidatura.setCvAttachment(null);
                break;
            case "Încarcă CV":
                candidatura.setCvAttachment(value);
                candidatura.setCvUrl(null);
                candidatura.setLinkedinUrl(null);
                break;
            default:
                throw new IllegalArgumentException("Opțiune necunoscută pentru încărcarea CV-ului: " + option);
        }
    }*/
    public Candidatura getCandidaturaData(Integer candidaturaId){
        return repository.getCandidaturaById(candidaturaId);
    }
}
