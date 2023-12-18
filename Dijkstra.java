import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
  
  public static void main(String[] args) {
    Knoten a = new Knoten("A");
    Knoten b = new Knoten("B");
    Knoten c = new Knoten("C");
    Knoten d = new Knoten("D");
    Knoten e = new Knoten("E");

    a.fügeNachbarHinzu(b, 100);
    a.fügeNachbarHinzu(d, 50);
    d.fügeNachbarHinzu(b, 100);
    d.fügeNachbarHinzu(e, 250);
    b.fügeNachbarHinzu(c, 100);
    b.fügeNachbarHinzu(e, 250);
    c.fügeNachbarHinzu(e, 50);

    ArrayList<Knoten> knotenmenge = new ArrayList<Knoten>();

    knotenmenge.add(a);
    knotenmenge.add(b);
    knotenmenge.add(c);
    knotenmenge.add(d);
    knotenmenge.add(e);

    Digraph g = new Digraph(knotenmenge, a);

    System.out.println(dijkstra(g, a));
  }

  public static String dijkstra(Digraph g, Knoten ziel) {
    String s = "";
    
    // Kosten initialisieren
    g.getStartknoten().setKostenBisHier(0);
    
    // initialisiere unbesuchte Knoten in Warteschlange
    PriorityQueue<Knoten> unbesuchteKnoten = new PriorityQueue<Knoten>();
    for (Knoten k : g.getKnotenmenge()) {
      unbesuchteKnoten.add(k);
    }

    // Schritt 2
    while(unbesuchteKnoten.size() != 0) {
      // Knoten wird aus Queue herausgenommen -> als besucht markiert
      Knoten derzeitigerKnoten = unbesuchteKnoten.poll();
      s += derzeitigerKnoten.getName();

      // Wenn herausgenommener Knoten Zielknoten, dann Abbruch
      if (derzeitigerKnoten.equals(ziel)) {
        return s;
      }
    }

    return s;
  }

}
