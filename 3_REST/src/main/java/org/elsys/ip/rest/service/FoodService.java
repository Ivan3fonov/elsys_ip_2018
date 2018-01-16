package org.elsys.ip.rest.service;

import org.elsys.ip.rest.model.Food;
import org.elsys.ip.rest.repository.FoodRepository;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Set;

public class FoodService {

    private FoodRepository foodRepository = new FoodRepository();


    public List<Food> getFoodList() {
        return foodRepository.getFoodList();
    }

    public Food getFoodById(Integer id) {
        return foodRepository.getFoodById(id).orElse(null);
    }

    public Set<Food> getFoodListByMyltipleId(@Context UriInfo uriInfo) {
        return foodRepository.getFoodByMultipleId(uriInfo);
    }

    public Set<Food> getFoodByMultipleParams (@Context UriInfo uriInfo) {
        return foodRepository.getFoodByMultipleParams(uriInfo);
    }
    public Food saveFood(Food food) {
        return foodRepository.saveFood(food);
    }

    public Food updateFood(Integer id, Food food) {
        return foodRepository.updateFood(id, food);
    }

    public void deleteFood(Integer id) {
        foodRepository.deleteFood(id);
    }

}
