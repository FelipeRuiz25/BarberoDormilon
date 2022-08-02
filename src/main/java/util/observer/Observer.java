package util.observer;

import simulator.models.SimulationStatus;

public interface Observer{

    void update(SimulationStatus status);
}
