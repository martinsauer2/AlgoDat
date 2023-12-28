import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStern {
  
  public static void main(String[] args) {
    // Anlegen von Knoten und Zuweisen der H-Werte (geschätzte Kosten vom aktuellen Knoten zum Zielknoten)
    Knoten sb = new Knoten("Saarbrücken", 222); // Start
    Knoten kl = new Knoten("Kaiserslautern", 158);
    Knoten ff = new Knoten("Frankfurt", 96);
    Knoten kr = new Knoten("Karlsruhe", 140);
    Knoten lh = new Knoten("Ludwigshafen", 108);
    Knoten hb = new Knoten("Heilbronn", 87);
    Knoten wb = new Knoten("Würzburg", 0); // Ziel

    // Anlegen der Pfade und Nachbarn
    sb.fügeNachbarHinzu(kl, 70);
    sb.fügeNachbarHinzu(kr, 145);
    kr.fügeNachbarHinzu(hb, 84);
    hb.fügeNachbarHinzu(wb, 102);
    kl.fügeNachbarHinzu(ff, 103);
    kl.fügeNachbarHinzu(lh, 53);
    ff.fügeNachbarHinzu(wb, 116);
    lh.fügeNachbarHinzu(wb, 183);

    // Anlegen des Graphen mit Startknoten
    ArrayList<Knoten> knotenmenge = new ArrayList<Knoten>();
    knotenmenge.add(sb);
    knotenmenge.add(kl);
    knotenmenge.add(ff);
    knotenmenge.add(kr);
    knotenmenge.add(lh);
    knotenmenge.add(hb);
    knotenmenge.add(wb);

    Digraph graph = new Digraph(knotenmenge, sb);

    System.out.println(gibPfadAus(astern(graph, hb)));
  }

  // Methode zur Ausgabe des Pfades
  private static String gibPfadAus(Knoten k) {
    String s = "";
    Knoten v = k;

    while (v != null) {
      s = v.getName() + " " + s;
      v = v.getVorgänger();
    }

    return s;
  }

  public static Knoten astern(Digraph graph, Knoten zielknoten) {
    // Offene Liste für Knoten, die noch überprüft werden müssen
    PriorityQueue<Knoten> offeneListe = new PriorityQueue<Knoten>();

    // Hinzufügen von Startknoten zu offener Liste
    offeneListe.add(graph.getStartknoten());

    // Geschlossene Liste für Knoten, die bereits besucht wurden
    ArrayList<Knoten> geschlosseneListe = new ArrayList<Knoten>();

    // Solange die offene Liste nicht leer ist
    while (! offeneListe.isEmpty()) {
      // Auswahl Knoten mit niedrigstem F-Wert aus offener Liste
      Knoten k = offeneListe.poll();

      // Wenn Knoten der Zielknoten ist
      if (k == zielknoten) {
        // Suche beenden
        return k;
      }

      // Ansonsten Knoten in geschlossene Liste schieben um ihn als überprüft zu markieren
      geschlosseneListe.add(k);

      // Betrachte alle Nachbarn des ausgewählten Knotens
      for (Pfad p : k.getNachbarn()) {
        // Berechne G-Wert für Nachbar vorübergehend
        int tempG = k.getG() + p.getKosten();

        // Wenn Knoten weder in offener noch geschlossener Liste ist, dann füge Ihn mit berechneten Werten zu offener Liste hinzu
        if (! offeneListe.contains(p.getZiel()) && ! geschlosseneListe.contains(p.getZiel())) {
          p.getZiel().setG(tempG);
          p.getZiel().setVorgänger(k);
          offeneListe.add(p.getZiel());
        } else {
          // Wenn Knoten in einer Liste ist
          // und wenn berechnete Kosten geringer als derzeitige Kosten
          if (tempG < p.getZiel().getG()) {
            // Dann update Kosten und setze derzeitigen Knoten als Vorgänger
            p.getZiel().setVorgänger(k);
            p.getZiel().setG(tempG);

            // Wenn Nachbar in geschlossener Liste, dann setze ihn wieder auf die offene Liste
            if (geschlosseneListe.contains(p.getZiel())) {
              geschlosseneListe.remove(p.getZiel());
              offeneListe.add(p.getZiel());
            }
          }
        }

      }

      // Knoten wurde besucht
      geschlosseneListe.add(k);

    }

    return null;
    
  }

}
