package model;

import java.util.Set;
import java.util.TreeSet;

public class Tableau {

    private String nameT;
    private Set<Column> listcat;

    public Tableau(String nameT) {
        this.nameT = nameT;
        this.listcat = new TreeSet<>();
    }

    public String getNameT() {
        return nameT;
    }

    public Set<Column> getListcat() {
        return listcat;
    }

    public void setNameT(String nameT) {
        this.nameT = nameT;
    }

    public void setListcat(Set<Column> listcat) {
        this.listcat = listcat;
    }
}
