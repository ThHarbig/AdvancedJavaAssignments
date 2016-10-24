import java.io.IOException;

/**
 * Created by theresa on 22.10.16.
 */
public class CommandLine {
    public static void main(String[] args) throws IOException {
        String file = args[0];
        FastaTool tool = new FastaTool(file);
        tool.readFile();
        tool.formattedOutput();
    }
}
