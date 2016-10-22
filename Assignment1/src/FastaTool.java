/**
 * Created by theresa on 22.10.16.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FastaTool {
    public String readFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
