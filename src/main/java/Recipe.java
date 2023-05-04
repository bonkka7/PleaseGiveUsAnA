import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe {
    private int ID;
    private String user;
    private String name;
    private String description;
    private String picture;
    private char meal;
    private int time;
    private int servings;
    private int avgRate;
    private String instructions;
    private List<Ingredient> ingredients;
    private List<String> utensils;

    public Recipe(int id, String use, String n, String desc, String pic, char m, int tm, int serve, int rt, String instr, List<Ingredient> ing, List<String> uts){
        ID = id;
        user = use;
        name = n;
        description = desc;
        picture = pic;
        meal = m;
        time = tm;
        servings = serve;
        avgRate = rt;
        instructions = instr;
        ingredients = ing;
        utensils = uts;
    }
    public Recipe(int id, String use, String n, String desc, String pic, char m, int tm, int serve, int rt, String instr){
        ID = id;
        user = use;
        name = n;
        description = desc;
        picture = pic;
        meal = m;
        time = tm;
        servings = serve;
        avgRate = rt;
        instructions = instr;
        ingredients = new ArrayList<>();
        utensils = new ArrayList<>();
    }

    public void addIngredient(Ingredient ing){ingredients.add(ing);}
    public void addUtensil(String ut){utensils.add(ut);}

    public int getID() {
        return ID;
    }

    public String getUser() {
        return user;
    }

    public String getName() {
        return name;
    }
    public char getMeal(){
        return meal;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getAvgRate() {
        return avgRate;
    }

    public int getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getUtensils() {
        return utensils;
    }

    public int getServings() {
        return servings;
    }
    public boolean equal(Recipe recipe){
        if(this.ID == recipe.getID() && Objects.equals(this.user, recipe.getUser())){
            return true;
        }
        return false;
    }
}
