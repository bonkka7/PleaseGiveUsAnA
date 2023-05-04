package recibuddy.pls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

//Makes RecipeRepository capable of accessing and modifying Recipe entities
public interface RecipeRepository<Recipe> extends JpaRepository<Recipe, Long>, ListCrudRepository<Recipe,Long> {

}