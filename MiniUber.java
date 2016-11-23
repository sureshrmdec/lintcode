import java.util.*;

//Definition of Trip:
class Trip {
    public int id; // trip's id, primary key
    public int driver_id, rider_id; // foreign key
    public double lat, lng; // pick up location
    public Trip(int rider_id, double lat, double lng) { }
}

//Definition of Helper
class Helper {
    public static double get_distance(double lat1, double lng1,
            double lat2, double lng2) {
        // return distance between (lat1, lng1) and (lat2, lng2)
        return 0.0;
    }
};

public class MiniUber {

    private Map<Integer, Trip> driverTrips;
    private Map<Integer, Info> driverInfos;

    private class Info {
        public double lat, lng;
        public boolean available;
        public Info(double lat, double lng, boolean available) {
            this.lat = lat; this.lng = lng; this.available = available;
        }
    }

    public MiniUber() {
        driverTrips = new HashMap<Integer, Trip>();
        driverInfos = new HashMap<Integer, Info>();
    }

    // @param driver_id an integer
    // @param lat, lng driver's location
    // return matched trip information if there have matched rider or null
    public Trip report(int driver_id, double lat, double lng) {
        if (driverTrips.containsKey(driver_id)) {
            return driverTrips.get(driver_id);
        }
        else {
            driverInfos.put(driver_id, new Info(lat, lng, true));
            return null;
        }
    }

    // @param rider_id an integer
    // @param lat, lng rider's location
    // return a trip
    public Trip request(int rider_id, double lat, double lng) {
        int matched = -1;
        double minDist = Integer.MAX_VALUE;

        for (int driver: driverInfos.keySet()) {
            Info info = driverInfos.get(driver);
            if (!info.available) continue;

            double driverLat = info.lat, driverLng = info.lng;
            double dist = Helper.get_distance(lat, lng, driverLat, driverLng);
            if (minDist > dist) {
                matched = driver;
                minDist = dist;
            }
        }

        Trip trip = new Trip(rider_id, lat, lng);
        trip.driver_id = matched;
        driverInfos.get(matched).available = false;
        driverTrips.put(matched, trip);

        return trip;
    }
}
