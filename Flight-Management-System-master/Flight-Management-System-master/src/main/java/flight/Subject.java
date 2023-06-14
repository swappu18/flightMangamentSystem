package flight;

public interface Subject {
    void register(Observer obs);
    void unregister(Observer obs);

    void notifyObservers();

    Object getUpdate(Observer obs);
}
