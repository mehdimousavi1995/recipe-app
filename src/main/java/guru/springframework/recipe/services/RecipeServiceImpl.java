package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipesSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipesSet::add);
        return recipesSet;
    }
}
