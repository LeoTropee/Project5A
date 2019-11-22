package com.LeBerreTropee.appproject5a;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;


    // list of titles


    // list of descriptions



    public ArrayList<Airport> airports;

    public SlideAdapter(Context context) {
        this.context = context;

    }

    public SlideAdapter(Context context,ArrayList<Airport> list ) {
        this.context = context;
        this.airports = list;
    }




    @Override
    public int getCount() {
        return airports.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);

        TextView name = (TextView) view.findViewById(R.id.name);

        TextView lat = (TextView) view.findViewById(R.id.lat);
        TextView lon = (TextView) view.findViewById(R.id.lon);

        TextView previousAirport = (TextView) view.findViewById(R.id.previousAirport);
        TextView currentAirport = (TextView) view.findViewById(R.id.currentAirport);
        TextView nextAirport = (TextView) view.findViewById(R.id.nextAirport);

        currentAirport.setText(airports.get(position).getIACO());

        if (position == 0)
        {
            previousAirport.setText("");
            nextAirport.setText(airports.get(position+1).getIACO());
        }
        else if(position == this.getCount()-1)
        {
            nextAirport.setText("");
            previousAirport.setText(airports.get(position-1).getIACO());

        }
        else
        {
            previousAirport.setText(airports.get(position - 1 ).getIACO());
            nextAirport.setText(airports.get(position + 1).getIACO());
        }

        name.setText(airports.get(position).getName());
        lat.setText("" + airports.get(position).getLatitude());
        lon.setText("" + airports.get(position).getLongitude());



//        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
//        imgslide.setImageResource(lst_images[position]);
//        txttitle.setText(lst_title[position]);
        float width = previousAirport.getPaint().measureText((String)previousAirport.getText());
        Shader textShader=new LinearGradient(0, 0, width , previousAirport.getText().length(),
                new int[]{
                        Color.parseColor("#05FFFFFF"),
                        Color.parseColor("#66FFFFFF"),
                        Color.parseColor("#BBFFFFFF"),
                }, null, Shader.TileMode.CLAMP);
        previousAirport.getPaint().setShader(textShader);
        float widthRight = nextAirport.getPaint().measureText((String)nextAirport.getText());
        Shader rightTextShader=new LinearGradient( widthRight , nextAirport.getText().length(),0,0,
                new int[]{
                        Color.parseColor("#05FFFFFF"),
                        Color.parseColor("#66FFFFFF"),
                        Color.parseColor("#BBFFFFFF"),
                }, null, Shader.TileMode.CLAMP);
        nextAirport.getPaint().setShader(rightTextShader);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

}

