package com.example.justin.wrestlingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AddMatches extends AppCompatActivity {
    public static ArrayList MatchHistList = new ArrayList<MatchHistoryList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matches);
        ListView MatchList2 = (ListView)findViewById(R.id.MatchHistView);
        MatchList2.setAdapter(new HistAdapter(this,R.layout.match_history_layout,MatchHistList));

    }

    public void onBackPressed(){
        startActivity(new Intent(this, MainScreen.class));
    }
    //Defining 3D Array to keep track of wrestler name, match number and if they won or lost
    public static class MatchHistoryList {
        private String mWrestlerName;
        private String mMatchStatus;
        private int mMatchNum;

        public MatchHistoryList(String WrestlerName, int MatchNum, String MatchStatus){
            mWrestlerName = WrestlerName;
            mMatchNum = MatchNum;
            mMatchStatus=MatchStatus;
        }
        public String getWrestlerName2(){
            return mWrestlerName;
        }
        public String getMatchStatus2(){return mMatchStatus;}
        public int getMatchNum2(){
            return mMatchNum;
        }

    }

    public static ArrayList<MatchHistoryList> getMatchHistList(){
        return MatchHistList;
    }
    //Sets up the custom listview from the xml file match_history_layout.xml
    private class HistAdapter extends ArrayAdapter<AddMatches.MatchHistoryList> {
        private int layout;
        public HistAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AddMatches.MatchHistoryList> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder2 mainViewHolder2 =null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
            }
            ViewHolder2 viewHolder2 = new ViewHolder2();
            viewHolder2.delete_button2 = (Button) convertView.findViewById(R.id.DelBtn2);
            viewHolder2.listItem2 = (TextView) convertView.findViewById(R.id.listItem2);
            viewHolder2.MatchNum2 = (TextView) convertView.findViewById(R.id.MatchNum2);
            viewHolder2.MatchStatus= (TextView) convertView.findViewById(R.id.MatchStatus);
            viewHolder2.listItem2.setText((CharSequence) getItem(position).getWrestlerName2());
            viewHolder2.MatchNum2.setText("Match # "+(CharSequence) String.valueOf(getItem(position).getMatchNum2()));
            viewHolder2.MatchStatus.setText((CharSequence)getItem(position).getMatchStatus2());
            viewHolder2.delete_button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"Delete Button Clicked!"+position,Toast.LENGTH_SHORT).show();
                    MatchHistList.remove(getItem(position));
                    startActivity(new Intent(AddMatches.this, AddMatches.class));
                }
            });
            convertView.setTag(viewHolder2);

            if(convertView!=null){
                mainViewHolder2 = (ViewHolder2) convertView.getTag();
                mainViewHolder2.listItem2.setText((CharSequence) getItem(position).getWrestlerName2());
                mainViewHolder2.MatchNum2.setText("Match # "+(CharSequence) String.valueOf(getItem(position).getMatchNum2()));
                viewHolder2.MatchStatus.setText(getItem(position).getMatchStatus2());
            }
            return convertView;

        }
    }
    public class ViewHolder2{
        TextView listItem2;
        TextView MatchNum2;
        TextView MatchStatus;
        Button delete_button2;
    }
}
