package com.cafemetrix.cafelab.coffees.application.acl;

import com.cafemetrix.cafelab.coffees.domain.model.commands.CreateCoffeeCommand;
import com.cafemetrix.cafelab.coffees.domain.model.queries.GetCoffeeByIdQuery;
import com.cafemetrix.cafelab.coffees.domain.services.CoffeeCommandService;
import com.cafemetrix.cafelab.coffees.domain.services.CoffeeQueryService;
import com.cafemetrix.cafelab.coffees.interfaces.acl.CoffeesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class CoffeesContextFacadeImpl implements CoffeesContextFacade {
    private final CoffeeCommandService coffeeCommandService;
    private final CoffeeQueryService coffeeQueryService;

    public CoffeesContextFacadeImpl(CoffeeCommandService coffeeCommandService, CoffeeQueryService coffeeQueryService) {
        this.coffeeCommandService = coffeeCommandService;
        this.coffeeQueryService = coffeeQueryService;
    }

    @Override
    public Long createCoffee(String name, String region, String variety, Double totalWeight) {
        var createCoffeeCommand = new CreateCoffeeCommand(name, region, variety, totalWeight);
        var coffee = coffeeCommandService.handle(createCoffeeCommand);
        return coffee.isEmpty() ? 0L : coffee.get().getId();
    }

    @Override
    public Long fetchCoffeeIdByName(String name) {
        var getCoffeeByIdQuery = new GetCoffeeByIdQuery(0L); // Aquí necesitarías implementar la lógica para buscar por nombre
        var coffee = coffeeQueryService.handle(getCoffeeByIdQuery);
        return coffee.isEmpty() ? 0L : coffee.get().getId();
    }
}