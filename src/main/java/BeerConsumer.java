import Model.BeerBottle;

public class BeerConsumer implements Runnable {
    private BeerBottleQueue beerQueue;

    public BeerConsumer(BeerBottleQueue beerQueue){
        this.beerQueue = beerQueue;
    }


    //For at simularer en afhentning med vogn/bil
    //Vil der i stedet tages et array med alle de nuværende flasker
    //som der er i "BeerBottleQueue"
    @Override
    public void run(){
        BeerBottle[] bottles = null;
        while (true){
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ie){
                System.out.println("Fejl ved Interruption i 'BeerConsumer'");
            }
            try {
                bottles = beerQueue.takeAll();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            if (bottles != null) {
                System.out.println("\n\n\u001B[33m" + "Afhentning af Ølflasker er begyndt:");
                for (BeerBottle bottle : bottles) {
                    System.out.println("\u001B[32m  " + bottle.getText() + " med løbenummeret " + bottle.getSerialNumber() + " afhentet");
                }
                System.out.println("\n");
            }
        }
    }


}
