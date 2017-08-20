package com.example.kappa.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kappa on 2016/10/23.
 */
public class DynamicActivity extends AppCompatActivity  {

    private static final String DYNAMICACTION ="com.example.myapplication.dynamicreceiver";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_type);

       final EditText typ = (EditText)findViewById(R.id.texting);
       final Button but = (Button)findViewById(R.id.rbut);
       final DynamicReceiver dynamicReceiver = new DynamicReceiver();

        but.setOnClickListener(new View.OnClickListener(){
            boolean change = false;
            @Override
            public void onClick(View v){
             if(change){
                 but.setText("Register Broadcast");
                 unregisterReceiver(dynamicReceiver);

             }
                if(!change) {
                    but.setText("Unregister Broadcast");
                    IntentFilter dynamic_filter = new IntentFilter();
                    dynamic_filter.addAction(DYNAMICACTION);
                    registerReceiver(dynamicReceiver,dynamic_filter);
                }
                change = !change;
            }
        });

        final Button sen = (Button)findViewById(R.id.sending);

        sen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(but.getText()=="Unregister Broadcast") {
                    Intent intent = new Intent(DYNAMICACTION);
                    Bundle bundle = new Bundle();

                    bundle.putString("word", typ.getText().toString());
                    intent.putExtras(bundle);
                    sendBroadcast(intent);

                }
            }
        });




    }
}
