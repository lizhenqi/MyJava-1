package test.com.kaisehngit;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/7/28.
 */
public class Father<T,PK> {


//    *************************************************************重要
    public Father(){
//        System.out.println("Father"+this);
        Class<?> clzz=this.getClass();//Son
//        System.out.println(clzz.getSuperclass());//Father

        ParameterizedType type= (ParameterizedType) clzz.getGenericSuperclass();//能获取Father的泛型T,PK是什么类型
        Type[] param=type.getActualTypeArguments();//泛型数组
        Class<?> msg= (Class<?>) param[0];//第一个T的类型
        System.out.println(msg);

    }
}
