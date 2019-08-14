package guru.springframework.recipe;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeService;
import guru.springframework.recipe.services.RecipeServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Set;

@SpringBootApplication
public class RecipeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(RecipeApplication.class, args);

        RecipeService recipeService = ctx.getBean(RecipeService.class);
        Set<Recipe> it = recipeService.getRecipes();
        it.forEach(recipe -> {
            System.out.println(recipe.getId());
            System.out.println(recipe.getDescription());
        });
    }

}
