package com.dudu.dao;

import com.dudu.entity.Dish;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class DishMapperImpl implements DishMapper{
    //sqlSession不用我们自己创建了，Spring来管理
    private SqlSessionTemplate sqlSession;

    @Override
    public void removeDishById(Long id) {
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        dishMapper.removeDishById(id);
    }

    @Override
    public void removeDishFlavorByDishId(Long dishId) {
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        dishMapper.removeDishFlavorByDishId(dishId);
    }

    @Override
    public List<Dish> FinAllDelDish() {
        DishMapper dishMapper = sqlSession.getMapper(DishMapper.class);
        return  dishMapper.FinAllDelDish();
    }


    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
}
