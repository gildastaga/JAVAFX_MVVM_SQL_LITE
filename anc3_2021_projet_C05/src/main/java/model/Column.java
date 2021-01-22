package model;

import java.util.Set;
import java.util.TreeSet;

public class Column {
    private String nameC;
    private Set<Carte> listcat;

    public Column(String nameC) {
        this.nameC = nameC;
        this.listcat = new TreeSet<>();
    }

    public String getNameC() {
        return nameC;
    }

    public Set<Carte> getListcat() {
        return listcat;
    }


    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public void setListcat(Set<Carte> listcat) {
        this.listcat = listcat;
    }
}
