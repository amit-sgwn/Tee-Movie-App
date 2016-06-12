package com.example.amit.themovieapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.amit.themovieapp.R;

import java.util.List;

public class AndroidFlavorAdapter extends ArrayAdapter<AndroidFlaver> {

    public AndroidFlavorAdapter(Activity context, List<AndroidFlaver> androidFlavers) {
        super(context,0, androidFlavers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AndroidFlaver androidFlavor = getItem(position);


        return super.getView(position, convertView, parent);
    }
}
