package adaptator;

import maze.Maze;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class GraphMaze<T> implements Graph<T> {

    private Maze<T> maze = null;

    public GraphMaze(Maze<T> maze) {
        this.maze = maze;
    }

    public List<Arc<T>> getSucc(T sommet){

        // On récupère l'ensemble des voisins accessibles du sommet
        Set<T> s = maze.openedNeighbours(sommet);

        // On créé une liste d'arcs et on y crée nos arcs de poids 1 pour chaque voisins
        List<Arc<T>> arcs = new ArrayList<Arc<T>>();
        for (T i : s){
            Arc<T> a = new Arc<>(1, i);
            arcs.add(a);
        }

        return arcs;

    }


}
