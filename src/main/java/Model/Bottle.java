package Model;

public abstract class Bottle {
    private String text;
    private int serialNumber;

    public Bottle (String text, int serialNumber){
        this.text = text;
        this.serialNumber = serialNumber;
    }

    public int getSerialNumber(){
        return serialNumber;
    }

    //Text er bare for at bevise at sorteringen sker ordentligt, da der egentligt sorteres baseret på instanceof
    public String getText(){
        return text;
    }
}
