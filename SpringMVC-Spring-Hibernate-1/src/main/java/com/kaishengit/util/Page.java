package com.kaishengit.util;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class Page<T> {
    private Integer size;
    private Integer totalSize;
    private Integer pageNo;
    private Integer totalPage;
    private List<T> items;

    /**
     * 获取当前页起始行数
     */
    private Integer start;

    /**
     *
     * @param size 每页显示条数
     * @param totalSize 总记录
     * @param pageNo 当前第几页
     */
    public Page(Integer size, Integer totalSize, Integer pageNo) {
        this.size = size;
        this.totalSize = totalSize;
        this.pageNo = pageNo;

        totalPage=totalSize/size;

        if(totalSize % size!=0){
            totalPage=totalPage+1;
        }
        if(this.pageNo<=0){
            this.pageNo=1;//注意下面的几个pageNo要有this,因为传值过来后，得判断，不满足的要重新赋值给本类即：this.pageNo=xx;
        }
        if(pageNo>totalPage){
            this.pageNo=totalPage;
        }
//当前页的起始条数
        start=(this.pageNo-1)*size;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getTotalSize() {
        return totalSize;
    }
}
