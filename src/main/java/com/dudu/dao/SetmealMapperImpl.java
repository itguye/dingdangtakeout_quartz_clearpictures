package com.dudu.dao;

import com.dudu.entity.Dish;
import com.dudu.entity.DishFlavor;
import com.dudu.entity.Setmeal;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class SetmealMapperImpl implements SetmealMapper{
    //sqlSession不用我们自己创建了，Spring来管理
    private SqlSessionTemplate sqlSession;
    @Override
    public void removeSetmealById(Long id) {
        SetmealMapper dishMapper = sqlSession.getMapper(SetmealMapper.class);
        dishMapper.removeSetmealById(id);
    }

    @Override
    public void removeSetmealFlavorByDishId(Long dishId) {
        SetmealMapper dishMapper = sqlSession.getMapper(SetmealMapper.class);
        dishMapper.removeSetmealFlavorByDishId(dishId);
    }

    @Override
    public List<Setmeal> FinAllDelSetmeal() {
        SetmealMapper dishMapper = sqlSession.getMapper(SetmealMapper.class);
        return dishMapper.FinAllDelSetmeal();
    }

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

}
