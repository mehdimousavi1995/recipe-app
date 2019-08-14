package guru.springframework.recipe.controllers;

import guru.springframework.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private RecipeService recipeRepository;

    public IndexController(RecipeService recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndex(Model model) {
        model.addAttribute("recipes", recipeRepository.getRecipes());
        return "index";
    }
}
