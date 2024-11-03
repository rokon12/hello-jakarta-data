package org.eclipse.jakarta.hello;

import jakarta.data.repository.*;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface CoffeeRepository {
    @Insert
    void add(Coffee coffee);

    @Update
    void update(Coffee coffee);

    @Delete
    void remove(Coffee coffee);

    @Find
    Coffee findById(Long id);

    @Find
    List<Coffee> findAll();

    @Query("SELECT c FROM Coffee c WHERE c.type = :type AND c.rating >= :minRating")
    List<Coffee> findByTypeAndMinRating(CoffeeType type, double minRating);

    @Query("SELECT c FROM Coffee c WHERE c.price <= :maxPrice AND c.rating >= COALESCE(:minRating, 1.0)")
    List<Coffee> findAffordableCoffees(double maxPrice, Double minRating);

    @Transactional
    @Query("UPDATE Coffee c SET c.price = :newPrice WHERE c.type = :type")
    int updatePriceByType(CoffeeType type, double newPrice);

    @Transactional
    @Query("DELETE FROM Coffee c WHERE c.rating < :minRating")
    int deleteLowRatedCoffees(double minRating);
}
