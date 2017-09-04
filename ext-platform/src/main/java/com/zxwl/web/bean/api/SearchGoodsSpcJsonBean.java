package com.zxwl.web.bean.api;


public class SearchGoodsSpcJsonBean {

    private String  size ;

    private String  color;

    private String style;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "SearchGoodsSpcJsonBean{" +
                "size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}
