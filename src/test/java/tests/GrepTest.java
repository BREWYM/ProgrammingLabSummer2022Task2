package tests;

import static org.junit.jupiter.api.Assertions.*;

import grep.Grep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import grep.ListOutput;
import grep.OutputType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrepTest {
    private static final Grep grep = new Grep();
    private static final List<String> list = new ArrayList<>();
    private static final OutputType output = new ListOutput(list); //Вынес интерфейс в поле
    //Создаем лист, который мы перезаписываем при помощи grep и потом сравниваем


    //Я попробовал убрать очистку и сделать лист полем у outputType
    @AfterEach
    void cleanUp(){
        list.clear();
    }

    @Test
    void keepDigitTest() throws IOException {
        grep.grep(false, false,
                true, "[0-9]+", "input/keepDigit.txt", output);
        assertEquals(List.of("123", "456", "789"), list);
    }

    @Test
    void lowercaseTest() throws IOException {
        grep.grep(false,
                true, true, "A\\b", "input/lowercase.txt", output);
        assertEquals(List.of("Alla", "Guava"), list);
    }

    @Test
    void noDigitInversionTest() throws IOException {
        grep.grep(true, false,
                true, "[0-9]+", "input/noDigit.txt", output);
        assertEquals(List.of("ABC", "DEF", "GHI"), list);
    }

    @Test
    void test() throws IOException {
        grep.grep(false, false, false,
                "word", "input/word.txt", output);
        assertEquals(List.of("word avb", "fgfdd word gsgsgs"), list);
    }

    @Test
    void throwTest() {
        assertThrows(IOException.class, () ->
                grep.grep(false, false, false, "bobr", "sosker", output));
    }

    @Test
    void mountain() throws IOException {
        grep.grep(false, true, false, "FUDZI", "input/text.txt", output);
        assertEquals(List.of("fudzi132dhd", "wfegOJOSg2123Fudzi", "N-wordFudzi"), list);
    }
}