package taco.data;

import taco.domain.Ingredient;

public interface IngredientRepository {

    Iterable <Ingredient> findAll();

    Ingredient findOneById(String id);

    Ingredient save(Ingredient ingredient);
}
