package com.androidlime.androidjsondataparsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mithun on 9/28/2019.
 */
public class CustomAdapter extends BaseAdapter {



    Context applicationContext;

    int sample;

    List<DemoStudent> s;

    private LayoutInflater inflater;



    public CustomAdapter(Context applicationContext, int sample, List<DemoStudent> s) {

        this.applicationContext=applicationContext;
        this.sample=sample;
        this.s=s;



    }

    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int i) {
        return s.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if (view == null) {

            inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            view =inflater.inflate(R.layout.sample, viewGroup, false);
        }


        TextView name, dept, country;


        name = view.findViewById(R.id.name);
        dept = view.findViewById(R.id.dept);
        country = view.findViewById(R.id.country);


         name.setText( s.get(i).getName());
         dept.setText( s.get(i).getDept());
         country.setText( s.get(i).getCountry());
        return view;
    }




    }

