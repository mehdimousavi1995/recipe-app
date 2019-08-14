package guru.springframework.recipe.repositories;

import guru.springframework.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

interface CategoryRepository extends CrudRepository<Category, Long> {
}
