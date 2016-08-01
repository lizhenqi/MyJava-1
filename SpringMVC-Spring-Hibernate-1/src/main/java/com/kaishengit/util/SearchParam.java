package com.kaishengit.util;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public class SearchParam {
    private String type;
    private String propertyName;
    private Object value;


    public static List<SearchParam> buildSearchParam(HttpServletRequest request) {

        List<SearchParam> searchParamList = Lists.newArrayList();
//1.用于获取所有查询字符串
        Enumeration<String> enumeration = request.getParameterNames();

        while (enumeration.hasMoreElements()) {
            String queryString = enumeration.nextElement();
            Object value = request.getParameter(queryString);

//            q_s_like_bookname（在list.jsp中通过设置valueType，以达到不同需求）


            if (queryString.startsWith("q_") && value!=null&& StringUtils.isNotEmpty(value.toString())) {
                String[] arry = queryString.split("_",4);
                    if (arry.length != 4) {
                    throw new RuntimeException("查询格式错误" + queryString);
                }
                String valueType = arry[1];//s
                String type = arry[2];//like
                String propertyName = arry[3];//bookname

                SearchParam searchParam = new SearchParam();
                searchParam.setPropertyName(propertyName);

                value = converValueType(value, valueType);//这样的话会把value自动转成对应的类型

                searchParam.setValue(value);
                searchParam.setType(type);

                searchParamList.add(searchParam);

                request.setAttribute(queryString, value);//给请求
            }
        }

        return searchParamList;
    }

    private static Object converValueType(Object value, String valueType) {
        if ("s".equalsIgnoreCase(valueType)) {
            return Strings.toUTF8(value.toString());
        } else if ("d".equalsIgnoreCase(valueType)) {
            return Double.valueOf(value.toString());
        } else if ("f".equalsIgnoreCase(valueType)) {
            return Float.valueOf(value.toString());
        } else if ("i".equalsIgnoreCase(valueType)) {
            return Integer.valueOf(value.toString());
        } else if ("b".equalsIgnoreCase(valueType)) {
            return Boolean.valueOf(value.toString());
        }
        return null;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
