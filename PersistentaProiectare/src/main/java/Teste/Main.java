package Teste;

import org.oop.app.Candidat;
import org.oop.app.Candidatura;
import org.oop.app.Departament;
import org.oop.app.Pozitie;
import repository.DepPozRepository;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DepPozRepository repository = new DepPozRepository();

        try {
            // Crearea unui nou candidat
            Candidat candidat = new Candidat(null, "Ion ion", "123 Main St", "555-58855", "ion.ion@example.com", new Date());
            repository.saveCandidat(candidat);

            // Obținerea și afișarea tuturor departamentelor
            List<Departament> departamente = repository.getAllDepartments();
            departamente.forEach(System.out::println);

            // Obținerea pozițiilor vacante pentru un departament specific, dacă există departamente
            if (!departamente.isEmpty()) {
                Departament departament = departamente.get(0);
                List<Pozitie> pozitiiVacante = repository.getPozByDepartament(departament);
                pozitiiVacante.forEach(System.out::println);
            }

            // Crearea și salvarea unei noi candidaturi
            Candidatura candidatura = new Candidatura();
            candidatura.setStatus(Candidatura.StatusAplicare.in_curs);
            candidatura.setCV("Continut CV");
            candidatura.setScrisoareIntentie("Scrisoare de intentie");
            candidatura.setDataAplicare(new Date());

            repository.saveCandidatura(candidatura);

            // Asocierea candidatului cu candidatura
            candidat.setCandidatura(candidatura);
            repository.saveCandidat(candidat);

            // Obținerea candidaturilor pentru un candidat și afișarea lor
            List<Candidatura> candidaturi = repository.getCandidaturiByCandidat(candidat);
            candidaturi.forEach(System.out::println);
        } finally {
            // Închidere repository
            repository.close();
        }
    }
}
