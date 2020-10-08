import Logic.*;
import View.MainView;

public class Program {
    public static void main(String[] args) {
        Bag bag = new Bag();

        MainView view = new MainView();
        view.setBag(bag);

        view.newData();
    }
}
