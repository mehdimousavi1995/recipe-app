package guru.springframework.recipe.services;

import guru.springframework.recipe.converters.RecipeCommandToRecipe;
import guru.springframework.recipe.converters.RecipeToRecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.excpetions.NotFoundException;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeService recipeService;


    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUpRecipe() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeById() {
        Optional<Recipe> recipe = Optional.of(new Recipe());
        when(recipeRepository.findById(anyLong())).thenReturn(recipe);

        Recipe recipeReturned =  recipeService.findById(1L);

        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();


    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdNotFound() {
        Optional<Recipe> optionalRecipe = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe recipe = recipeService.findById(1L);

    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);

        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void deleteRecipeById() {
        Long recipeToDelete = 2L;

        recipeService.deleteById(recipeToDelete);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}