import java.util.ArrayList;
import java.util.Iterator;

/*
 * Diese Klasse repräsentiert Knoten in einem gerichteten Graphen.
 */
public class Knoten {
  
  // für die Breitensuche relevant
  // ++++++++++++++++++++++++++++
  // Vorgängerknoten zur Bestimmung des Pfades
  private Knoten vorgänger = null;
  // Knoten wurde schon besucht
  private boolean wurdeBesucht = false;
  // ++++++++++++++++++++++++++++
  
  private String name;
  private ArrayList<Pfad> benachbarteKnoten;

  public Knoten(String name) {
    this.name = name;
    this.benachbarteKnoten = new ArrayList<Pfad>();
  }

  public void fügeNachbarHinzu(Knoten ziel) {
    benachbarteKnoten.add(new Pfad(ziel));
  }

  public String getName() {
    return name;
  }

  public ArrayList<Pfad> getNachbarn() {
    return benachbarteKnoten;
  }

  // Gibt die Nachbarn eines Knotens aus
  public String gibNachbarnAus() {
    String s = "";
    Iterator<Pfad> iter = benachbarteKnoten.iterator();

    while(iter.hasNext()) {
      Pfad derzeitigerPfad = iter.next();
      s += derzeitigerPfad.getZiel().getName() + "\n"; 
    }

    return s;
  }

  public Knoten getVorgänger() {
    return vorgänger;
  }

  public void setVorgänger(Knoten vorgänger) {
    this.vorgänger = vorgänger;
  }

  public boolean wurdeBesucht() {
    return wurdeBesucht;
  }

  public void setBesucht(boolean wurdeBesucht) {
    this.wurdeBesucht = wurdeBesucht;
  }

}
