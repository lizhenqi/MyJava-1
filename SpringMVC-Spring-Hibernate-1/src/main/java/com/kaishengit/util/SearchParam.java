package com.kaishengit.util;



import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

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


    public static List<SearchParam> buildSearchParam(HttpServletRequest request){

        List<SearchParam> searchParamList = Lists.newArrayList();
//1.用于获取所有查询字符串
        Enumeration<String> enumeration= request.getParameterNames();

        while (enumeration.hasMoreElements()){
            String queryString=enumeration.nextElement();
            String value=request.getParameter(queryString);
            if(queryString.startsWith("q_")&& StringUtils.isNotEmpty(value)){
                String[] arry=queryString.split("_");
                if(arry.length!=3){
                    throw new RuntimeException("查询格式错误"+queryString);
                }
                String type = arry[1];
                String propertyName = arry[2];

                SearchParam searchParam = new SearchParam();
                searchParam.setPropertyName(propertyName);
                searchParam.setValue(Strings.toUTF8(value));
                searchParam.setType(type);

                searchParamList.add(searchParam);
            }
        }

        return searchParamList;
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
