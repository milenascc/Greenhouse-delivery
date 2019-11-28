package demo.service.impl;

import demo.model.Restaurant;
import demo.repository.RestaurantRepository;
import demo.service.RestaurantService;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RestaurantServiceImpl.class);
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        Restaurant restaurant = restaurantRepository.findFirstByName(name);
        return restaurant;
    }

	@Override
	public List<Restaurant> listAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		return restaurants;
	}
}
