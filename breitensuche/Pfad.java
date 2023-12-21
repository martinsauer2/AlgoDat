/*
 * Diese Klasse repräsentiert Pfade zu Nachbarknoten eines Knotens ohne Kantengewichte.
 */
public class Pfad {
  
  // Zielknoten (also Nachbar)
  private Knoten ziel;

  public Pfad(Knoten ziel) {
    this.ziel = ziel;
  }

  // ----------------------------------------

  public Knoten getZiel() {
    return ziel;
  }

  public void setZiel(Knoten ziel) {
    this.ziel = ziel;
  }

}
