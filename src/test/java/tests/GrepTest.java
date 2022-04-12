package tests;

import static org.junit.jupiter.api.Assertions.*;

import Grep.Grep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Grep.outputToList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrepTest {
    private static final Grep grep = new Grep();
    private static final List<String> list = new ArrayList<>();
    //Создаем лист, который мы перезаписываем при помощи grep и потом сравниваем

    @BeforeEach
    void clearance() {
        list.clear();
    }

    @Test
    void keepDigitTest() throws IOException {
        grep.grep(false, false,
                true, "[0-9]+", "input/keepDigit.txt", new outputToList(list));
        assertEquals(List.of("123", "456", "789"), list);
    }

    @Test
    void lowercaseTest() throws IOException {
        grep.grep(false,
                true, true, "A\\b", "input/lowercase.txt", new outputToList(list));
        assertEquals(List.of("Alla", "Guava"), list);
    }

    @Test
    void noDigitInversionTest() throws IOException {
        grep.grep(true, false,
                true, "[0-9]+", "input/noDigit.txt", new outputToList(list));
        assertEquals(List.of("ABC", "DEF", "GHI"), list);
    }

    @Test
    void test() throws IOException {
        grep.grep(false, false, false,
                "word", "input/word.txt", new outputToList(list));
        assertEquals(List.of("word avb", "fgfdd word gsgsgs"), list);
    }

    @Test
    void throwTest() {
        assertThrows(IOException.class, () ->
                grep.grep(false, false, false, "bobr", "sosker", new outputToList(list)));
    }

    @Test
    void mountain() throws IOException {
        grep.grep(false, true, false, "FUDZI", "input/text.txt", new outputToList(list));
        assertEquals(List.of("fudzi132dhd", "wfegOJOSg2123Fudzi", "N-wordFudzi"), list);
    }
}