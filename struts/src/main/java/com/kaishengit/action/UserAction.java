package com.kaishengit.action;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/8/2.
 */
public class UserAction extends BaseAction {


    public String execute() {
        HttpSession session= getHttpSession();

        return SUCCESS;
    }




}
