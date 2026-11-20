package ma.emsi.gestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gestion des étudiants EMSI");

        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant(1, "Amina", 15));
        etudiants.add(new Etudiant(2, "Youssef", 9));
        etudiants.add(new Etudiant(3, "Sara", 12.5));

        for (Etudiant e : etudiants) {
            System.out.println(e.getNom() + " : " + e.getNote());
            if (e.estAdmis()) {
                System.out.println("Admis " + e.getNom());
            }
        }
        System.out.println("Moyenne : " + calculerMoyenne(etudiants));

        Etudiant trouve = rechercherParNom(etudiants, " amina ");
        System.out.println("Recherche : " + (trouve == null ? "Introuvable" : trouve.getNom()));

        try {
            new Etudiant(4, "Test", -2);
        } catch (IllegalArgumentException ex) {
            System.out.println("Erreur de note " + ex.getMessage());
        }

        Set<Etudiant> uniques = new HashSet<>();
        uniques.add(new Etudiant(1, "Amina", 15));
        uniques.add(new Etudiant(1, "Autre nom", 7));
        System.out.println("Taille du Set : " + uniques.size());

        Map<Integer, Etudiant> parId = new HashMap<>();
        for (Etudiant e : etudiants) {
            parId.put(e.getId(), e);
        }
        System.out.println("Id 2 : " + parId.get(2).getNom());

        List<Etudiant> parIdentifiant = new ArrayList<>(etudiants);
        Collections.sort(parIdentifiant);

        List<Etudiant> parNote = new ArrayList<>(etudiants);
        parNote.sort(new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant a, Etudiant b) {
                return Double.compare(a.getNote(), b.getNote());
            }
        });

        afficherOrdre("Tri id", parIdentifiant);
        afficherOrdre("Tri note", parNote);
    }

    static double calculerMoyenne(List<Etudiant> liste) {
        if (liste.isEmpty()) throw new IllegalArgumentException("Liste vide");
        double somme = 0;
        for (Etudiant e : liste) {
            somme += e.getNote();
        }
        return somme / liste.size();
    }

    static Etudiant rechercherParNom(List<Etudiant> liste, String saisie) {
        if (saisie == null) return null;
        String recherche = saisie.trim();
        for (Etudiant e : liste) {
            if (e.getNom().equalsIgnoreCase(recherche)) return e;
        }
        return null;
    }

    static void afficherOrdre(String titre, List<Etudiant> liste) {
        System.out.print(titre + " : ");
        for (Etudiant e : liste) {
            System.out.print(e.getNom() + " ");
        }
        System.out.println();
    }
}
