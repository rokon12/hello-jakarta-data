package org.eclipse.jakarta.hello;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CoffeeService {
    @Inject
    private CoffeeRepository coffeeRepository;

    public void addCoffee(Coffee coffee) {
        coffeeRepository.add(coffee);
    }

    public Coffee getCoffeeById(Long id) {
        return coffeeRepository.findById(id);
    }

    public List<Coffee> getCoffeesByTypeAndMinRating(CoffeeType type, double minRating) {
        return coffeeRepository.findByTypeAndMinRating(type, minRating);
    }

    public List<Coffee> getAffordableCoffees(double maxPrice, Double minRating) {
        return coffeeRepository.findAffordableCoffees(maxPrice, minRating);
    }

    @Transactional
    public void bulkUpdatePrice(CoffeeType type, double newPrice) {
        coffeeRepository.updatePriceByType(type, newPrice);
    }

    @Transactional
    public void removeLowRatedCoffees(double minRating) {
        coffeeRepository.deleteLowRatedCoffees(minRating);
    }

    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }
}
