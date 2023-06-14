package flight;

import java.io.Serializable;

public class WifiOption extends AddOptions implements Serializable {

    private String ans;

    public WifiOption(Options options, String ans) {
        super(options);
        this.ans = ans;
    }

    @Override
    public String getDescription() {
        return super.getDescription( ) + addWifi();
    }

    public String addWifi(){
        return "WI-FI connection: " + ans + "\n";
    }

    @Override
    public int getPrice() {
        if(ans.equals("Yes"))
            return super.getPrice( ) + 5000;
        else
            return super.getPrice( );
    }
}
