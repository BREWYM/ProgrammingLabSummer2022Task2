package grep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private boolean ignoringCase;

    public String shapedLine(String s) {
        return ignoringCase ? s.toLowerCase() : s;
    }

    public void grep(boolean inversion, boolean ignoring, boolean regex, String inputWord, String inputname, OutputType outputType)
            throws IOException {
        this.ignoringCase = ignoring;
        String word = shapedLine(inputWord);
        String line;
        Pattern pattern = null;

        if (word == null) throw new NullPointerException();

        try (BufferedReader br = new BufferedReader(new FileReader(inputname))) {

            if (regex) {
                pattern = Pattern.compile(word);
                //Не понял замечание про pattern/compile/matcher. Судя по всему, они не могут вернуть ноль
                if (pattern == null) throw new NullPointerException(); //Но проверку добавил
            }
            while ((line = br.readLine()) != null) {
                if (regex) {
                    Matcher matcher = pattern.matcher(shapedLine(line));
                    if (matcher.find() != inversion) outputType.outputTo(line);
                } else {
                    if (shapedLine(line).contains(word) != inversion)
                        outputType.outputTo(line);
                }

            }

        }
    }
}


