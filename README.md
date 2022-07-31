### 1.项目配置
修改spring-dao.xml中的dataSource里的mysql配置
修改redis-config.xml中的jedisPool里的redis配置

### 2.项目执行
通过mvn package进行打包,将生成war包,将war包放入到Tomcat中的webapps中即可运行

![在这里插入图片描述](https://img-blog.csdnimg.cn/6553c1c4635144079b5cb82457cef5e6.png)

### 3.运行情况(在Tomcat中看不出任何效果,下面是在IDEA中执行的效果)

清理Redis中的缓存和七牛云的垃圾数据:

![在这里插入图片描述](https://img-blog.csdnimg.cn/1dfbda3ddc744724ba87ce01b0fbf2de.png)

清理数据库和七牛云中的垃圾数据:

![在这里插入图片描述](https://img-blog.csdnimg.cn/3a18b1d8404141a9bfd36f85af8f9658.png)
