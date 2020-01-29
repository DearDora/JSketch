import java.awt.Color;
import java.util.*;

public class Model {
    private List<Observer> observers;
    Color temp = Color.WHITE;
    ColorBlock red = new ColorBlock(Color.RED);
    ColorBlock orange = new ColorBlock(Color.ORANGE);
    ColorBlock yellow = new ColorBlock(Color.YELLOW);
    ColorBlock green = new ColorBlock(Color.GREEN);
    ColorBlock cyan = new ColorBlock(Color.CYAN);
    ColorBlock blue = new ColorBlock(Color.BLUE);
    ColorBlock lightgray = new ColorBlock(Color.LIGHT_GRAY);
    ColorBlock darkgray = new ColorBlock(Color.DARK_GRAY);
    ColorBlock black = new ColorBlock(Color.BLACK);

    ArrayList<Shape> shapes = new ArrayList<Shape>();

    Color innerColor = Color.WHITE;
    Color lineColor = Color.BLACK;
    int lineThickness = 1;
    int currentCommand = Command.SELECTION;
    int selected = -1;

    ColorBlock last = black;

    public Model() {
        this.observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer: this.observers) {
            observer.update(this);
        }
    }
}
