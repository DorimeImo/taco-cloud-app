package taco.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import taco.data.IngredientRepository;
import taco.data.TacoRepository;
import taco.domain.Ingredient;
import taco.domain.Ingredient.Type;
import taco.domain.Order;
import taco.domain.Taco;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    @ModelAttribute
    public void addAttributes(Model model) {
        if(model.getAttribute("order")==null)
        model.addAttribute("order",new Order());
        model.addAttribute("taco", new Taco());
    }

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository,
                                TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){

        model= populateModelWithIngredients(model);
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, ArrayList<Ingredient> list, Model model) {
        Order order = (Order) model.getAttribute("order");
        log.info(design.toString());
        log.info(order.toString());
        list.forEach(x->System.out.println(x.toString()));

        if (errors.hasErrors()) {
            populateModelWithIngredients(model);
            return "design";
        }
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> getIngredients(){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        return ingredients;
    }

    private List<Ingredient> filterByType(
      List <Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    private Model populateModelWithIngredients(Model model){
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(getIngredients(), type));
        }
        return model;
    }

}
