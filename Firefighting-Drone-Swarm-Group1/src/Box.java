/**
 * A counter that holds the two ingredients
 * given by the agent and can give to the chefs.
 * @author Marc Fernandes 101288346
 * @version 2025-01-23
 */
public class Box {
    // the object to hold and whether the counter is empty
    private Object o;
    private boolean empty = true;

    /**
     * Returns the object if the counter is not empty, and
     * sets the counter to empty.
     * @return The object that was held
     */
    public synchronized Object get() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        empty = true;
        notify();
        return o;
    }

    /**
     * Sets the counter to the object given.
     * @param o The object for the counter to take
     */
    public synchronized void put(Object o) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.o = o;
        empty = false;
        notifyAll();
    }

    /**
     * Returns the object if the counter is not empty,
     * however does not remove the object from the counter.
     * @return The object that was held
     */
    public synchronized Object peek() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        return o;
    }
}