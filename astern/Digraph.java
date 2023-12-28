import java.util.ArrayList;

/*
 * Diese Klasse repräsentiert einen gerichteten Graphen.
 */
public class Digraph {
  
  // Liste mit allen zum Graph zugehörigen Knoten
  private ArrayList<Knoten> knotenmenge;
  // Startknoten des Graphs
  private Knoten startknoten;

  public Digraph(ArrayList<Knoten> knotenmenge, Knoten startknoten) {
    this.knotenmenge = knotenmenge;
    this.startknoten = startknoten;
  }

  // ----------------------------------------------------------------

  public ArrayList<Knoten> getKnotenmenge() {
    return knotenmenge;
  }

  public void setKnotenmenge(ArrayList<Knoten> knotenmenge) {
    this.knotenmenge = knotenmenge;
  }

  public Knoten getStartknoten() {
    return startknoten;
  }

  public void setStartknoten(Knoten startknoten) {
    this.startknoten = startknoten;
  }

}
