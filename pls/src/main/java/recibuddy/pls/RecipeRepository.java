import org.springframework.data.jpa.repository.JpaRepository;

//Makes RecipeRepository capable of accessing and modifying Recipe entities
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}