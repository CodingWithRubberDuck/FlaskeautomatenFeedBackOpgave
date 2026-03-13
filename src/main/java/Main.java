import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        SerialNumberCounter counter = new SerialNumberCounter(new ReentrantLock(true));
        Random random = new Random();


        ReentrantLock bottleLock = new ReentrantLock(true);
        BottleQueue bottleQueue = new BottleQueue(bottleLock, bottleLock.newCondition(), bottleLock.newCondition(), new ArrayDeque<>());

        Thread producerThread = new Thread(new BottleProducerThread(bottleQueue, counter, random));
        producerThread.start();

        ReentrantLock beerLock = new ReentrantLock(true);
        BeerBottleQueue beerQueue = new BeerBottleQueue(beerLock, beerLock.newCondition(), beerLock.newCondition(), new ArrayDeque<>());

        ReentrantLock waterLock = new ReentrantLock(true);
        WaterBottleQueue waterQueue = new WaterBottleQueue(waterLock, waterLock.newCondition(), waterLock.newCondition(), new ArrayDeque<>());

        Thread splitterThread = new Thread(new BottleSplitterThread(bottleQueue, beerQueue, waterQueue));
        splitterThread.start();


        Thread beerConsumer = new Thread(new BeerConsumerThread(beerQueue));
        beerConsumer.start();

        Thread waterConsumer = new Thread(new WaterConsumerThread(waterQueue));
        waterConsumer.start();
    }
}
