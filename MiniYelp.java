import java.util.*;

// Definition of Location:
class Location {
    public double latitude, longitude;
    public static Location create(double lati, double longi) {
        // This will create a new location object
        return null;
    }
};

// Definition of Restaurant
class Restaurant {
    public int id;
    public String name;
    public Location location;
    public static Restaurant create(String name, Location location) {
        // This will create a new restaurant object,
        // and auto fill id
        return null;
    }
};

// Definition of Helper
class Helper {
    public static double get_distance(Location location1, Location location2) {
        // return distance between location1 and location2.
        return 0.0;
    }
};

class GeoHash {
    public static String encode(Location location) {
        // return convert location to a GeoHash string
        return "";
    }
    public static Location decode(String hashcode) {
         // return convert a GeoHash string to location
         return null;
    }
};

public class MiniYelp {

    private class Node {
        public String name;
        public double distance;
        public Node(String n, double d) { name = n; distance = d; }
    }

    private class NodeComparator implements Comparator<Node> {
        public int compare(Node v, Node w) {
            if (v.distance < w.distance) return -1;
            else if (v.distance > w.distance) return 1;
            else return 0;
        }
    }

    private HashMap<Integer, Restaurant> restaurants;

    public MiniYelp() {
        restaurants = new HashMap<Integer, Restaurant>();
    }

    // @param name a string
    // @param location a Location
    // @return an integer, restaurant's id
    public int addRestaurant(String name, Location location) {
        Restaurant r = Restaurant.create(name, location);
        restaurants.put(r.id, r);
        return r.id;
    }

    // @param restaurant_id an integer
    public void removeRestaurant(int restaurant_id) {
        restaurants.remove(restaurant_id);
    }

    // @param location a Location
    // @param k an integer, distance smaller than k miles
    // @return a list of restaurant's name and sort by 
    // distance from near to far.
    public List<String> neighbors(Location location, double k) {
        List<Node> results = new LinkedList<Node>();
        for (int id: restaurants.keySet()) {
            Restaurant r = restaurants.get(id);
            double distance = Helper.get_distance(r.location, location);
            if (distance< k)
                results.add(new Node(r.name, distance));
        }

        Collections.sort(results, new NodeComparator());
        List<String> answer = new LinkedList<String>();
        for (Node n: results)
            answer.add(n.name);

        return answer;
    }
};
