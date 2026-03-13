import Model.BeerBottle;
import Model.Bottle;
import Model.WaterBottle;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BeerBottleQueue {
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private final ArrayDeque<BeerBottle> queue;
    private final int max;

    public BeerBottleQueue(ReentrantLock lock, Condition notEmpty, Condition notFull, ArrayDeque<BeerBottle> queue){
        this.lock = lock;
        this.notEmpty = notEmpty;
        this.notFull = notFull;
        this.queue = queue;
        max = 50;
    }

    public void put(BeerBottle bottle){
        lock.lock();
        try {
            while (queue.size() == max){
                notFull.await();
            }
            queue.addLast(bottle);
            notEmpty.signalAll();
        } catch (InterruptedException ie) {
            throw new RuntimeException("Fejl ved Interruption i flaskekøen (input)");
        } finally {
            lock.unlock();
        }
    }

    public BeerBottle[] takeAll(){
        lock.lock();
        try {
            while (queue.isEmpty()){
                notEmpty.await();
            }
            int length = queue.size();
            BeerBottle[] allWaterBottles = new BeerBottle[length];
            for (int i = 0; i < length; i++){
                allWaterBottles[i] = queue.pop();
            }
            notFull.signalAll();
            return allWaterBottles;
        } catch (InterruptedException ie){
            throw new RuntimeException("Fejl ved Interruption i flaskekøen (output)");
        } finally {
            lock.unlock();
        }
    }

}
