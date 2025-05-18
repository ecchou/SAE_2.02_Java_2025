package path;

import graph.Graph.Arc;
import graph.Graph;
import graph.ShortestPath;

import java.util.*;

public class Dijkstra<T> implements ShortestPath<T> {

    public Distances<T> compute(Graph<T> g, T src, Animator<T> animator) throws IllegalArgumentException{

        Map<T, Integer> dist = new HashMap<>(); // Associe à un sommet T sa distance la plus courte trouvée au point source
        Map<T, T> pred = new HashMap<>();       // Associe à un sommet T dans un des chemins de longueur optimale
        Set<T> treated = new HashSet<>();       // Stocke les sommets T dont la distance minimale est connue

        // Une file d'attente qui trie par distance les sommets qu'il reste à traiter
        PriorityQueue<T> file = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        dist.put(src, 0);       // On part du point source qui a comme distance à lui même 0
        pred.put(src, null);    // On met le point source dans pred, il est le seul point à ne pas avoir de predeceseur
        file.add(src);          // On met dans la file d'attente le premier sommet qu'on va traiter : la source

        while (!file.isEmpty()){

            // On récupère et on enlève le premier sommet de la file d'attente
            T sommet = file.poll();

            if (treated.contains(sommet))
                continue;

            // On log qu'on traite ce sommet avec sa distance à la source
            animator.accept(sommet, dist.get(sommet));
            //System.out.println("Sommet " + sommet + ": " + dist.get(sommet));
            //System.out.println("Successeurs de " + sommet + ":" + g.getSucc(sommet).toString());
            // Pour chaque arc sortant du sommet

            for (Arc<T> arc : g.getSucc(sommet)){

                // Si l'arc a un poids négatif, erreur
                if (arc.val() < 0)
                    throw new IllegalArgumentException();

                // avec T le point de destination et distance sa distance au point de départ via son prédécésseur
                T destination = arc.dst();
                int distance = dist.get(sommet) + arc.val();

                // si le point n'a pas encore de distance calculée ou si on a trouvé mieux :
                if (!dist.containsKey(destination) || distance < dist.get(destination)) {
                    // on met la distance trouvée ou on remplace celle trouvée avant
                    if (!dist.containsKey(destination))
                        dist.put(destination, distance);
                    else
                        dist.replace(destination, distance);
                    pred.put(destination, sommet);  // on indique son prédécésseur sur le chemin actuel
                    file.add(destination);          // on l'ajoute à la file d'attente
                    //System.out.println("Sommet " + destination + " ajouté à la file");
                }

            }

            treated.add(sommet);

        }

        // On renvoie le résultat sous forme d'un objet Distances
        return new Distances<>(dist, pred);

    }

}
