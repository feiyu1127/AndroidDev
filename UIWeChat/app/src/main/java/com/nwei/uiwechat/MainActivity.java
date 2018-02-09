package com.nwei.uiwechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nwei.uiwechat.Adapter.MsgAdapter;
import com.nwei.uiwechat.Entity.Msg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecycleView;
    private MsgAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsgs();
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecycleView = findViewById(R.id.msg_recycle_view);


        //layoutManager 指定了 RecycleView 的布局方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecycleView.setLayoutManager(layoutManager);

        adapter = new MsgAdapter(msgList);
        msgRecycleView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);

                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecycleView.scrollToPosition(msgList.size() -1);
                    inputText.setText("");
                }
            }
        });

    }


    private void initMsgs(){
        Msg msg1 = new Msg("Hello",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Who is that?",Msg.TYPE_SENT);
        msgList.add(msg2);
    }

}
