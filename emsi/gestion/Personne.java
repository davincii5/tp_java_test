package ma.emsi.gestion;

public abstract class Personne {
    private final String nom;

    protected Personne(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Nom obligatoire");
        }
        this.nom = nom.trim();
    }

    public String getNom() {
        return nom;
    }

    public abstract String role();
}
