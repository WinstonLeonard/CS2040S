import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
public class TSPGraph implements IApproximateTSP {

    HashMap<Integer, Integer> parent;

    int mapSize;


    @Override
    public void MST(TSPMap map) {

        // TODO: implement this method
        //Nodes are represented as integers on the Map
        //Prioritized by Double
        TreeMapPriorityQueue<Double, Integer> pq = new TreeMapPriorityQueue<>();

        for (int i = 0; i < map.getCount(); i++) {
            pq.add(i, Double.POSITIVE_INFINITY);
        }
        pq.decreasePriority(0, 0.0);

        HashSet<Integer> S = new HashSet<>();
        S.add(0);

        parent = new HashMap<>();
        parent.put(0, null);


        while(!pq.isEmpty()) {
            Integer i = pq.extractMin();
            S.add(i);
            for (int j = 0; j < map.getCount(); j++) {
                double distance = map.pointDistance(i, j);
                if(!S.contains(j) && pq.lookup(j) > distance) {
                    pq.decreasePriority(j, distance);
                    parent.put(j, i);
                }
            }
        }
        for(int i = 1; i < map.getCount(); i++) {
            Integer k = parent.get(i);
            map.setLink(i, k, false);
        }
        map.redraw();
    }


    HashMap<Integer, Integer> hm;
    List<Integer> list;
    HashSet<Integer> visitedSet;

    int counter;

    int MSTSize;
    @Override
    public void TSP(TSPMap map) {
        hm = new HashMap<>();
        list = new ArrayList<>();
        counter = 0;
        MST(map);
        // TODO: implement the rest of this method.
        visitedSet = new HashSet<>();
        MSTSize = parent.size();

        DFSVisit(0);

        for(int i = 0; i < list.size()-1; i++) {
            map.setLink(list.get(i), list.get(i+1), false);
        }
        map.setLink(list.get(list.size()-1), list.get(0), false);
        map.redraw();
    }
    public void DFSVisit(int startID) {
        visitedSet.add(startID);
        list.add(startID);
        for(int i : getAdjacentNodes(startID)) {
            if(!visitedSet.contains(i)) {
                DFSVisit(i);
            }
        }
    }

    public ArrayList<Integer> getAdjacentNodes(int j) {
        ArrayList<Integer> adjacentNodes = new ArrayList<>();
        for(int i = 0; i < MSTSize; i++) {
            if(parent.get(i) != null && parent.get(i) == j) {
                adjacentNodes.add(i);
            }
        }
        if(parent.get(j) != null) {
            adjacentNodes.add(parent.get(j));
        }
        return adjacentNodes;
    }

    HashSet<Integer> validTourVisited;
    @Override
    public boolean isValidTour(TSPMap map) {
        // Note: this function should with with *any* map, and not just results from TSP().
        // TODO: implement this method
        validTourVisited = new HashSet<>();
        int start = 0;
        validTourVisited.add(start);
        int nextCity = map.getLink(start);
        if(nextCity < 0) {
            return false;
        }
        while(nextCity != start && map.getLink(nextCity) >= 0) {
            //System.out.println(nextCity);
            if(validTourVisited.contains(nextCity)) {
                return false;
            } else {
                validTourVisited.add(nextCity);
            }
            nextCity = map.getLink(nextCity);
        }
        System.out.println(validTourVisited.size() == map.getCount());
        if(validTourVisited.size() == map.getCount()) {
            return true;
        }  else {
            return false;
        }
    }

    @Override
    public double tourDistance(TSPMap map) {
        // Note: this function should with with *any* map, and not just results from TSP().
        // TODO: implement this method
        if(isValidTour(map)) {
            double sum = 0;
            int counter = 0;
            int currentCity = 0;
            while(counter < map.getCount()){
                int nextCity = map.getLink(currentCity);
                double distance = map.pointDistance(currentCity, nextCity);
                sum = sum + distance;
                counter++;
                currentCity = nextCity;
            }
            return sum;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        TSPMap map = new TSPMap(args.length > 0 ? args[0] : "tenpoints.txt");
        TSPGraph graph = new TSPGraph();

        //graph.MST(map);
        //graph.TSP(map);
        map.setLink(0, 1);
        map.setLink(1, 2);
        map.setLink(2, 3);
        map.setLink(3, 4);
        map.setLink(4, 5);
        map.setLink(5, 6);
        map.setLink(6, 7);
        map.setLink(7, 8);
        map.setLink(8, 9);
        map.eraseLink(9);
        System.out.println(graph.isValidTour(map));
        // System.out.println(graph.tourDistance(map));



    }
}
