import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Allows creation and editing of Recipe entities through RecipeRepository
public class RecipeService {

    //Connects to recipeRepository
    @Autowired
    private RecipeRepository recipeRepository;

    //Formats HTTP requests into function calls
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipe(Long id) {
        return recipeRepository.findById(id);
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id){ recipeRepository.deleteById(id); }
}