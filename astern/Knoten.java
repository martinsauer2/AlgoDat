import java.util.ArrayList;
import java.util.Iterator;

/*
 * Diese Klasse repräsentiert Knoten in einem gerichteten Graphen.
 */
public class Knoten implements Comparable<Knoten> {
  
  // Kosten des besten Pfades vom Startknoten zum aktuellen Knoten
  private int g = 0;

  // geschätzte Kosten zum Zielknoten von diesem Knoten aus
  private int h;

  // Gesamtwert, Summe aus g und h
  private int f;
  
  private Knoten vorgänger;
  private String name;
  private ArrayList<Pfad> benachbarteKnoten;

  public Knoten(String name, int h) {
    this.name = name;
    this.h = h;
    this.benachbarteKnoten = new ArrayList<Pfad>();
  }

  public void fügeNachbarHinzu(Knoten ziel, int kosten) {
    benachbarteKnoten.add(new Pfad(ziel, kosten));
  }

  public String getName() {
    return name;
  }

  public ArrayList<Pfad> getNachbarn() {
    return benachbarteKnoten;
  }

  public int getG () {
    return g;
  }

  public int getH () {
    return h;
  }

  public void setG (int g) {
    this.g = g;
    this.f = this.g + this.h;
  }

  public int getF () {
    return f;
  }

  public Knoten getVorgänger () {
    return this.vorgänger;
  }

  public void setVorgänger (Knoten vorgänger) {
    this.vorgänger = vorgänger;
  }

  // Gibt die Nachbarn und die zugehörigen Kosten, um diese zu erreichen, aus
  public String gibNachbarnAus() {
    String s = "";
    Iterator<Pfad> iter = benachbarteKnoten.iterator();

    while(iter.hasNext()) {
      Pfad derzeitigerPfad = iter.next();
      s += derzeitigerPfad.getZiel().getName() + " - " + derzeitigerPfad.getKosten() + "\n"; 
    }

    return s;
  }

  // Methode zum Vergleich zweier Knoten aufgrund ihres F-Wertes
  @Override
  public int compareTo(Knoten k) {
    if (f == k.getF()) return 0;
    if (f < k.getF()) return -1;
    return 1;
  }

}
