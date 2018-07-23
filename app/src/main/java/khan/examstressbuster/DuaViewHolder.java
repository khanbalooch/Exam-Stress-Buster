package khan.examstressbuster;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static khan.examstressbuster.R.xml.preferences;


/**
 * Created by Khan on 03-Mar-17.
 */

public class DuaViewHolder extends RecyclerView.ViewHolder
{
    protected TextView dName;
    protected TextView dText;
    protected TextView dReference;
    //protected ImageView expandButton;
    protected LinearLayout dHeader;
    private Context context;

    public DuaViewHolder(View duaView)
    {
        super(duaView);
        context = duaView.getContext();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String size = preferences.getString(context.getString(R.string.pref_size_key),"normal");
        String color = preferences.getString(context.getString(R.string.pref_color_key),"red");

        dHeader = (LinearLayout)duaView.findViewById(R.id.duaHeader);
        //expandButton = (ImageView)duaView.findViewById(R.id.expandButton);
        dName = (TextView) duaView.findViewById(R.id.title);
        //dText.setBackground(R.drawable.border);

        dText = (TextView) duaView.findViewById(R.id.text);
        dReference = (TextView) duaView.findViewById(R.id.reference);

        setSize(size);
        setColor(color);

        dHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if( dText.getVisibility() == View.VISIBLE )
                {
                    //expandButton.setImageResource(R.drawable.show);
                    dText.setVisibility(View.GONE);
                    dReference.setVisibility(View.GONE);
                }else
                {
                    //expandButton.setImageResource(R.drawable.hide);
                    dText.setVisibility(View.VISIBLE);
                    dReference.setVisibility(View.VISIBLE);
                }

            }
        });
    }
    private void setSize(String size)
    {
        if(size.equals(context.getString(R.string.pref_size_large_value)))
        {
            dText.setTextSize(50);
        }else if(size.equals(context.getString(R.string.pref_size_normal_value)))
        {
            dText.setTextSize(30);
        }else if(size.equals(context.getString(R.string.pref_size_large_value)))
        {
            dText.setTextSize(20);
        }

    }
    private void setColor(String color)
    {
        if(color.equals(context.getString(R.string.pref_color_blue_value)))
        {
            dText.setTextColor(Color.BLUE);
        }else if(color.equals(context.getString(R.string.pref_color_red_value)))
        {
            dText.setTextColor(Color.RED);
        }else if(color.equals(context.getString(R.string.pref_color_green_value)))
        {
            dText.setTextColor(Color.GREEN);
        }
    }
}
