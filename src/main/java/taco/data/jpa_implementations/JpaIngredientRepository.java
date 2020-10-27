package taco.data.jpa_implementations;

import org.springframework.data.repository.CrudRepository;
import taco.data.IngredientRepository;
import taco.domain.Ingredient;

public interface JpaIngredientRepository extends CrudRepository<Ingredient, String>, IngredientRepository {


}
