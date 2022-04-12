package Grep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private boolean ignoring;

    public String shapedLine(String s) {
        return ignoring ? s.toLowerCase() : s;
    }

    public void grep(boolean inversion, boolean ignoring, boolean regex, String inputWord, String inputname, OutputType outputType)
            throws IOException {
        this.ignoring = ignoring;
        String word = shapedLine(inputWord);
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(inputname))) {
            Pattern pattern = Pattern.compile(word);
            while ((line = br.readLine()) != null) {

                if (inversion) { //Вынес инверсию, но так выглядит хуже, чем было
                    if (regex) {
                        Matcher matcher = pattern.matcher(shapedLine(line));
                        if (!matcher.find()) outputType.outputTo(line);
                    } else {
                        if (!shapedLine(line).contains(word))
                            outputType.outputTo(line);
                    }
                } else {
                    if (regex) {
                        Matcher matcher = pattern.matcher(shapedLine(line));
                        if (matcher.find()) outputType.outputTo(line);
                    } else {
                        if (shapedLine(line).contains(word))
                            outputType.outputTo(line);
                    }
                }
            }
        }

    }
}
