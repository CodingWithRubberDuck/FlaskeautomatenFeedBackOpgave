import java.util.concurrent.locks.Lock;

public class SerialNumberCounter {
    private final Lock lock;
    private int currentSerialNumber;

    public SerialNumberCounter(Lock lock){
        this.lock = lock;
        currentSerialNumber = 0;
    }

    public int incrementAndGet(){
        lock.lock();
        try {
            currentSerialNumber++;
            return currentSerialNumber;
        } finally {
            lock.unlock();
        }
    }
}
