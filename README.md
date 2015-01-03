###IMPORTANT

因为目录结构正在更新中，主要是去掉项目原先的各种独有包路径，hiring-common已完成路径重构，hiring-web正在进行。

googlecode的repo被ZF屏蔽了，如果在跑create-db.bat的时候遇到依赖包无法下载之类的问题问Vincent或者周鑫要jar包放到本地maven仓库即可。

本地参考运行环境 （Vincent）： 
* java 1.6.0_30
* Maven 3.0.5
* MySQL 5.6 
* MySQL-Front可以作为MySQL的可视化编程工具，安装MySQL的时候端口用默认的3306

POM里的依赖关系和项目名称/路径的前缀已经改成了hiring, 部署好之后访问 localhost:9080/hiring-web/ 初始化项目。
hiring-web模块的UT需要创建完数据库和导入数据之后才能跑，所有编译操作要跳过UT来跑

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


##使用Eclipse对jetty进行debug (plugin都在pom里面配好了)
####1.进入Run/External Tools/External Tools Configuration目录，选择“Program”并点击“New”按钮。在“Main”标签中的“Location”中设置mvn的执行路径（比如/JavaDev/apache-maven-3.0.3/bin/mvn），在“Working Directory”中选择webapp所属的workspace，在“Arguments”中添加“jetty:run”，
在“Environment”标签中，点击“New”按钮，添加变量：MAVEN_OPTS=-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8010,server=y,suspend=y
如果设置suspend=n，则运行时不需要等待debugger。

####2. 进入“Run/Debug/Debug Configuration”目录，选择“Remote Java Application”并点击“New”按钮，在“Project”中选择webapp项目。并确保端口号与步骤一中“address=”设置的值相同。
关联源码：选择“Source”标签，选择“Add…”按钮添加关联的源代码，选择“Java Project”关联hiring-common和hiring-web的源码。源代码关联后，即可进行调试。为了关闭jetty，建议选中步骤二中的“Allow termination of remote VM”

####3.配置完毕，开始调试：
*1. 在“Run/External Tools”中选择步骤一中创建的“Program”名称，开始运行插件，如果设置了suspend=y，则会等待下面的debugger运行；
*2. 在“Run/Debug”中选择步骤二中创建的debugger，连接上后，即可以进行debug。
*3. 关闭Jetty
