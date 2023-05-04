package recibuddy.pls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/recipes")
//Handles HTTP Requests for Recipe
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;
    //Calls recipeService to execute commands
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/allRecipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/returnRecipe")
    public Optional<Recipe> getRecipeById(@PathVariable("id") Long id) {
        return recipeRepository.findById(id);
    }

    @PostMapping("/storeRecipe")
    public Recipe saveRecipe(@RequestBody Recipe recipe) {
        return recipeService.saveRecipe(recipe);
    }

    @DeleteMapping("/delete")
    public void deleteRecipe(@PathVariable Long id) { recipeService.deleteRecipe(id); }
}