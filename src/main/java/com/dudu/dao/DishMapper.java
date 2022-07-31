package com.dudu.dao;

import com.dudu.entity.Dish;

import java.util.List;

public interface DishMapper {
    void removeDishById(Long id);
    void removeDishFlavorByDishId(Long dishId);
    List<Dish> FinAllDelDish();
}
