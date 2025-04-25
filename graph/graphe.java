package dijkstra.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class graphe<T> implements Graph<T> {

    private HashMap<T, List<Arc<T>>> arcs;

    public graphe(){
        arcs = new HashMap<>();
    }

    public boolean arcExists(T s1, T s2){
        if (!arcs.containsKey(s1)){
            return false;
        }

        for (Arc<T> sommet : arcs.get(s1)){
            if (sommet == s2){
                return true;
            }
        }

        return false;
    }

    public void newArc(T src, T dst, int val) throws ArcAlreadyExistsException {
        if (arcExists(src, dst)){
            throw new ArcAlreadyExistsException();
        }

        if (!arcs.containsKey(src)){
            List<Arc<T>> listeTemp = new ArrayList<>();
            listeTemp.add(new Arc<>(val, dst));
            arcs.put(src, listeTemp);
        }
        else {
            arcs.get(src).add(new Arc<>(val, dst));
        }
    }

    @Override
    public List<Arc<T>> getSucc(T s){
        return arcs.getOrDefault(s, null);
    }

}
