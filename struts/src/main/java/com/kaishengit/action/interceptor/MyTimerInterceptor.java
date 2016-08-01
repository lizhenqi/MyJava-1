package com.kaishengit.action.interceptor;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Created by Administrator on 2016/8/2.
 */
public class MyTimerInterceptor extends AbstractInterceptor {
//AbstractInterceptor就是一个适配器模式的实现

//    在struts.xml中需要先把拦截器定义出来

//    这里面可以各句需要实现初始化和摧毁


    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionProxy actionProxy = invocation.getProxy();//当前调用那个action，就是谁的代理对象

        String namespace = actionProxy.getNamespace(); //获取目标对象的namespace
        String methodName = actionProxy.getMethod(); //目标对象执行的方法名称
        String name = actionProxy.getActionName(); // 目标对象在URL中请求的名称

        long stratTime = System.currentTimeMillis();//获取当前时间戳
        String result = actionProxy.execute(); //目标对象（Action）方法的执行
        long endTime = System.currentTimeMillis();

        System.out.println((namespace + "/" + name) + " 方法：" + methodName + "耗费:" + (endTime - stratTime) + "ms");

        return result;
    }
}
