package grep;

import java.util.List;

//Реализация интерфейса для наполнения листа
public class ListOutput implements OutputType {
    private final List<String> list;

    @Override
    public void outputTo(String s) {
        list.add(s);
    }

    public ListOutput(List<String> list) {
        this.list = list;

    }
}
