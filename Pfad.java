public class Pfad {
  
  private Knoten ziel;
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
