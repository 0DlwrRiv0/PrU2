package com.example.pru2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pru2.MyData.MyData;

import java.io.Serializable;
import java.util.List;

public class MyAdapter extends BaseAdapter implements Serializable {

    private List<MyData> list;
    private Context context;
    private LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        if(isEmptyOrNull())
        {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if(isEmptyOrNull())
        {
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView= null;
        TextView textView1= null;
        ImageView imageView = null;
        view = layoutInflater.inflate(R.layout.activity_lista, null );
        textView= view.findViewById(R.id.textU);
        textView1=view.findViewById(R.id.textCon);
        imageView = view.findViewById(R.id.imageView1);
        textView1.setText(String.valueOf(list.get(i).getContra()));
        textView.setText(String.valueOf(list.get(i).getUsuario()));
        return view;
    }

    public MyAdapter(List<MyData> list, Context context)
    {
        this.list = list;
        this.context = context;
        if( context != null)
        {
            layoutInflater = LayoutInflater.from(context);
        }
    }

    public boolean isEmptyOrNull( )
    {
        return list == null || list.size() == 0;
    }
}
