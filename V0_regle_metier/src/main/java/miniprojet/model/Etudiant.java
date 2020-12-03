package miniprojet.model;


import java.util.Objects;

public class Etudiant{
    private String nom ;

    public Etudiant(String nom)  {
        this.nom=nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(nom, etudiant.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
    public boolean compareto (Etudiant t){
        return this.getNom().compareToIgnoreCase(t.getNom())==0;
    }
}
