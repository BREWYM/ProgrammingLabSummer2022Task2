package Grep;


public class outputToConsole implements OutputType {
    //Реализация интерфейса для вывода на консоль

    @Override
    public void outputTo(String s) {
        System.out.println(s);
    }
}
