import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        SerialNumberCounter counter = new SerialNumberCounter(new ReentrantLock(true));
        Random random = new Random();


        //Bottle Queue
        ReentrantLock bottleLock = new ReentrantLock(true);
        BottleQueue bottleQueue = new BottleQueue(bottleLock, bottleLock.newCondition(), bottleLock.newCondition(), new ArrayDeque<>());

        //BottleProducer
        Thread producerThread = new Thread(new BottleProducerThread(bottleQueue, counter, random));
        producerThread.start();

        //Beer Queue
        ReentrantLock beerLock = new ReentrantLock(true);
        BeerBottleQueue beerQueue = new BeerBottleQueue(beerLock, beerLock.newCondition(), beerLock.newCondition(), new ArrayDeque<>());

        //Water Queue
        ReentrantLock waterLock = new ReentrantLock(true);
        WaterBottleQueue waterQueue = new WaterBottleQueue(waterLock, waterLock.newCondition(), waterLock.newCondition(), new ArrayDeque<>());

        //Bottle Splitter
        Thread splitterThread = new Thread(new BottleSplitterThread(bottleQueue, beerQueue, waterQueue));
        splitterThread.start();

        //Beer Consumer
        Thread beerConsumer = new Thread(new BeerConsumerThread(beerQueue));
        beerConsumer.start();

        //Water Consumer
        Thread waterConsumer = new Thread(new WaterConsumerThread(waterQueue));
        waterConsumer.start();
    }
}
