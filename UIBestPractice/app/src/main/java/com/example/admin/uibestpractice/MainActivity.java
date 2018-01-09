package com.example.admin.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.uibestpractice.http.HttpCallBackLisioner;
import com.example.admin.uibestpractice.http.HttpUtil;
import com.example.admin.uibestpractice.http.Message;
import com.example.admin.uibestpractice.http.ParseJson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;

    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsgs(); //初始化消息
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1); //当有新消息时刷新 ListView 中的消息
                    msgRecyclerView.scrollToPosition(msgList.size() - 1); //将 ListView 定位到最后一行
                    inputText.setText(""); //清空输入框中的内容

                    HttpUtil.getRequest(content, new HttpCallBackLisioner() {
                        @Override
                        public void onFinish(String requestString) {

                            final String msgM = requestString;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Message message = ParseJson.parseJson(msgM, Message.class);

                                        Msg msgReceived = new Msg(message.getContent(), Msg.TYPE_RECEIVED);
                                        msgList.add(msgReceived);
                                        adapter.notifyItemInserted(msgList.size() - 1); //当有新消息时刷新 ListView 中的消息
                                        msgRecyclerView.scrollToPosition(msgList.size() - 1); //将 ListView 定位到最后一行

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });

                }
            }
        });

    }


    private void initMsgs() {
        Msg msg1 = new Msg("hello guy", Msg.TYPE_RECEIVED);
        msgList.add(msg1);

        Msg msg2 = new Msg("hello ,who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);

        Msg msg3 = new Msg("this is Tom,Nice talking to you.", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

}
