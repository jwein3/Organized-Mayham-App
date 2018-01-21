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

import static android.app.ProgressDialog.show;


public class MainScreen extends AppCompatActivity implements View.OnClickListener {


    public static ArrayList list = new ArrayList<WrestlerList>();
    ArrayList MatchHistList = AddMatches.getMatchHistList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        ListView MatchList = (ListView)findViewById(R.id.MatchList);
        MatchList.setAdapter(new ListAdapter(this,R.layout.match_list_layout,list));
        /*
        list.add(new WrestlerList("test",1));
        list.add(new WrestlerList("test",2));
        list.add(new WrestlerList("test",3));
        list.add(new WrestlerList("test",4));
        list.add(new WrestlerList("test",5));
        list.add(new WrestlerList("test",6));
        list.add(new WrestlerList("test",7));
        list.add(new WrestlerList("test",8));
        list.add(new WrestlerList("test",9));
        list.add(new WrestlerList("test",10));
        list.add(new WrestlerList("test",11));
        list.add(new WrestlerList("test",12));
        list.add(new WrestlerList("test",13));
        list.add(new WrestlerList("test",14));
        list.add(new WrestlerList("test",15));
        list.add(new WrestlerList("test",16));
        list.add(new WrestlerList("test",17));
        list.add(new WrestlerList("test",18));
        list.add(new WrestlerList("test",19));
        list.add(new WrestlerList("test",20));
        */
        SortList(list);
        SetButtons();
       

        }
    public static ArrayList<WrestlerList> getNamesList(){
        return list;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainScreen.class));
    }

    public void SetButtons(){
        Button ManagePlayersButton = (Button) (findViewById(R.id.btnAddPlayers));
        Button ManageMatchesButton = (Button) (findViewById(R.id.btnAddMatches));
        ManagePlayersButton.setOnClickListener((View.OnClickListener) this);
        ManageMatchesButton.setOnClickListener((View.OnClickListener) this);





    }
    //Add Players Button
    public void onClick (View v){
        if (v.getId() == R.id.btnAddPlayers) {

            startActivity(new Intent(MainScreen.this, AddPlayers.class));

        }
        //Add Matches Button
        else if(v.getId() == R.id.btnAddMatches){

            startActivity(new Intent(MainScreen.this, AddMatches.class));
        }
    }

    public static void SortList(ArrayList<WrestlerList> list){
        Collections.sort(list, new Comparator() {
            @Override
            //Sorts By Match Number
            public int compare(Object obj1, Object obj2) {
                Integer compMatchNum1 = ((WrestlerList)obj1).getMatchNum();
                Integer compMatchNum2 = ((WrestlerList)obj2).getMatchNum();
                int sortComp = compMatchNum1.compareTo(compMatchNum2);

                if(sortComp!=0){
                    return sortComp;
                }
                //Sorts List Alphabetically once its done sorting by match number
                else{
                    String compName1 = ((WrestlerList) obj1).getWrestlerName();
                    String compName2 = ((WrestlerList) obj2).getWrestlerName();
                    return compName1.compareTo(compName2);
                }
            }
        });
    }

    private class ListAdapter extends ArrayAdapter<WrestlerList>{
        private int layout;
        public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<WrestlerList> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder =null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
            }
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.win_button = (Button) convertView.findViewById(R.id.win_btn);
                viewHolder.lose_button = (Button) convertView.findViewById(R.id.loss_btn);
                viewHolder.delete_button = (Button) convertView.findViewById(R.id.DelBtn);
                viewHolder.listItem = (TextView) convertView.findViewById(R.id.listItem);
                viewHolder.MatchNum = (TextView) convertView.findViewById(R.id.MatchNum);
                viewHolder.listItem.setText((CharSequence) getItem(position).getWrestlerName());
                viewHolder.MatchNum.setText("Match # "+(CharSequence) String.valueOf(getItem(position).getMatchNum()));
                viewHolder.win_button.setOnClickListener(new View.OnClickListener(){
                  @Override
                  public void onClick(View v){

                      Toast.makeText(getContext(), "Match sent to Match History!" + position,Toast.LENGTH_SHORT).show();
                      MatchHistList.add(new AddMatches.MatchHistoryList(getItem(position).getWrestlerName(),getItem(position).getMatchNum(),"Won"));

                      list.remove(getItem(position));

                      startActivity(new Intent(MainScreen.this, MainScreen.class));

                  }

                });
                viewHolder.lose_button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(getContext(), "Match sent to Match History" + position,Toast.LENGTH_SHORT).show();
                        MatchHistList.add(new AddMatches.MatchHistoryList(getItem(position).getWrestlerName(),getItem(position).getMatchNum(),"Lost"));
                        list.remove(getItem(position));
                        startActivity(new Intent(MainScreen.this, MainScreen.class));

                    }

                });
                viewHolder.delete_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"Delete Button Clicked!"+position,Toast.LENGTH_SHORT).show();
                        list.remove(getItem(position));
                        startActivity(new Intent(MainScreen.this, MainScreen.class));
                    }
                });
                convertView.setTag(viewHolder);

            if(convertView!=null){
                mainViewHolder = (ViewHolder) convertView.getTag();
                mainViewHolder.listItem.setText((CharSequence) getItem(position).getWrestlerName());
                mainViewHolder.MatchNum.setText("Match # "+(CharSequence) String.valueOf(getItem(position).getMatchNum()));
            }
            return convertView;
            //return super.getView(position, convertView, parent);
        }
    }

    public class ViewHolder{
        TextView listItem;
        TextView MatchNum;
        Button win_button;
        Button lose_button;
        Button delete_button;
    }

    public static class WrestlerList {
        private String mWrestlerName;
        private int mMatchNum;

        public WrestlerList(String WrestlerName, int MatchNum){
            mWrestlerName = WrestlerName;
            mMatchNum = MatchNum;
        }
        public String getWrestlerName(){
            return mWrestlerName;
        }

        public int getMatchNum(){
            return mMatchNum;
        }
    }


}


