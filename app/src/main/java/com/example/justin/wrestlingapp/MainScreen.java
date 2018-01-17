package com.example.justin.wrestlingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.app.ProgressDialog.show;


public class MainScreen extends AppCompatActivity implements View.OnClickListener {


    public static ArrayList <String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        TextView Names = (TextView) findViewById(R.id.listItem);


        //Test to see how many items in the array this thing detects
        //Toast.makeText(getApplicationContext(), "Entered Screen",Toast.LENGTH_SHORT).show();
        //Used for setting the player names to the TextView, figure out how to fix that
        ListView MatchList = (ListView)findViewById(R.id.MatchList);
        MatchList.setAdapter(new ListAdapter(this,R.layout.match_list_layout,list));
        SetButtons();
       

        }
    public static ArrayList<String> getNamesList(){
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




        //Names.setText("Sup");
    }
    //Add Players Button
    public void onClick (View v){
        if (v.getId() == R.id.btnAddPlayers) {

            startActivity(new Intent(MainScreen.this, AddPlayers.class));

        }
        //Add Matches Button
        else if(v.getId() == R.id.btnAddMatches){
            /*list.add("test1");
            list.add("test2");
            list.add("test3");
            list.add("test4");
            */
            startActivity(new Intent(MainScreen.this, AddMatches.class));
        }
    }

    private class ListAdapter extends ArrayAdapter<String>{
        private int layout;
        public ListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder =null;
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView= inflater.inflate(layout,parent,false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.win_button = (Button) convertView.findViewById(R.id.win_btn);
                viewHolder.lose_button = (Button) convertView.findViewById(R.id.loss_btn);
                viewHolder.listItem = (TextView) convertView.findViewById(R.id.listItem);
                viewHolder.listItem.setText(getItem(position));
                viewHolder.win_button.setOnClickListener(new View.OnClickListener(){
                  @Override
                  public void onClick(View v){
                      Toast.makeText(getContext(), "Win Button Clicked!" + position,Toast.LENGTH_SHORT).show();

                  }

                });
                viewHolder.lose_button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(getContext(), "Lose Button Clicked!" + position,Toast.LENGTH_SHORT).show();

                    }

                });
                convertView.setTag(viewHolder);
            }
            else{
                mainViewHolder = (ViewHolder) convertView.getTag();
                mainViewHolder.listItem.setText(getItem(position));
            }
            return convertView;
            //return super.getView(position, convertView, parent);
        }
    }

    public class ViewHolder{
        TextView listItem;
        Button win_button;
        Button lose_button;
    }

}


