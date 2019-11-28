package demo.service;


import java.util.List;

import demo.model.Restaurant;

public interface RestaurantService {
    void createRestaurant(Restaurant restaurant);
    Restaurant getRestaurantByName(String name);
    List<Restaurant> listAllRestaurants();
}
