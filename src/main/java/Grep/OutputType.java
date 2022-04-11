package Grep;


public interface OutputType {
    //интерфейс, который позволяет при тестах наполнять лист, а при обычной работе утилиты выводить строки в консоль
    void outputTo(String s);
}
