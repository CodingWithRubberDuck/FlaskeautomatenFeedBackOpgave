import Model.Bottle;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BottleQueue {

    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private final ArrayDeque<Bottle> queue;
    private final int max;

    public BottleQueue(ReentrantLock lock, Condition notEmpty, Condition notFull, ArrayDeque<Bottle> queue){
        this.lock = lock;
        this.notEmpty = notEmpty;
        this.notFull = notFull;
        this.queue = queue;
        max = 10;
    }

    public void put(Bottle bottle){
        lock.lock();
        try {
            while (queue.size() == max){
                notFull.await();
            }
            System.out.println("\u001B[37mIndsat  " + bottle.getText() + " med løbenummer " + bottle.getSerialNumber());
            queue.addLast(bottle);
            notEmpty.signalAll();
        } catch (InterruptedException ie) {
            throw new RuntimeException("Fejl ved Interruption i flaskekøen (input)");
        } finally {
            lock.unlock();
        }
    }

    public Bottle take(){
        lock.lock();
        try {
            while (queue.isEmpty()){
                notEmpty.await();
            }
            Bottle bottle = queue.pop();
            notFull.signalAll();
            return bottle;
        } catch (InterruptedException ie){
            throw new RuntimeException("Fejl ved Interruption i flaskekøen (output)");
        } finally {
            lock.unlock();
        }
    }



}
