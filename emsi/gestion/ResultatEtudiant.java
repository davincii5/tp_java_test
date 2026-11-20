package ma.emsi.gestion;

public record ResultatEtudiant(int id, String nom, double note, boolean admis) {
    public ResultatEtudiant {
        if (id <= 0 || nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Identité invalide");
        }
        if (!Double.isFinite(note) || note < 0 || note > 20) {
            throw new IllegalArgumentException("Note invalide");
        }
    }
}
