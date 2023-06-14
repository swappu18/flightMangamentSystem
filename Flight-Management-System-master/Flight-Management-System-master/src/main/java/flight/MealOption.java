package flight;

import java.io.Serializable;

public class MealOption extends AddOptions implements Serializable {

    private String mealType;

    public MealOption(Options options, String mealType) {
        super(options);
        this.mealType = mealType;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + addMeal();
    }

    public String addMeal(){
        return "Meal Type: " + mealType + "\n";
    }

    @Override
    public int getPrice() {
        if(mealType.equals("Standard"))
            return super.getPrice( );
        else
            return super.getPrice( ) + 10000;
    }
}
