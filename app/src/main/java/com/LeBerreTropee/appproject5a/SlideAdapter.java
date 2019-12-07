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

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private MapView mapView;




    private ArrayList<Airport> airports;

    SlideAdapter(Context context) {
        this.context = context;
        Mapbox.getInstance(context, "pk.eyJ1IjoiYmFyYm91aWxsZSIsImEiOiJjazNlN3BvaHIxY3F1M2NuM2pvM3dwOTBlIn0.pZrTwcJxH6x1EU6RFrmceg");


    }

    public SlideAdapter(Context context,ArrayList<Airport> list ) {
        this.context = context;
        this.airports = list;
    }

     ArrayList<Airport> getAirports() {
        return airports;
    }

     void setAirports(ArrayList<Airport> airports) {
        this.airports = airports;
    }



    @Override
    public int getCount() {
        return airports.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, Object object) {
        return (view==object);
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);

        mapView = view.findViewById(R.id.mapView);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

// Map is set up and the style has loaded. Now you can add data or make other map adjustments.


                    }
                });
                mapboxMap.setCameraPosition(new CameraPosition.Builder()
                        .target(new LatLng(airports.get(position).getLatitude(), airports.get(position).getLongitude())).zoom(11.5).build());
                mapboxMap.setMinZoomPreference(7);
                mapboxMap.setMaxZoomPreference(15);
                mapboxMap.setStyle(Style.SATELLITE);
            }
        });
        TextView name = (TextView) view.findViewById(R.id.name);

        TextView lon = (TextView) view.findViewById(R.id.snowtam);

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

