import java.util.ArrayList;
import java.util.Iterator;

/*
 * Diese Klasse repräsentiert Knoten in einem gerichteten Graphen.
 */
public class Knoten implements Comparable<Knoten> {
  
  // für den Dijkstra-Algorithmus relevant
  // ++++++++++++++++++++++++++++
  // Kosten zu diesem Knoten vom Startknoten aus
  private int kostenBisHier;
  // Vorgängerknoten zur Bestimmung des Pfades
  private Knoten vorgänger = null;
  // ++++++++++++++++++++++++++++
  
  private String name;
  private ArrayList<Pfad> benachbarteKnoten;

  public Knoten(String name) {
    this.name = name;
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

  public int getKostenBisHier() {
    return kostenBisHier;
  }

  public void setKostenBisHier(int kostenBisHier) {
    this.kostenBisHier = kostenBisHier;
  }

  public Knoten getVorgänger() {
    return vorgänger;
  }

  public void setVorgänger(Knoten vorgänger) {
    this.vorgänger = vorgänger;
  }

  // Methode zum Vergleich zweier Knoten, relevant für PriorityQueue im Dijkstra-Algorithmus
  @Override
  public int compareTo(Knoten k) {
    if (kostenBisHier == k.getKostenBisHier()) return 0;
    if (kostenBisHier < k.getKostenBisHier()) return -1;
    return 1;
  }

}
