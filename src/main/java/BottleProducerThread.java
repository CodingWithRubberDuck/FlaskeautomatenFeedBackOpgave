import Model.BeerBottle;
import Model.WaterBottle;

import java.util.Random;

public class BottleProducerThread implements Runnable{
    private final BottleQueue queue;
    private final SerialNumberCounter counter;
    private final Random random;

    public BottleProducerThread(BottleQueue queue, SerialNumberCounter counter, Random random){
        this.queue = queue;
        this.counter = counter;
        this.random = random;
    }

    @Override
    public void run(){
        int randomBottle;
        while (true){
            randomBottle = random.nextInt(0,2);
            switch (randomBottle){
                case 0: queue.put(new BeerBottle("øl", counter.incrementAndGet()));
                break;
                case 1: queue.put(new WaterBottle("vand" , counter.incrementAndGet()));
                break;
            }
            try {
                // Thread.sleep i producer vil i sidste ende altid blive constrained af BottleQueue's Maksimum længde,
                // Som derfor er påvirket/afhænger af Thread.sleep i SplitterThread
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
