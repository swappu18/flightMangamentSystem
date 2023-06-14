package flight;

import java.io.Serializable;

public abstract class AddOptions implements Options, Serializable {
    private Options options;

    public AddOptions(Options options) {
        this.options = options;
    }

    @Override
    public String getDescription() {
        return options.getDescription();
    }

    @Override
    public int getPrice() {
        return options.getPrice();
    }
}
