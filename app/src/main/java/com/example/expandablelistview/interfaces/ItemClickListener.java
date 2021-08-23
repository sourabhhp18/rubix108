package com.example.expandablelistview.interfaces;

import com.example.expandablelistview.model.Brand;
import com.example.expandablelistview.model.Products;


public interface ItemClickListener {
    void itemClicked(Products products);
    void itemClicked(Brand brand);
}