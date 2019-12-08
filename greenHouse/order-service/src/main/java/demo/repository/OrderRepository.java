package demo.repository;

import demo.model.Order;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "orders")
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {
    @RestResource(rel = "find-by-id", description = @Description("Find by id"))
    public Order findFirstById(@Param("id") String id);

    @RestResource(rel = "delete-by-id", description = @Description("Delete by id"))
    public void delete(@Param("id") String id);

    @RestResource(rel = "create", description = @Description("Create"))
    public Order save(@Param("order") Order order);
    
    @RestResource(rel = "find-all", description = @Description("List all orders"))
    public List<Order> findAll();
}
