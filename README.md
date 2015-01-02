###IMPORTANT

googlecode的repo被ZF屏蔽了，如果在跑create-db.bat的时候遇到依赖包无法下载之类的问题问Vincent或者周鑫要jar包放到本地maven仓库即可。

本地参考运行环境 （Vincent）： 
* java 1.6.0_30
* Maven 3.0.5
* MySQL 5.6 
MySQL-Front可以作为MySQL的可视化编程工具，安装MySQL的时候端口用默认的3306

POM里的依赖关系和项目名称/路径的前缀已经改成了hiring, 部署好之后访问 localhost:9080/hiring-web/ 初始化项目


##如何运行

####1、到web/pom.xml修改数据库配置：
*  默认修改：profiles/profile/development下的
*  connection.admin.url (默认不需要改动)
*  connection.username
*  connection.password

####2、到项目的根下(hiring)
* cd bin
* install.bat 安装jar包到本地仓库，或者在根目录执行过mvn clean install则无需运行installer.bat (需要跳过测试)
* create-db.bat 创建数据库（mysql需要5.5及以上 编码为utf-8）
* refresh-db.bat 创建schema和初始化data
* jetty.bat 启动web应用 默认端口9080 可以到web/pom.xml下修改（servlet 2.5即可）
* 系统默认帐户是admin/123456

####3、注意
如果你是用mvn jetty:run启动项目，默认会执行speed-up 应用，不过可以到src/main/resources/spring-speed-up.xml中把profile="development"改成任意其他的即可，或者删除<br/>
请参考<a href='http://jinnianshilongnian.iteye.com/blog/1883013'>加速spring/hibernate应用调试时启动速度</a>
