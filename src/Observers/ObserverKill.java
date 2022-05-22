package Observers;

public class ObserverKill implements Observer{
    @Override
    public void update() {
        System.out.println("Enemy killed!");
    }
}
