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

    Digraph graph = new Digraph(knotenmenge, a);

    dijkstra(graph);

    System.out.print(gibErgebnisAus(graph));

    //System.out.println(zeigePfadZu(c));
  }

  private static String zeigePfadZu(Knoten ziel) {
    String s = "";
    Knoten k = ziel;

    while(k != null) {
      s = k.getName() + " " + s;
      k = k.getVorgänger();
    }

    s += "\n" + "Kosten: " + ziel.getKostenBisHier();

    return s;
  }

  private static String gibErgebnisAus(Digraph graph) {
    String s = "Knoten : Kosten, Vorgänger\n--------------------------\n";

    for (Knoten k : graph.getKnotenmenge()) {
      if (k.getVorgänger() != null) {
        s += k.getName() + ": " + k.getKostenBisHier() + ", " + k.getVorgänger().getName() + "\n";  
      } else {
        s += k.getName() + ": " + k.getKostenBisHier() + ", " + "-" + "\n";
      } 
    }

    return s;
  }

  public static void dijkstra(Digraph g) {
    // Kosten initialisieren
    for (Knoten k : g.getKnotenmenge()) {
      k.setKostenBisHier(Integer.MAX_VALUE);
    }
    g.getStartknoten().setKostenBisHier(0);
    
    // initialisiere unbesuchte Knoten in Warteschlange
    PriorityQueue<Knoten> unbesuchteKnoten = new PriorityQueue<Knoten>();
    for (Knoten k : g.getKnotenmenge()) {
      unbesuchteKnoten.add(k);
    }

    // Schritt 2
    while(! unbesuchteKnoten.isEmpty()) {
      // Knoten mit minimalen Kosten wird aus Queue herausgenommen -> als besucht markiert
      Knoten derzeitigerKnoten = unbesuchteKnoten.poll();

      // Iteriere über alle Nachbarknoten
      for (Pfad p : derzeitigerKnoten.getNachbarn()) {
        // wenn Nachbarknoten noch unbesucht ist
        if (unbesuchteKnoten.contains(p.getZiel())) {
          // Berechne Kosten für Nachbarknoten
          int neueKosten = derzeitigerKnoten.getKostenBisHier() + p.getKosten();
          // Wenn berechnete Kosten kleiner als bisherige Kosten
          if (neueKosten < p.getZiel().getKostenBisHier()) {
            // aktualisiere Kosten
            p.getZiel().setKostenBisHier(neueKosten);
            // und setze aktuellen Knoten als Vorgänger
            p.getZiel().setVorgänger(derzeitigerKnoten);

            // Element in Queue updaten
            unbesuchteKnoten.remove(p.getZiel());
            unbesuchteKnoten.add(p.getZiel());
          }
        }
      }
    }
  }

}
