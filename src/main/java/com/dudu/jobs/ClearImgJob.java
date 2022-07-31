package com.dudu.jobs;

import com.dudu.dao.DishMapper;
import com.dudu.dao.SetmealMapper;
import com.dudu.entity.Dish;
import com.dudu.entity.Setmeal;
import com.dudu.utiles.QiniuUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

public class ClearImgJob {
    @Resource
    private JedisPool jedisPool;
    // 清理Redis缓存的数据
    public void clearImg_redis(){
        System.out.println("==========开始清理缓存中的垃圾数据==========");
        Set<String> set =jedisPool.getResource().sdiff("PicResources", "PicDbResources");
        System.out.println("dishPicResources:"+jedisPool.getResource().scard("PicResources"));
        System.out.println("disPicDbResources:"+jedisPool.getResource().scard("PicDbResources"));
        if(set != null){
            for (String picName : set) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(picName);
                //从Redis集合中删除图片名称
                jedisPool.getResource().srem("PicResources",picName);
                System.out.println("清理垃圾:"+picName);
            }
        }
    }

    // 清理数据库中的垃圾数据
    public void clearImg_databases() {
        System.out.println("==========开始清理数据库中的垃圾数据==========");
        // 删除菜品及菜品详情
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        DishMapper mapper = (DishMapper) context.getBean("DishMapper");
        // 获取需要删除的集合
        List<Dish> dishList = mapper.FinAllDelDish();
        // 先删除数据库中的数据
        for (Dish dish : dishList) {
            // 先删除数据库中的数据
            mapper.removeDishFlavorByDishId(dish.getId());
            mapper.removeDishById(dish.getId());
            // 删除七牛云上的图片
            if (dish.getImage() != null) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(dish.getImage());
                System.out.println("清理七牛云中的垃圾:"+dish.getImage());
            }

            System.out.println("清理数据库中的垃圾:"+dish.getImage());
        }

        // 删除套餐及套餐详情
        SetmealMapper setmealMapper = (SetmealMapper) context.getBean("SetmealMapper");
        // 获取需要删除的集合
        List<Setmeal> setmealList = setmealMapper.FinAllDelSetmeal();
        // 先删除数据库中的数据
        for (Setmeal setmeal : setmealList) {
            // 先删除数据库中的数据
            setmealMapper.removeSetmealFlavorByDishId(setmeal.getId());
            setmealMapper.removeSetmealById(setmeal.getId());
            // 删除七牛云上的图片
            if (setmeal.getImage() != null) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(setmeal.getImage());
                System.out.println("清理七牛云中的垃圾:"+setmeal.getImage());
            }

            System.out.println("清理垃圾:"+setmeal.getImage());
        }

    }
}
