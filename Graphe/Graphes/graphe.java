package dijkstra.Graphe.Graphes;

import dijkstra.Graphe.IVarGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class graphe<String> implements IVarGraph<String> {

    private HashMap<String, List<Arc<String>>> arcs;

    public graphe(){
        arcs = new HashMap<>();
    }

    // Vérifie si un arc existe
    public boolean arcExiste(String s1, String s2){
        // Si le point source n'existe pas ou n'a aucun arc sortant
        if (!arcs.containsKey(s1)){
            return false;
        }

        // Sinon on parcoure chaque arc et on regarde si le point destination est s2
        for (Arc<String> sommet : arcs.get(s1)){
            if (sommet == s2){
                return true; // Dans ce cas l'arc existe
            }
        }

        // Si on est ici, il n'existe pas
        return false;
    }

    // Ajoute un point
    public void ajouterSommet(String s){
        arcs.put(s, new ArrayList<>());
    }

    // Ajoute un arc
    public void ajouterArc(String src, String dst, int val) throws IllegalArgumentException {
        // Si l'arc existe déjà
        if (arcExiste(src, dst)){
            throw new IllegalArgumentException();
        }

        // Si le point source n'a pas d'arc
        if (!arcs.containsKey(src)){
            List<Arc<String>> listeTemp = new ArrayList<>();
            listeTemp.add(new Arc<>(val, dst));
            arcs.put(src, listeTemp);
        }
        else { // Si il a déjà un ou des arc(s)
            arcs.get(src).add(new Arc<>(val, dst));
        }
    }

    @Override
    public List<Arc<String>> getSucc(String s){
        return arcs.getOrDefault(s, null);
    }

}
