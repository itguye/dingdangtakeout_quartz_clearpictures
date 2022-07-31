package com.dudu.dao;

import com.dudu.entity.Dish;
import com.dudu.entity.Setmeal;

import java.util.List;

public interface SetmealMapper {
    void removeSetmealById(Long id);
    void removeSetmealFlavorByDishId(Long dishId);
    List<Setmeal> FinAllDelSetmeal();
}
