package flight;

import java.io.Serializable;

public class OptionsImpl implements Options, Serializable {

    private int price;

    public OptionsImpl(int price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return "Options: \n";
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
