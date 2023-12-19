import java.util.ArrayList;
import java.util.Iterator;

public class Knoten implements Comparable<Knoten> {
  
  // ++++++++++++++++++++++++++++
  private int kostenBisHier;
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

  // -------------------------------------------------------

  public String getName() {
    return name;
  }

  public ArrayList<Pfad> getNachbarn() {
    return benachbarteKnoten;
  }

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

  @Override
  public int compareTo(Knoten k) {
    if (kostenBisHier == k.getKostenBisHier()) return 0;
    if (kostenBisHier < k.getKostenBisHier()) return -1;
    return 1;
  }

}
