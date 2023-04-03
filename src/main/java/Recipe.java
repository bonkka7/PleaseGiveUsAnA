import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private int ID;
    private String name;
    private String description;
    private String picture;
    private int time;
    private int avgRate;
    private List<String> ingredients;
    private List<String> utensils;

    public Recipe(int id, String n, String desc, String pic, int tm, int rt, List<String> ing, List<String> uts){
        ID = id;
        name = n;
        description = desc;
        picture = pic;
        time = tm;
        avgRate = rt;
        ingredients = ing;
        utensils = uts;
    }
    public Recipe(int id, String n, String desc, String pic, int tm, int rt){
        ID = id;
        name = n;
        description = desc;
        picture = pic;
        time = tm;
        avgRate = rt;
        ingredients = new ArrayList<>();
        utensils = new ArrayList<>();
    }

    public void addIngredient(String ing){ingredients.add(ing);}
    public void addUtensil(String ut){utensils.add(ut);}


}
