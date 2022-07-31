import com.dudu.dao.DishMapper;
import com.dudu.dao.SetmealMapper;
import com.dudu.entity.Dish;
import com.dudu.entity.Setmeal;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MybatisTest {
    @Test
    public void FinAllDelDish() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        DishMapper mapper = (DishMapper) context.getBean("DishMapper");
        List<Dish> list = mapper.FinAllDelDish();
        for (Dish dish : list) {
            System.out.println(dish.toString());
        }

        System.out.println("===============");
        // 删除套餐及套餐详情
        SetmealMapper setmealMapper = (SetmealMapper) context.getBean("SetmealMapper");
        // 获取需要删除的集合
        List<Setmeal> setmealList = setmealMapper.FinAllDelSetmeal();
        for (Setmeal setmeal : setmealList) {
            System.out.println(setmeal.toString());
        }
    }
}
