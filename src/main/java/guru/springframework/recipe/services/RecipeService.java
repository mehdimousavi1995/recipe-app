package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();
}
