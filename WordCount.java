public class WordCount {
    public static void main(String[] args) {
        String chunk = "Google Bye GoodBye Hadopp lintcode";
        String[] words = chunk.split(" ");
        for (String word: words) {
            System.out.println("word: " + word);
        }
    }
}
