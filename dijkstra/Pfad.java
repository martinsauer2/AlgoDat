/*
 * Diese Klasse repräsentiert Pfade zu Nachbarknoten eines Knotens.
 */
public class Pfad {
  
  // Zielknoten (also Nachbar)
  private Knoten ziel;
  // Kosten, um dorthin zu gelangen
  private int kosten;

  public Pfad(Knoten ziel, int kosten) {
    this.ziel = ziel;
    this.kosten = kosten;
  }

  // ----------------------------------------

  public Knoten getZiel() {
    return ziel;
  }

  public void setZiel(Knoten ziel) {
    this.ziel = ziel;
  }

  public int getKosten() {
    return kosten;
  }

  public void setKosten(int kosten) {
    this.kosten = kosten;
  }

}
