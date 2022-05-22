package Observers;

public class ObserverScore implements Observer{
    @Override
    public void update() {
        System.out.println("Score ++");
    }
}
