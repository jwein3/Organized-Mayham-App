import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.justin.wrestlingapp.R;

import java.util.ArrayList;

/**
 * Created by justin on 10/01/18.
 */

public class ArrayAdapter extends BaseAdapter implements ListAdapter{
    private ArrayList<String> list = new ArrayList<>();
    private Context context;

    public ArrayAdapter(Context context,ArrayList<String> list ){
        this.list = list;
        this.context = context;

    }
    @Override
    public int getCount(){
        return list.size();
    }
    @Override
    public Object getItem(int position){
        return list.get(position);
    }
    @Override
    public long getItemId(int position){
        return 0;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.match_list_layout, null);
        }
        TextView listItemText = (TextView)view.findViewById(R.id.listItem);
        listItemText.setText(list.get(position));

        Button win_btn = (Button)view.findViewById(R.id.win_btn);
        Button lose_btn = (Button)view.findViewById(R.id.loss_btn);

        win_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Send to win list
                list.remove(position);
            }
        });
        lose_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Send to loss list
                list.remove(position);
            }
        });
        return view;
        }
}

