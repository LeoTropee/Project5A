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

import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;


    // list of titles
    public String[] airportNames = {
            "COSMONAUT",
            "SATELITE",
            "GALAXY",
            "ROCKET"
    }   ;

    // list of descriptions
    public String[] lst_description = {
            "0",
            "1",
            "2",
            "3"
    };
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(1,188,212)
    };


    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return airportNames.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);

        TextView txttitle= (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtdescription);
        TextView previousAirport = (TextView) view.findViewById(R.id.previousAirport);
        TextView currentAirport = (TextView) view.findViewById(R.id.currentAirport);
        TextView nextAirport = (TextView) view.findViewById(R.id.nextAirport);

        currentAirport.setText(airportNames[position]);

        if (position == 0)
        {
            previousAirport.setText("");
            nextAirport.setText(airportNames[position+1]);
        }
        else if(position == this.getCount()-1)
        {
            nextAirport.setText("");
            previousAirport.setText(airportNames[position-1]);

        }
        else
        {
            previousAirport.setText(airportNames[position-1]);
            //nextAirport.setText(airportNames[position+1]);
        }


//        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
//        imgslide.setImageResource(lst_images[position]);
//        txttitle.setText(lst_title[position]);
        float width = previousAirport.getPaint().measureText((String)previousAirport.getText());
        Shader textShader=new LinearGradient(0, 0, width , previousAirport.getTextSize(),
                new int[]{
                        Color.parseColor("#05FFFFFF"),
                        Color.parseColor("#66FFFFFF"),
                        Color.parseColor("#BBFFFFFF"),
                }, null, Shader.TileMode.CLAMP);
        previousAirport.getPaint().setShader(textShader);
        float widthRight = nextAirport.getPaint().measureText((String)nextAirport.getText());
        Shader rightTextShader=new LinearGradient( widthRight , nextAirport.getTextSize(),0,0,
                new int[]{
                        Color.parseColor("#05FFFFFF"),
                        Color.parseColor("#66FFFFFF"),
                        Color.parseColor("#BBFFFFFF"),
                }, null, Shader.TileMode.CLAMP);
        nextAirport.getPaint().setShader(rightTextShader);
        description.setText(lst_description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
