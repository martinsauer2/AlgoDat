import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Breitensuche {

  public static void main(String[] args) {

    Knoten a = new Knoten("A");
    Knoten b = new Knoten("B");
    Knoten c = new Knoten("C");
    Knoten d = new Knoten("D");
    Knoten e = new Knoten("E");

    // Verknüpfen von Knoten
    a.fügeNachbarHinzu(b);
    a.fügeNachbarHinzu(c);
    b.fügeNachbarHinzu(d);
    c.fügeNachbarHinzu(e);
   
    // Liste mit Knoten für einen Graphen
    ArrayList<Knoten> knotenmenge = new ArrayList<Knoten>();
    knotenmenge.add(a);
    knotenmenge.add(b);
    knotenmenge.add(c);
    knotenmenge.add(d);
    knotenmenge.add(e);

    // Anlegen des Graphs mit Knotenmenge und Startknoten
    Digraph graph = new Digraph(knotenmenge, a);

    // Aufruf Breitensuche für Graphen
    // beeinflusst nur Variablen "wurdeBesucht" und "vorgänger" der Knoten
    breitensuche(graph);

    System.out.println(zeigePfadZu(e));
  }

  private static String zeigePfadZu(Knoten ziel) {
    // Ausgabe des kürzesten berechneten Pfades zu Zielknoten durch Breitensuche
    String s = "";
    Knoten k = ziel;

    while(k != null) {
      s = k.getName() + " " + s;
      k = k.getVorgänger();
    }

    return s;
  }

  private static void breitensuche(Digraph graph) {
    // Initialisierung der Knoten, indem Vorgänger gelöscht werden und sie als nicht besucht markiert werden
    for (Knoten k : graph.getKnotenmenge()) {
      k.setBesucht(false);
      k.setVorgänger(null);
    }

    // Anlegen der Queue / Warteschlange
    Queue<Knoten> q = new LinkedList<Knoten>();

    // Ablegen des Startknotens in der Warteschlange und diesen als besucht markieren
    q.add(graph.getStartknoten());
    graph.getStartknoten().setBesucht(true);

    // Solange die Warteschlange nicht leer ist
    while (! q.isEmpty()) {
      // Knoten am Anfang der Warteschlange entfernen und als aktuellen Knoten auswählen
      Knoten derzeitigerKnoten = q.poll();

      // Iteriere über alle Nachbarn des aktuellen Knotens
      for (Pfad p : derzeitigerKnoten.getNachbarn()) {
        // Wenn noch nicht als besucht markiert
        if (p.getZiel().wurdeBesucht() == false) {
          // Der Warteschlange hinzufügen
          q.add(p.getZiel());
          // als besucht markieren
          p.getZiel().setBesucht(true);
          // und derzeitigen Knoten als Vorgänger hinzufügen
          p.getZiel().setVorgänger(derzeitigerKnoten);
        }
      }
    }
  }
  
}