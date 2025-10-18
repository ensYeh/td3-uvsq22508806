package fr.uvsq.cprog.collex;

public class NomMachine {

    private String nom;
    private String domaine;

    public NomMachine(String n, String d) {

        if (n.contains("."))
            throw new IllegalArgumentException("Ne pas mettre de point dans un nom de machine");
        if (!d.matches("^(?!-)([A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Nom de domaine invalide");
        }
        this.nom = n;
        this.domaine = d;

    }

    public String getNom() {
        return nom;
    }

    public String getDomaine() {
        return domaine;
    }

    public String NomQualifie() {
        return nom + "." + domaine;
    }

    @Override
    public String toString() {
        return NomQualifie(); 
    }
}
