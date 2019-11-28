package demo.repository;

import demo.model.Restaurant;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "restaurants")
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, String> {
    @RestResource(rel = "by-name", description = @Description("Find by name"))
    public Restaurant findFirstByName(@Param("name") String name);
    
    @RestResource(rel = "find-all", description = @Description("List all restaurants"))
    public List<Restaurant> findAll();
}
