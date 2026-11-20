package ma.emsi.gestion;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnalyseEtudiants {
    public static void etape1(List<Etudiant> etudiants) {
        etudiants.stream()
                .filter(e -> e.getNote() >= 10)
                .forEach(e -> System.out.println(e.getNom() + " : " + e.getNote()));
    }

    public static void etape2(List<Etudiant> etudiants) {
        List<ResultatEtudiant> resultats = etudiants.stream()
                .filter(e -> e.getNote() >= 10)
                .map(e -> new ResultatEtudiant(e.getId(), e.getNom(), e.getNote(), true))
                .toList();

        resultats.forEach(System.out::println);

        List<String> noms = etudiants.stream()
                .filter(e -> e.getNote() >= 10)
                .map(Etudiant::getNom)
                .toList();
        System.out.println(noms);
    }

    public static void etape3(List<Etudiant> etudiants) {
        List<Etudiant> top3 = etudiants.stream()
                .sorted(Comparator.comparingDouble(Etudiant::getNote).reversed())
                .limit(3)
                .toList();

        top3.forEach(e -> System.out.println(e.getNom() + " : " + e.getNote()));

        List<String> nomsTop3 = top3.stream()
                .map(Etudiant::getNom)
                .toList();
        System.out.println(nomsTop3);
    }

    public static void etape4(List<Etudiant> etudiants) {
        long admisCount = etudiants.stream().filter(e -> e.getNote() >= 10).count();
        boolean noteSup16 = etudiants.stream().anyMatch(e -> e.getNote() >= 16);
        boolean toutesValides = etudiants.stream().allMatch(e -> e.getNote() >= 0 && e.getNote() <= 20);

        Optional<Etudiant> meilleur = etudiants.stream().max(Comparator.comparingDouble(Etudiant::getNote));
        double moyenne = etudiants.stream().mapToDouble(Etudiant::getNote).average().orElse(0.0);

        System.out.println("Admis : " + admisCount);
        System.out.println("Note >= 16 : " + noteSup16);
        System.out.println("Notes valides : " + toutesValides);
        meilleur.ifPresent(e -> System.out.println("Meilleur : " + e.getNom() + " (" + e.getNote() + ")"));
        System.out.printf("Moyenne : %.2f\n", moyenne);
    }

    public static void etape5(List<Etudiant> etudiants) {
        Map<Boolean, List<Etudiant>> partition = etudiants.stream()
                .collect(Collectors.partitioningBy(e -> e.getNote() >= 10));

        System.out.println("Admis : " + partition.get(true).stream().map(Etudiant::getNom).toList());
        System.out.println("Ajournés : " + partition.get(false).stream().map(Etudiant::getNom).toList());

        Map<String, List<Etudiant>> groupes = etudiants.stream()
                .collect(Collectors.groupingBy(e -> {
                    if (e.getNote() >= 16) return "Très bien";
                    if (e.getNote() >= 10) return "Admis";
                    return "Ajourné";
                }));

        System.out.println("Très bien a: " + groupes.getOrDefault("Très bien", List.of()).stream().map(Etudiant::getNom).toList());
        System.out.println("Admis : " + groupes.getOrDefault("Admis", List.of()).stream().map(Etudiant::getNom).toList());
        System.out.println("Ajournés : " + groupes.getOrDefault("Ajourné", List.of()).stream().map(Etudiant::getNom).toList());
    }

    public static void defis(List<Etudiant> etudiants) {
        double somme = etudiants.stream()
                .map(Etudiant::getNote)
                .reduce(0.0, Double::sum);

        System.out.println("Somme : " + somme);

        List<Etudiant> groupe1 = etudiants.subList(0, 3);
        List<Etudiant> groupe2 = etudiants.subList(3, 6);

        List<Etudiant> fusion = java.util.stream.Stream.of(groupe1, groupe2)
                .flatMap(List::stream)
                .toList();

        System.out.println("Taille fusion : " + fusion.size());
    }
}
