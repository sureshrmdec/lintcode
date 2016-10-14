import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;

public class TestcaseReader {

    final static Charset ENCODING = StandardCharsets.UTF_8;
    private BufferedReader reader;

    public TestcaseReader(String filename) {
        Path path = Paths.get(filename);
        try {
            reader = Files.newBufferedReader(path, ENCODING);
        }
        catch (IOException e) {
            System.err.format("IOException: " + e);
        }
    }

    public int[][] readMatrix() throws IOException {

        try {
            String line = reader.readLine();

            ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> list = new ArrayList<Integer>();
            String str = "";
            for (int i = 0; i < line.length(); ++i) {
                char c = line.charAt(i);
                if (c == '[') {
                    if (!list.isEmpty()) lists.add(list);
                    list = new ArrayList<Integer>();
                }
                else if (c == ']' || c == ' ' || c == ',') {
                    if (str.isEmpty()) continue;
                    list.add(Integer.parseInt(str));
                    str = "";
                }
                else str += c;
            }

            int m = lists.size();
            int n = lists.get(0).size();
            int[][] array = new int[m][n];
            int i = 0;
            for (ArrayList<Integer> l: lists) {
                int j = 0;
                for (int v: l) {
                    array[i][j++] = v;
                }
                i++;
            }

            return array;
        }
        catch (IOException e) {
            System.err.format("IOException: " + e);
        }

        return null;
    }

    public static void main(String[] args) {
        try {
            //TestcaseReader reader = new TestcaseReader("./1.in");
            TestcaseReader reader = new TestcaseReader("./matrix.in");
            int[][] matrix = reader.readMatrix();
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[0].length; ++j)
                    System.out.print(matrix[i][j] + " ");
                System.out.println();
            }
        }
        catch (IOException e) {
        }
    }
}
