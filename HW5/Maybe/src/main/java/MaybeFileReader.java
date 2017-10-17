import java.io.*;
import java.util.ArrayList;

public class MaybeFileReader {
    public static void main(String[] args) {

        ArrayList<Maybe<Integer>> maybeArray = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("numbers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Integer lineInt;
                try {
                    lineInt = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    lineInt = null;
                }
                maybeArray.add(new Maybe<>(lineInt));
            }
        } catch (IOException e) {
            System.out.println("An IO exception occured");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("squared.txt"))) {
            for (Maybe<Integer> maybeInt : maybeArray) {
                String toWrite;
                try {
                    Integer result = maybeInt.map(value -> value*value).get();
                    toWrite = Integer.toString(result);
                } catch (NothingException e) {
                    toWrite = "null";
                }
                bw.write(toWrite);
                bw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("An IO exception occured");
        }
    }
}
