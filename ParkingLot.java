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
    private boolean[][] grid;

    public Level(int rows, int spots) {
        grid = new boolean[rows][spots];
    }

    public boolean park(Vehicle v) {
        int m = grid.length, n = grid[0].length;

        int start = 0, end = 0, step = 1;
        switch (v.size()) {
            case Motorcycle:    start = 0;          end = n; step = 1; break;
            case Compact:       start = n / 4;      end = n; step = 1; break;
            case Large:         start = n / 4 * 3;  end = n; step = 5; break;
            default: break;
        }

        for (int i = 0; i < m; ++i) {
            for (int j = start; j < end; ++j) {
                while (grid[i][j] == false && j < end) ++j;

                int count = 0;
                while (grid[i][j] == true && j < end) {
                    ++j;
                    if (++count == step) {
                        while (count > 0) {
                            grid[i][j - count] = false;
                            count--;
                        }
                        return true;
                    }
                }
            }
        }

        return false;
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
    public void unParkVehicle(Vehicle vehicle) {
        
    }
}
