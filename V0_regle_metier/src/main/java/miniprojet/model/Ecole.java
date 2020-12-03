package miniprojet.model;


import java.util.Set;
import java.util.TreeSet;

public class Ecole {
    private static final int MAX_STUDENTS_PER_COURSE = 5;
    private static final int MAX_COURSES_PER_STUDENT = 2;
    private static final Set<Cours> listcoour = new TreeSet<>();
    private static final Set<Etudiant> listetudiant = new TreeSet<>();


    public Ecole(Cours cours,Etudiant t) {
        listcoour.add(cours);
        listetudiant.add(t);
    }

    private boolean getEtudiant(Etudiant nom){
        return listetudiant.contains(nom);
    }

    private boolean getCours( Cours cour){
        return listcoour.contains(cour);
    }

    private boolean addCours (Cours c){
       return listcoour.add(c);
    }

    private boolean addStudent ( Etudiant t){
        return listetudiant.add(t);
    }
    private boolean desincritCou(Cours c){
        return listcoour.remove(c);
    }
    private boolean desincritEtu(Etudiant c){
        return listetudiant.remove(c);
    }
}
