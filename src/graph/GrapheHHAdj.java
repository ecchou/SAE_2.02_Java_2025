package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.String;

public class GrapheHHAdj implements VarGraph {

    private HashMap<String, List<Arc<String>>> arcs;

    public GrapheHHAdj(){
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
            if (sommet.dst().equals(s2)){
                return true; // Dans ce cas l'arc existe
            }
        }

        // Si on est ici, il n'existe pas
        return false;
    }

    // Ajoute un point
    public void ajouterSommet(String noeud){
        if (!arcs.containsKey(noeud)){ // si le point n'existe pas déjà
            arcs.put(noeud, new ArrayList<>());
        }

    }

    // Ajoute un arc
    public void ajouterArc(String source, String destination, Integer valeur) throws IllegalArgumentException {
        // Si l'arc existe déjà
        if (arcExiste(source, destination) ){
            throw new IllegalArgumentException();
        }

        // Si le point source n'a pas d'arc (pas nécessaire si on est passé par ajouterSommet)
        if (!arcs.containsKey(source)){
            List<Arc<String>> listeTemp = new ArrayList<>();
            listeTemp.add(new Arc<>(valeur, destination));
            arcs.put(source, listeTemp);
        }
        else { // Si il a déjà un ou des arc(s)
            Arc<String> a = new Arc<>(valeur, destination);
            arcs.get(source).add(a);
        }
    }

    @Override
    public List<Arc<String>> getSucc(String s){
        //System.out.println(arcs.toString());
        return arcs.getOrDefault(s, null);
    }

}
