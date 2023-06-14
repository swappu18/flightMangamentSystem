package flight;

import java.io.Serializable;

public class BaggageOption extends AddOptions implements Serializable {

    private int bag_num;

    public BaggageOption(Options options, int bag_num) {
        super(options);
        this.bag_num = bag_num;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + addBaggage();
    }

    public String addBaggage(){
        return "Baggage: " + String.valueOf(bag_num) + "\n";
    }

    @Override
    public int getPrice() {
        return super.getPrice( ) + (bag_num * 7000);
    }
}
