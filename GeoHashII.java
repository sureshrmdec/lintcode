public class GeoHashII {
    /**
     * @param geohash a base32 string
     * @return latitude and longitude a location coordinate pair
     */
    private static String DICT = "0123456789bcdefghjkmnpqrstuvwxyz";

    public double[] decode(String geohash) {
        // Write your code here
        //System.out.println("decode:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < geohash.length(); ++i) {
            int index = DICT.indexOf(geohash.charAt(i));
            char[] array = {'0', '0', '0', '0', '0'};

            int count = 4;
            while (index != 0) {
                if (index % 2 == 1) array[count] = '1';
                index /= 2;
                count--;
            }
            sb.append(new String(array));
        }

        StringBuilder lngStr = new StringBuilder();
        StringBuilder latStr = new StringBuilder();
        for (int i = 0; i < sb.length(); i += 2) {
            lngStr.append(sb.charAt(i));
            if (i + 1 < sb.length()) latStr.append(sb.charAt(i + 1));
        }

        double[] latLng = new double[2];
        latLng[0] = divide(latStr.toString(), -90, 90);
        latLng[1] = divide(lngStr.toString(), -180, 180);
        
        return latLng;
    }

    private double divide(String str, double min, double max) {
        double mid = (min + max) / 2;
        for (char c: str.toCharArray()) {
            if (c == '1') {
                min = mid;
            }
            else {
                max = mid;
            }
            mid = (min + max) / 2;
        }

        return mid;
    }
}
