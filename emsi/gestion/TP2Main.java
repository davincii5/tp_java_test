package ma.emsi.gestion;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TP2Main {
    static boolean noteAdmise(double note) {
        return note >= 10;
    }

    static boolean verifier(Etudiant e, RegleAdmission regle) {
        return regle.valider(e);
    }

    public static void main(String[] args) {
        List<Etudiant> etudiants = List.of(
            new Etudiant(1, "Amina", 15),
            new Etudiant(2, "Youssef", 9),
            new Etudiant(3, "Sara", 12.5)
        );

        RegleAdmission regle = e -> e.getNote() >= 10;
        Predicate<Etudiant> admis = regle::valider;
        Function<Etudiant, ResultatEtudiant> bilan = e -> new ResultatEtudiant(e.getId(), e.getNom(), e.getNote(), admis.test(e));
        Consumer<ResultatEtudiant> afficher = System.out::println;
        Supplier<Etudiant> exemple = () -> new Etudiant(4, "Omar", 14);

        for (Etudiant e : etudiants) {
            if (admis.test(e)) {
                afficher.accept(bilan.apply(e));
            }
        }
        System.out.println("Exemple : " + exemple.get().getNom());
    }
}
