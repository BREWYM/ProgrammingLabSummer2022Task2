import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class GrepLauncher {
    @Option(name = "-v", metaVar = "InversionV", required = false, usage = "Condition Inversion")
    private final boolean inversion = false;

    @Option(name = "-i", metaVar = "RegisterIngnoringI", required = false, usage = "Ignoring of words' register")
    private final boolean ignoring = false;

    @Option(name = "-r", metaVar = "Regex", required = false, usage = "Comparing to regex")
    private final boolean regex = false;

    @Argument(required = true, metaVar = "Word", usage = "A word to compare to")
    private String word;

    @Argument(required = true, metaVar = "InputName", index = 1, usage = "Name of the input file")
    private String inputFileName;

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar grep.jar [-v] [-i] [-r] Word InputName.txt");
            parser.printUsage(System.err);
            return;
        }
        Grep grep = new Grep();
        try {
            grep.grep(inversion, ignoring, regex, word, inputFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new GrepLauncher().launch(args);
    }
}
