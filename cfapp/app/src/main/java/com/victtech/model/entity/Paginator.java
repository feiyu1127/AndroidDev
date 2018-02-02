package com.victtech.model.entity;

/**
 * Created by Richard on 2018/1/26.
 */

public class Paginator {
    private int current_page;
    private int total;
    private int last_page;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }
}
