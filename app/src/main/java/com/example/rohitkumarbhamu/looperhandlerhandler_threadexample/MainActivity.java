package com.example.rohitkumarbhamu.looperhandlerhandler_threadexample;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Worker worker;
    private TextView tvMessage;

    private Handler handler= new Handler( Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvMessage.setText((String)msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        worker = new Worker();
        tvMessage = (TextView)findViewById(R.id.tv_message);

        worker.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = Message.obtain();
            message.obj= "Task 1 completed";
            handler.sendMessage(message);
        }).execute(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message = Message.obtain();
            message.obj= "Task 2 completed";
            handler.sendMessage(message);

        }).execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message = Message.obtain();
            message.obj= "Task 3 completed";
            handler.sendMessage(message);

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        worker.quit();
    }
}
