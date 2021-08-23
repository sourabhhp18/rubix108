package com.example.expandablelistview.adpter;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expandablelistview.interfaces.BrandStateChangeListener;
import com.example.expandablelistview.interfaces.ItemClickListener;
import com.example.expandablelistview.model.Brand;
import com.example.expandablelistview.model.Products;
import com.example.expandablelistview.roomdatabase.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BrandExpandableLayoutHelper implements BrandStateChangeListener {

    //data list
    private LinkedHashMap<Brand, ArrayList<Products>> mBrandDataMap = new LinkedHashMap<Brand, ArrayList<Products>>();
    private ArrayList<Object> mDataArrayList = new ArrayList<Object>();

    //section map
    //TODO : look for a way to avoid this

    private HashMap<String, Brand> mBrandMap = new HashMap<String, Brand>();

    //adapter
    private BrandedExpandableGridAdapter mBrandedExpandableGridAdapter;

    //recycler view
    RecyclerView mRecyclerView;

    public BrandExpandableLayoutHelper(Context context, RecyclerView recyclerView, ItemClickListener itemClickListener,
                                       int gridSpanCount) {

        //setting the recycler view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, gridSpanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        mBrandedExpandableGridAdapter = new BrandedExpandableGridAdapter(context, mDataArrayList,
                gridLayoutManager, itemClickListener, this);
        recyclerView.setAdapter(mBrandedExpandableGridAdapter);

        mRecyclerView = recyclerView;
    }

    public void notifyDataSetChanged() {
        //TODO : handle this condition such that these functions won't be called if the recycler view is on scroll
        generateDataList();
        mBrandedExpandableGridAdapter.notifyDataSetChanged();
    }

    public void addBrand(String brand, ArrayList<Products> items) {
        Brand newSection;
        mBrandMap.put(brand, (newSection = new Brand()));
        mBrandDataMap.put(newSection, items);
    }

    public void addProduct(String brand, Products products) {
        mBrandDataMap.get(mBrandMap.get(brand)).add(products);
    }

    public void removeItem(String section, Products products) {
        mBrandDataMap.get(mBrandMap.get(section)).remove(products);
    }

    public void removeSection(String section) {
        mBrandDataMap.remove(mBrandMap.get(section));
        mBrandMap.remove(section);
    }

    private void generateDataList () {
        mDataArrayList.clear();
        for (Map.Entry<Brand, ArrayList<Products>> entry : mBrandDataMap.entrySet()) {
            Brand key;
            mDataArrayList.add((key = entry.getKey()));
            if (key.isExpanded)
                mDataArrayList.addAll(entry.getValue());
        }
    }

    @Override
    public void onBrandStateChanged(Brand brand, boolean isOpen) {
        if (!mRecyclerView.isComputingLayout()) {
            brand.isExpanded = isOpen;
            notifyDataSetChanged();
        }
    }
}