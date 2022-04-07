package Grep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    public String neededCondition(boolean i, String s) {
        return (i) ? s.toLowerCase() : s;
    }

    public List<String> grep(boolean inversion, boolean ignoring, boolean regex, String inputWord, String inputname)
            throws IOException {
        String word = neededCondition(ignoring, inputWord);
        List<String> list = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(inputname))) {
            while ((line = br.readLine()) != null) {

                if (regex) {
                    Pattern pattern = Pattern.compile(word);
                    Matcher matcher = pattern.matcher(neededCondition(ignoring, line));
                    boolean find = matcher.find();
                    if (find && !inversion) list.add(line);
                    if (!find && inversion) list.add(line);

                } else {
                    if (neededCondition(ignoring, line).contains(word) && !inversion)
                             list.add(line);
                    if (!neededCondition(ignoring, line).contains(word) && inversion) list.add(line);
                }
            }
        }
        list.forEach(System.out::println);
        return list;
    }

}
