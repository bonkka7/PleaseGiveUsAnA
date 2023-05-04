public class Ingredient {
    private int amount;
    private String ingredient;
    private String measurement;

    public Ingredient(String ing, int am, String measure){
        ingredient = ing;
        measurement = measure;
        amount = am;
    }

    public int getAmount(){
        return amount;
    }
    public String getIngredient(){
        return ingredient;
    }
    public String getMeasurement(){
        return measurement;
    }

    public String toString(){
        return (ingredient + " : " + amount + " " + measurement);
    }

}
