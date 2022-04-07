import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrepTest {
    Grep grep = new Grep();

    @Test
    void keepDigitTest() throws IOException {
        assertEquals(List.of("123", "456", "789"), grep.grep(false, false,
                true, "[0-9]+", "input/keepDigit.txt"));
    }

    @Test
    void lowercaseTest() throws IOException {
        assertEquals(List.of("Alla", "Guava"), grep.grep(false,
                true, true, "A\\b", "input/lowercase.txt"));
    }

    @Test
    void noDigitInversionTest() throws IOException {
        assertEquals(List.of("ABC", "DEF", "GHI"), grep.grep(true, false,
                true, "[0-9]+", "input/noDigit.txt"));
    }

    @Test
    void test() throws IOException {
        assertEquals(List.of("word avb", "fgfdd word gsgsgs"), grep.grep(false, false, false,
                "word", "input/word.txt"));
    }

    @Test
    void throwTest() {
        assertThrows(IOException.class, () -> grep.grep(false, false, false, "bobr", "sosker"));
    }
}