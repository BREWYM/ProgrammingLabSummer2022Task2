package Grep;

import java.util.List;

//Реализация интерфейса для наполнения листа
public class outputToList implements OutputType {
    private final List<String> list;

    @Override
    public void outputTo(String s) {
        list.add(s);
    }

    public outputToList(List<String> list) {
        this.list = list;

    }
}
