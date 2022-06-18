package br.com.alexmdo.diningreviewapi.restaurant;

import br.com.alexmdo.diningreviewapi.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
