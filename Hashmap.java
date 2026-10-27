import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    class CheckInInfo {
        String station;
        int time;

        CheckInInfo(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    class RouteInfo {
        int totalTime;
        int count;

        RouteInfo(int totalTime, int count) {
            this.totalTime = totalTime;
            this.count = count;
        }
    }

    private Map<Integer, CheckInInfo> checkIns;
    private Map<String, RouteInfo> routes;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        routes = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckInInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInInfo info = checkIns.get(id);

        String route = info.station + "->" + stationName;
        int travelTime = t - info.time;

        if (routes.containsKey(route)) {
            RouteInfo r = routes.get(route);
            r.totalTime += travelTime;
            r.count++;
        } else {
            routes.put(route, new RouteInfo(travelTime, 1));
        }

        checkIns.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "->" + endStation;

        RouteInfo r = routes.get(route);

        return (double) r.totalTime / r.count;
    }
}