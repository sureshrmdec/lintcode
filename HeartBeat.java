public class HeartBeat {

    private Map<String, Integer> beats;
    private int K;
    public HeartBeat() {
        beats = new HashMap<String, Integer>();
    }

    // @param slaves_ip_list a list of slaves'ip addresses
    // @param k an integer
    // @return void
    public void initialize(List<String> slaves_ip_list, int k) {
        for (String ip: slaves_ip_list)
            beats.put(ip, 0);

        K = k;
    }

    // @param timestamp current timestamp in seconds
    // @param slave_ip the ip address of the slave server
    // @return nothing
    public void ping(int timestamp, String slave_ip) {
        if (!beats.containsKey(slave_ip)) return;

        beats.put(slave_ip, timestamp);
    }

    // @param timestamp current timestamp in seconds
    // @return a list of slaves'ip addresses that died
    public List<String> getDiedSlaves(int timestamp) {
        List<String> deadIps = new ArrayList<String>();
        for (String ip: beats.keySet()) {
            int life = beats.get(ip);
            if ((timestamp - life) >= 2*K)
                deadIps.add(ip);
        }
        return deadIps;
    }
}
