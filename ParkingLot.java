import java.util.*;

// enum type for Vehicle
enum VehicleSize {
    Motorcycle,
    Compact,
    Large,
}

abstract class Vehicle {
    public abstract VehicleSize size();
}

class Motorcycle extends Vehicle {
    public VehicleSize size() { return VehicleSize.Motorcycle; }
}

class Car extends Vehicle {
    public VehicleSize size() { return VehicleSize.Compact; }
}

class Bus extends Vehicle {
    public VehicleSize size() { return VehicleSize.Large; }
}

/* Represents a level in a parking garage */
class Level {
    private Vehicle[][] grid;

    public Level(int rows, int spots) {
        grid = new Vehicle[rows][spots];
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < spots; ++j)
                grid[i][j] = null;
    }

    public boolean park(Vehicle v) {
        List<Integer> list = findSpace(v);
        if (list == null) return false;

        int i = list.get(0);
        int j = list.get(1);
        int len = 1;
        if (v.size() == VehicleSize.Large) len = 5;
        while (len > 0) {
            grid[i][j + len - 1] = v;
            len--;
        }

        return true;
    }

    public boolean unPark(Vehicle v) {
        int m = grid.length, n = grid[0].length;

        int count = v.size() == VehicleSize.Large ? 5 : 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == v) {
                    grid[i][j] = null;
                    count--;
                }

                if (count == 0) return true;
            }
        }
        return false;
    }

    public List<Integer> findSpace(Vehicle v) {
        int m = grid.length, n = grid[0].length;

        int start = 0, end = 0, len = 1;
        switch (v.size()) {
            case Motorcycle:    start = 0;          end = n; len = 1; break;
            case Compact:       start = n / 4;      end = n; len = 1; break;
            case Large:         start = n / 4 * 3;  end = n; len = 5; break;
            default: break;
        }

        for (int i = 0; i < m; ++i) {
            for (int j = start; j < end; ++j) {
                while (j < end && grid[i][j] != null) ++j;

                int count = 0;
                while (j < end && grid[i][j] == null) {
                    if (++count == len) {

                        List<Integer> ret = new ArrayList<Integer>();
                        ret.add(i);
                        ret.add(j - count + 1);
                        return ret;
                    }
                    ++j;
                }
            }
        }
        return null;
    }

    public void debugPrint() {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j)
                System.out.print(grid[i][j] + ", ");
            System.out.println();
        }
    }
}

public class ParkingLot {

    // @param n number of leves
    // @param num_rows  each level has num_rows rows of spots
    // @param spots_per_row each row has spots_per_row spots

    private List<Level> levels;

    public ParkingLot(int n, int num_rows, int spots_per_row) {
        levels = new ArrayList<Level>();
        for (int i = 0; i < n; ++i) {
            levels.add(new Level(num_rows, spots_per_row));
        }
    }

    // Park the vehicle in a spot (or multiple spots)
    // Return false if failed
    public boolean parkVehicle(Vehicle v) {
        for (Level l: levels) {
            boolean ret = l.park(v);
            if (ret) return true;
        }
        return false;
    }

    // unPark the vehicle
    public void unParkVehicle(Vehicle v) {
        for (Level l: levels) {
            if (l.unPark(v)) return;
        }
    }

    private void debug() {
        int count = 0;
        for (Level l: levels) {
            System.out.println("Level: " + count);
            l.debugPrint();
        }
    }

    public static void main(String[] args) {
        ParkingLot p = new ParkingLot(1, 1, 11);
        Vehicle Motorcycle_1 = new Motorcycle();
        Vehicle Car_1 = new Car();
        Vehicle Car_2 = new Car();
        Vehicle Car_3 = new Car();
        Vehicle Car_4 = new Car();
        Vehicle Car_5 = new Car();
        Vehicle Bus_1 = new Bus();

        System.out.println(p.parkVehicle(Motorcycle_1)); // return true
        p.debug();
        System.out.println(p.parkVehicle(Car_1)); // return true
        p.debug();
        System.out.println(p.parkVehicle(Car_2)); // return true
        p.debug();
        System.out.println(p.parkVehicle(Car_3)); // return true
        p.debug();
        System.out.println(p.parkVehicle(Car_4)); // return true
        p.debug();
        System.out.println(p.parkVehicle(Car_5)); // return true
        p.debug();
        System.out.println(p.parkVehicle(Bus_1)); // return false
        p.unParkVehicle(Car_5);
        p.debug();
        System.out.println(p.parkVehicle(Bus_1)); // return true
        p.debug();
    }
}
