package grep;


public class ConsoleOutput implements OutputType {
    //Реализация интерфейса для вывода на консоль

    @Override
    public void outputTo(String s) {
        System.out.println(s);
    }
}
