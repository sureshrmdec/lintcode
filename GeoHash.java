import java.util.*;

public class GeoHash {
    /**
     * @param latitude, longitude a location coordinate pair 
     * @param precision an integer between 1 to 12
     * @return a base32 string
     */

    private static String DICT = "0123456789bcdefghjkmnpqrstuvwxyz";

    public String encode(double latitude, double longitude, int precision) {

        int evenDiv = precision * 5 / 2 + ((precision % 2 == 0) ? 0 : 1);
        int oddDiv = precision * 5 / 2;

        double minLong = -180, midLong = 0, maxLong = 180;
        int count = 0;
        StringBuilder sbLong = new StringBuilder();
        while (true) {
            //System.out.println("minLong: " + minLong + ", midLong: " + midLong + ", maxLong: " + maxLong);
            if (count++ < evenDiv) {
                if (midLong < longitude) {
                    sbLong.append("1");
                    minLong = midLong;
                    midLong = (minLong + maxLong) / 2;
                }
                else {
                    sbLong.append("0");
                    maxLong = midLong;
                    midLong = (minLong + maxLong) / 2;
                }
            }
            else break;
        }

        double minLat = -90, midLat = 0, maxLat = 90;
        count = 0;
        StringBuilder sbLat = new StringBuilder();
        while (true) {
            //System.out.println("minLat: " + minLat + ", midLat: " + midLat + ", maxLat: " + maxLat);
            if (count++ < oddDiv) {
                if (midLat < latitude) {
                    sbLat.append("1");
                    minLat = midLat;
                    midLat = (minLat + maxLat) / 2;
                }
                else {
                    sbLat.append("0");
                    maxLat = midLat;
                    midLat = (minLat + maxLat) / 2;
                }
            }
            else break;
        }

        StringBuilder sb = new StringBuilder();
        count = 0;
        while (count < evenDiv) {
            sb.append(sbLong.charAt(count));
            if (count < oddDiv) sb.append(sbLat.charAt(count));
            count++;
        }

        //System.out.println(sb.toString());

        StringBuilder result = new StringBuilder();
        count = 0;
        while (count < precision) {
            int start = count * 5;
            String str = sb.substring(start, start + 5);
            int index = 0;
            int mul = 16;
            for (int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) == '1')
                    index += mul;
                mul /= 2;
            }
            
            result.append(DICT.charAt(index));
            count++;
        }

        return result.toString();
    }

    private void decode(String hash) {
        System.out.println("decode:");
        for (int i = 0; i < hash.length(); ++i) {
            int index = DICT.indexOf(hash.charAt(i));
            char[] sb = {'0', '0', '0', '0', '0'};

            int count = 4;
            while (index != 0) {
                if (index % 2 == 1) sb[count] = '1';
                index /= 2;
                count--;
            }
            System.out.print(new String(sb));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GeoHash hash = new GeoHash();

        hash.decode("wx4g0s8q3jf9");

        System.out.println(hash.encode(39.92816697, 116.38954991, 12));
    }
}
