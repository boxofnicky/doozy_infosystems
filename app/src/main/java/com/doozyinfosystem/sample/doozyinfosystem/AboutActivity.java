package com.doozyinfosystem.sample.doozyinfosystem;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Relative Layout for about
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relAbout);

        //logo on the top
        ImageView imageView = (ImageView) findViewById(R.id.imageViewLogo);
        try {
            InputStream is = getAssets().open("mainImage.jpg");
            Drawable d = Drawable.createFromStream(is, null);
            imageView.setImageDrawable(d);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Scroll View beneath the image logo
        ScrollView sv = new ScrollView(this);
        RelativeLayout.LayoutParams svParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        svParam.addRule(RelativeLayout.BELOW, R.id.imageViewLogo);
        sv.setLayoutParams(svParam);
        relativeLayout.addView(sv);

        //Relative Layout for scroll view
        RelativeLayout relChild = new RelativeLayout(this);
        RelativeLayout.LayoutParams relChildParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relChild.setLayoutParams(relChildParam);
        sv.addView(relChild);

        //Text Views for Child Relative Layout
        //store id for layout reference
        int id = 0;

        //Outer loop for creating the text fields
        for (int i = 0; i < 4; i++) {
            RelativeLayout.LayoutParams tvParamH = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            RelativeLayout.LayoutParams tvParamT = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            TextView textViewH = new TextView(this);
            textViewH.setTextSize(20);
            textViewH.setTypeface(Typeface.DEFAULT_BOLD);
            textViewH.setTop(20);
            TextView textViewT = new TextView(this);
            switch (i) {
                case 0:
                    textViewH.setText(R.string.skill_heading);
                    textViewT.setText(R.string.skill_text);
                    break;
                case 1:
                    textViewH.setText(R.string.mission_heading);
                    textViewT.setText(R.string.mission_text);
                    break;
                case 2:
                    textViewH.setText(R.string.vision_heading);
                    textViewT.setText(R.string.vision_text);
                    break;
                case 3:
                    textViewH.setText(R.string.values_heading);
                    textViewT.setText(R.string.values_text);
                    break;
            }


            if (i != 0)
                tvParamH.addRule(RelativeLayout.BELOW, id);
            tvParamH.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER);
            textViewH.setLayoutParams(tvParamH);
            relChild.addView(textViewH);
            textViewH.setId(id = View.generateViewId());


            tvParamT.addRule(RelativeLayout.BELOW, id);
            tvParamT.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER);
            textViewT.setLayoutParams(tvParamT);
            relChild.addView(textViewT);
            textViewT.setId(id = View.generateViewId());
        }

    }
}
