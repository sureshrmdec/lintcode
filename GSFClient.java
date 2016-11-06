/* Definition of BaseGFSClient
 * class BaseGFSClient {
 *     private Map<String, String> chunk_list;
 *     public BaseGFSClient() {}
 *     public String readChunk(String filename, int chunkIndex) {
 *         // Read a chunk from GFS
 *     }
 *     public void writeChunk(String filename, int chunkIndex,
 *                            String content) {
 *         // Write a chunk to GFS
 *     }
 * }
 */
class BaseGFSClient {
    private Map<String, String> chunk_list;
    public BaseGFSClient() {}
    public String readChunk(String filename, int chunkIndex) {
        // Read a chunk from GFS
        return "";
    }
    public void writeChunk(String filename, int chunkIndex,
            String content) {
        return
    }
}

public class GFSClient extends BaseGFSClient {

    private int N;

    Map<String, Integer> chunkInfo;
    public GFSClient(int chunkSize) {
        chunkInfo = new HashMap<String, Integer>();
        N = chunkSize;
    }
    
    // @param filename a file name
    // @return conetent of the file given from GFS
    public String read(String filename) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        if (!chunkInfo.containsKey(filename) || chunkInfo.get(filename) == 0)
            return null;

        while (count < chunkInfo.get(filename)) {
            String chunk = readChunk(filename, count++);
            sb.append(chunk);
        }

        return sb.toString();
    }

    // @param filename a file name
    // @param content a string
    // @return void
    public void write(String filename, String content) {
        int chunkIndex = 0;
        for (int i = 0; i < content.length();) {
            int end = i + N > content.length() ? content.length() : i + N;
            writeChunk(filename, chunkIndex++, content.substring(i, end));
            i += N;
        }

        chunkInfo.put(filename, chunkIndex);
    }
}
