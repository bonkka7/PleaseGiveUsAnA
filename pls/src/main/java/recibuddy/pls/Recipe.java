import javax.persistence.*;

@Entity
@Table(name = "Recipe")

//Creates Recipe entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "recipeName")
    private String recipeName;

    @Column(name = "ingredients")
    private String[] ingredients;

    @Column(name = "utensils")
    private String[] utensils;

    @Column(name = "description")
    private String description;

    @Column(name = "cookTime")
    private String cookTime;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "servingSize")
    private int servingSize;

    @Column(name = "imgURL")
    private String imgURL;

    @Column(name = "meal")
    private String meal;

    // constructor, getters, and setters
    public Recipe() {}

    public Recipe(String[] ingredients, String[] utensils, String description, String cookTime, String instructions, int servingSize, String imgURL, String meal) {
        this.ingredients = ingredients;
        this.utensils = utensils;
        this.description = description;
        this.cookTime = cookTime;
        this.instructions = instructions;
        this.servingSize = servingSize;
        this.imgURL = imgURL;
        this.meal = meal;
    }

    // getters and setters
    //I'mma have to make ingredients and utensils arrays rather than singular strings.
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String[] getIngredients() {
        return ingredients;
    }
    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getUtensils() {
        return utensils;
    }
    public void setUtensils(String[] utensils) {
        this.utensils = utensils;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCookTime() {
        return cookTime;
    }
    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public int getServingSize() {
        return servingSize;
    }
    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getImgURL() {
        return imgURL;
    }
    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getMeal() {
        return meal;
    }
    public void setMeal(String meal) {
        this.meal = meal;
    }

    // toString method
    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", ingredients='" + ingredients + '\'' +
                ", utensils='" + utensils + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

  /*  public static void main(String[] args){
        Recipe burger = new Recipe("cheese", "fingers", "cheese", "eat", "instant", "all you want bby", "cheesepicture", "dinner");
        System.out.println(burger.toString());
    }
}*/