package com.example.expandablelistview.model;

public class Brand {
    private String productBarnd;
    public boolean isExpanded;
    public String getProductBarnd() {
        return productBarnd;
    }

    public void setProductBarnd(String productBarnd) {
        this.productBarnd = productBarnd;
    }


    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
