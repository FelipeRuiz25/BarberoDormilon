package util.observer;

import simulator.models.SimulationStatus;

import java.util.ArrayList;

public class Observable {

    protected ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o){
          observers.add(o);
    }

    public void removeObserver(Observer o){
        int index = observers.indexOf(o);
        if (index >= 0 && index < observers.size()) {
            observers.remove(index);
        }
    }

    public void notifyObservers(SimulationStatus status){
        for (Observer o: observers) {
            o.update(status);
        }
    }
}
