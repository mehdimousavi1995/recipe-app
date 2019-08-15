package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.converters.RecipeCommandToRecipe;
import guru.springframework.recipe.converters.RecipeToRecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipesSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipesSet::add);
        return recipesSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optRecipe = recipeRepository.findById(id);
        if(optRecipe.isPresent()) {
            return optRecipe.get();
        } else throw new RuntimeException("Recipe Not Found!");
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe recipe = recipeRepository.save(recipeCommandToRecipe.convert(command));
        log.debug("Saved RecipeId: ", recipe.getId());
        return recipeToRecipeCommand.convert(recipe);
    }


}
