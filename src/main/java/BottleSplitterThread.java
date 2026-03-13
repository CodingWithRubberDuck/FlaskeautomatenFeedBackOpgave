import Model.BeerBottle;
import Model.Bottle;
import Model.WaterBottle;

public class BottleSplitterThread implements Runnable{
    private final BottleQueue bottleQueue;
    private final BeerBottleQueue beerQueue;
    private final WaterBottleQueue waterQueue;

    public BottleSplitterThread(BottleQueue bottleQueue, BeerBottleQueue beerQueue, WaterBottleQueue waterQueue){
        this.bottleQueue = bottleQueue;
        this.beerQueue = beerQueue;
        this.waterQueue = waterQueue;
    }

    @Override
    public void run(){
        while (true){
            Bottle bottle = null;
            try {
                bottle = bottleQueue.take();
            } catch (RuntimeException re){
                System.out.println(re.getMessage());
            }
            if (bottle instanceof BeerBottle){
                beerQueue.put((BeerBottle) bottle);
            } else if (bottle instanceof WaterBottle) {
                waterQueue.put((WaterBottle) bottle);
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }

        }
    }
}
