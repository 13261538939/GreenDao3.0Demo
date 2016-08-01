# DaoDemo 
本Demo是GreenDao3.0的简单操作，包括常见的增删查改，

作为ORM类型的第三方库，GreenDao已被广大的Android开发者所使用，毕竟官方宣称是the fastest.

 GreenDao3.0较之前的版本有了很大的改变，主要不同点体现在以下两个方面：

1. DaoMaster、DaoSession等类的产生不再依赖单独的java的工程了，而是换成了Gradle插件来自动生成用户需要的Dao(其实Gradle插件内部
   封装了Generator,与之前的版本本质是一样的，封装之后方便开发者使用，配合ASS是比较爽的)
   
2. GreenDao3.0采用注解的方式定义实体类（Entity），然后在编译的时候自动生成与实体类对应的Dao类。


注意：配置的时候，以下的配置信息是放在App目录下的build.gradle文件中的，而不是外部的build.gradle文件
  
     buildscript {
        repositories {
             mavenCentral()
      }
         dependencies {
           classpath 'org.greenrobot:greendao-gradle-plugin:3.0.0'
       }
    }
    
     apply plugin: 'org.greenrobot.greendao'

     dependencies {
        compile 'org.greenrobot:greendao:3.0.1'
    }
   

