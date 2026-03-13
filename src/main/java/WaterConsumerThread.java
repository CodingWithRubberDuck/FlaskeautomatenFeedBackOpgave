import Model.WaterBottle;

public class WaterConsumerThread implements Runnable{
    private WaterBottleQueue waterQueue;

    public WaterConsumerThread(WaterBottleQueue waterQueue){
        this.waterQueue = waterQueue;
    }


    //For at simularer en afhentning med vogn/bil
    //Vil der i stedet tages et array med alle de nuværende flasker
    //som der er i "WaterBottleQueue"
    @Override
    public void run(){
        WaterBottle[] bottles = null;
        while (true){
            try {
                Thread.sleep(17000);
            } catch (InterruptedException ie){
                System.out.println("Fejl ved Interruption i 'BeerConsumer'");
            }
            try {
                bottles = waterQueue.takeAll();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            if (bottles != null) {
                System.out.println("\n\n\u001B[33m" + "Afhentning af Vandflasker er begyndt:");
                for (WaterBottle bottle : bottles) {
                    System.out.println("\u001B[34m  " + bottle.getText() + " med løbenummeret " + bottle.getSerialNumber() + " afhentet");
                }
                System.out.println("\n");
            }
        }
    }


}
