package model;

public class Card implements Comparable<Card> {

    private final String name;

    public Card( String name){
        this.name=name;
    }

    public final String getName() {
        return name;
    }



    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return name != null ? name.equals(card.name): card.name==null ;
    }

    @Override
    public int hashCode() {
        return name != null? name.hashCode() : 0 ;
    }

    @Override
    public int compareTo(Card c) {
        return this.name.compareToIgnoreCase(c.name);
    }
}
