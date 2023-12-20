import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
  
  public static void main(String[] args) {
    
    // Anlegen von Knoten
    Knoten a = new Knoten("A");
    Knoten b = new Knoten("B");
    Knoten c = new Knoten("C");
    Knoten d = new Knoten("D");
    Knoten e = new Knoten("E");

    // Verknüpfen von Knoten
    a.fügeNachbarHinzu(b, 100);
    a.fügeNachbarHinzu(d, 50);
    d.fügeNachbarHinzu(b, 100);
    d.fügeNachbarHinzu(e, 250);
    b.fügeNachbarHinzu(c, 100);
    b.fügeNachbarHinzu(e, 250);
    c.fügeNachbarHinzu(e, 50);

    // Liste mit Knoten für einen Graphen
    ArrayList<Knoten> knotenmenge = new ArrayList<Knoten>();
    knotenmenge.add(a);
    knotenmenge.add(b);
    knotenmenge.add(c);
    knotenmenge.add(d);
    knotenmenge.add(e);

    // Anlegen des Graphs mit Knotenmenge und Startknoten
    Digraph graph = new Digraph(knotenmenge, a);

    // Aufruf Dijkstra-Algorithmus für Graphen
    // beeinflusst nur Variablen "kostenBisHier" und "vorgänger" der Knoten
    dijkstra(graph);

    System.out.print(gibErgebnisAus(graph));

    //System.out.println(zeigePfadZu(b));
  }

  // Methode zur Ausgabe des günstigsten Pfades und Kosten um Zielknoten zu erreichen
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

  // Methode zur tabellarischen Ausgabe der Ergebnisse des Dijkstra-Algorithmus
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

  // Dijkstra-Algorithmus für einen Graphen
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

    // Solange es noch unbesuchte Knoten gibt
    while(! unbesuchteKnoten.isEmpty()) {
      // Knoten mit minimalen Kosten wird aus Queue ausgewählt und herausgenommen -> als besucht markiert
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
            // und setze aktuellen Knoten als Vorgänger des Nachbarknotens
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
