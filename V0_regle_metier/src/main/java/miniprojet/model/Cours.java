package miniprojet.model;


import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Cours {
    private String nomcours;
    private static final Set<Etudiant> listetudiantins = new TreeSet<>();
    public Cours( String nomcours ){
        this.nomcours=nomcours;
    }

    public void setNomcours(String nomcours) {
        this.nomcours = nomcours;
    }

    public String getNomcours() {
        return nomcours;
    }

    public static Set<Etudiant> getListetudiant() {
        return listetudiantins;
    }

    private boolean addStudent ( Etudiant t){
        return listetudiantins.add(t);
    }
    
    private boolean desincritStudent ( Etudiant t){return listetudiantins.remove(t);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cours)) return false;
        Cours cours = (Cours) o;
        return Objects.equals(getNomcours(), cours.getNomcours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNomcours());
    }
    public boolean compareto (Cours c){
        return this.getNomcours().compareToIgnoreCase(c.getNomcours())==0;
    }
}
