package Teste;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.oop.app.Departament;
import org.oop.app.Pozitie;

public class InitializareDateDepartament {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplicatieHR");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Inserare departamente
        Departament departament1 = new Departament(1,"Vânzări");
        Departament departament2 = new Departament(2,"Marketing");
        Departament departament3 = new Departament(3,"Resurse Umane");
        Departament departament4 = new Departament(4,"Logistică");
        Departament departament5 = new Departament(5,"Financiar");
        Departament departament6 = new Departament(6,"IT");
        Departament departament7 = new Departament(7,"Producție");
        Departament departament8 = new Departament(8,"Achiziții");
        Departament departament9 = new Departament(9,"Juridic");
        Departament departament10 = new Departament(10,"Servicii Clienți");

        em.persist(departament1);
        em.persist(departament2);
        em.persist(departament3);
        em.persist(departament4);
        em.persist(departament5);
        em.persist(departament6);
        em.persist(departament7);
        em.persist(departament8);
        em.persist(departament9);
        em.persist(departament10);

        // Inserare poziții pentru fiecare departament
        Pozitie pozitie1 = new Pozitie(null, "Manager Vânzări", Pozitie.Stare.vacanta, "Responsabil de vânzări.");
        pozitie1.setDepartament(departament1);
        Pozitie pozitie2 = new Pozitie(null, "Asistent Vânzări", Pozitie.Stare.ocupata, "Asistă managerul de vânzări.");
        pozitie2.setDepartament(departament1);
        Pozitie pozitie3 = new Pozitie(null, "Specialist Marketing", Pozitie.Stare.vacanta, "Responsabil de campaniile de marketing.");
        pozitie3.setDepartament(departament2);
        Pozitie pozitie4 = new Pozitie(null, "Recrutor", Pozitie.Stare.vacanta, "Responsabil de recrutarea personalului.");
        pozitie4.setDepartament(departament3);
        Pozitie pozitie5 = new Pozitie(null, "Manager Logistică", Pozitie.Stare.ocupata, "Responsabil de gestionarea logisticii.");
        pozitie5.setDepartament(departament4);
        Pozitie pozitie6 = new Pozitie(null, "Contabil", Pozitie.Stare.vacanta, "Responsabil de gestionarea financiară.");
        pozitie6.setDepartament(departament5);
        Pozitie pozitie7 = new Pozitie(null, "Dezvoltator Software", Pozitie.Stare.vacanta, "Responsabil de dezvoltarea aplicațiilor software.");
        pozitie7.setDepartament(departament6);
        Pozitie pozitie8 = new Pozitie(null, "Inginer Producție", Pozitie.Stare.vacanta, "Responsabil de procesele de producție.");
        pozitie8.setDepartament(departament7);
        Pozitie pozitie9 = new Pozitie(null, "Specialist Achiziții", Pozitie.Stare.ocupata, "Responsabil de achiziționarea materialelor.");
        pozitie9.setDepartament(departament8);
        Pozitie pozitie10 = new Pozitie(null, "Consilier Juridic", Pozitie.Stare.vacanta, "Responsabil de aspectele juridice.");
        pozitie10.setDepartament(departament9);
        Pozitie pozitie11 = new Pozitie(null, "Reprezentant Servicii Clienți", Pozitie.Stare.vacanta, "Responsabil de relațiile cu clienții.");
        pozitie11.setDepartament(departament10);

        em.persist(pozitie1);
        em.persist(pozitie2);
        em.persist(pozitie3);
        em.persist(pozitie4);
        em.persist(pozitie5);
        em.persist(pozitie6);
        em.persist(pozitie7);
        em.persist(pozitie8);
        em.persist(pozitie9);
        em.persist(pozitie10);
        em.persist(pozitie11);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
