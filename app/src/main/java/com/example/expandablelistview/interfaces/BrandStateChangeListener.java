package com.example.expandablelistview.interfaces;

import com.example.expandablelistview.model.Brand;

public interface BrandStateChangeListener {
    void onBrandStateChanged(Brand brand, boolean isOpen);
}