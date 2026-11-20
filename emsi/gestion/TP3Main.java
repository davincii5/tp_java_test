package ma.emsi.gestion;

import java.util.List;

public class TP3Main {
    public static void main(String[] args) {
        List<Etudiant> etudiants = List.of(
            new Etudiant(1, "Amina", 15.0),
            new Etudiant(2, "Youssef", 9.0),
            new Etudiant(3, "Sara", 12.5),
            new Etudiant(4, "Omar", 14.0),
            new Etudiant(5, "Lina", 18.0),
            new Etudiant(6, "Karim", 7.5)
        );

        System.out.println("--- Etape 1 ---");
        AnalyseEtudiants.etape1(etudiants);

        System.out.println("--- Etape 2 ---");
        AnalyseEtudiants.etape2(etudiants);

        System.out.println("--- Etape 3 ---");
        AnalyseEtudiants.etape3(etudiants);

        System.out.println("--- Etape 4 ---");
        AnalyseEtudiants.etape4(etudiants);

        System.out.println("--- Etape 5 ---");
        AnalyseEtudiants.etape5(etudiants);

        System.out.println("--- Defis ---");
        AnalyseEtudiants.defis(etudiants);

// etudiants.ajouter("Informatique"); // Erreur de compilation
    }
}
