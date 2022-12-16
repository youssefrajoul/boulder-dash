package Observer;

import java.util.*;

public abstract class Subject {
    private ArrayList observers;

    protected Subject(){
        observers  = new ArrayList();
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        ListIterator i = observers.listIterator(0);
        while (i.hasNext()) {
            ((Observer) i.next()).update(this);
        }
    }
}
