import java.util.ArrayList;

public class Digraph {
  
  private ArrayList<Knoten> knotenmenge;
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
