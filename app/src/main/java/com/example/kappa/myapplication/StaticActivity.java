package com.example.kappa.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kappa on 2016/10/23.
 */
public class StaticActivity extends AppCompatActivity {
    /*public int getClickedImageResId(int position){
        return fruitpic[position];
    } */
    private static final String STATICACTION ="com.example.myapplication.staticreceiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fruit);
        ListView fruitl = (ListView)findViewById(R.id.fruitlist);

        final Integer[] fruitpic = new Integer[]{R.mipmap.apple,R.mipmap.banana,R.mipmap.cherry,
                R.mipmap.coco,R.mipmap.kiwi,R.mipmap.orange
                ,R.mipmap.pear,R.mipmap.strawberry,R.mipmap.watermelon};

        final String [] fruitnam = new String[]{"Apple","Banana","Cherry","Coco","Kiwi"
                ,"Orange","Pear","Strawberry","Watermelon"};
        final List<Map<String,Object>> frulist = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            Map<String,Object> temp = new LinkedHashMap<>();
            temp.put("fruitlistpicture",fruitpic[i]);
            temp.put("fruitlistname",fruitnam[i]);
            frulist.add(temp);
        }

        SimpleAdapter fruitadp = new SimpleAdapter(this,frulist,R.layout.fruit_list,
                new String[]{"fruitlistpicture","fruitlistname"}
                ,new int[]{R.id.fruitpicline,R.id.fruitnameline});

        fruitl.setAdapter(fruitadp);
        fruitl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){

               // Toast.makeText(StaticActivity.this, "被點擊", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(STATICACTION);
              //  intent.setAction(STATICACTION);
                Bundle bundle= new Bundle();
                bundle.putInt("pic",fruitpic[i]);
                bundle.putString("fruitname",fruitnam[i]);
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });




    }
}
