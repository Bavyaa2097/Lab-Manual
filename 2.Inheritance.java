import java.util.*;

class UndergroundSystem {

    Map<Integer, String[]> checkIn = new HashMap<>();
    Map<String, int[]> travel = new HashMap<>();

    public UndergroundSystem() {
    }

    public void checkIn(int id, String stationName, int t) {
        checkIn.put(id, new String[]{stationName, String.valueOf(t)});
    }

    public void checkOut(int id, String stationName, int t) {
        String[] info = checkIn.remove(id);
        String key = info[0] + "-" + stationName;

        int[] data = travel.getOrDefault(key, new int[2]);
        data[0] += t - Integer.parseInt(info[1]); // total time
        data[1]++;                               // trip count
        travel.put(key, data);
    }

    public double getAverageTime(String startStation, String endStation) {
        int[] data = travel.get(startStation + "-" + endStation);
        return (double) data[0] / data[1];
    }
}
