package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Food;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FoodRepository {
    private static List<Food> foodList = new ArrayList<>(
            Arrays.asList(
                    new Food(1,
                            "spagetti",
                            70,
                            10,
                            5,
                            100,
                            3,
                            100,
                            5,
                            100),

                    new Food(2,
                            "zehtin",
                            0,
                            0,
                            90,
                            900,
                            9,
                            5,
                            10,
                            200)



            ));



    public List<Food> getFoodList() {
        return foodList;
    }

    public Optional<Food> getFoodById(Integer id) {
        return foodList.stream().filter(food -> food.getId() == id).findFirst();
    }

    public Set<Food> getFoodByMultipleId(@Context UriInfo uriInfo ) {

        List<String> empIdList = uriInfo.getQueryParameters().get("id");
        List<Integer> integerList = empIdList.stream().map(Integer::parseInt).collect(Collectors.toList());
        ArrayList<Integer> arrayIntegerList = (ArrayList) integerList;

        List<Food> filtredList = new ArrayList<>();

        for(int id:arrayIntegerList)  {
            filtredList.add(getFoodById(id).orElse(null));
        }

        return new HashSet<>(filtredList.stream().filter(food -> food != null).collect(Collectors.toList()));
    }

    public Set<Food> getFoodByMultipleParams(@Context UriInfo uriInfo) {

        Collection<Predicate<Food>> listOfPredicates = new ArrayList<Predicate<Food>>();
        MultivaluedMap<String, String> multiMap = uriInfo.getQueryParameters();

        for (String fieldName : multiMap.keySet()) {

            Object value = multiMap.get(fieldName).get(0);


            if (fieldName.equals("name")) {

                listOfPredicates.add(food -> food.getName().equals(value));

            } else if (fieldName.equals("carbs")) {

                listOfPredicates.add(food -> food.getCarbs() == Integer.parseInt((String) value));

            } else if (fieldName.equals("proteins")) {

                listOfPredicates.add(food -> food.getProteins() == Integer.parseInt((String) value));

            } else if (fieldName.equals("fats")) {

                listOfPredicates.add(food -> food.getFats() == Integer.parseInt((String) value));

            } else if(fieldName.equals(("unit"))) {

                listOfPredicates.add(food -> food.getUnit() == Integer.parseInt((String) value));

            } else if(fieldName.equals("calsPerUnit")) {

                listOfPredicates.add(food -> food.getCalsPerUnit() == Integer.parseInt((String) value));

            } else if(fieldName.equals("glycemicIndex")) {

                listOfPredicates.add(food -> food.getGlycemicIndex() == Integer.parseInt((String) value));

            } else if(fieldName.equals("price")) {

                listOfPredicates.add(food -> food.getPrice() == Integer.parseInt((String) value));

            } else if(fieldName.equals("vitaminA")) {

                listOfPredicates.add(food -> food.getVitaminA() == Integer.parseInt((String) value));

            }
        }

        return new HashSet<>(foodList.stream().filter(
                food -> listOfPredicates.stream().allMatch(predicate -> predicate.test(food))
        ).collect(Collectors.toList()));
    }
    public Food saveFood(Food food) {
        foodList.add(food);
        return food;
    }

    public Food updateFood(Integer id, Food food) {
        int realId = id - 1;
        foodList.set(realId, food);
        return food;
    }

    public void deleteFood(Integer id) {
        foodList.removeIf(it -> it.getId() == id);
    }


}



