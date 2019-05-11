package com.baijimao.goodsaidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baijimao.goodsaidlserver.Goods;
import com.baijimao.goodsaidlserver.IGoodsOrderItf;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView = null;
    private IGoodsOrderItf iGoodsOrderItf;
    private boolean connected = false;
    private List<Goods> goodsList = null;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iGoodsOrderItf = IGoodsOrderItf.Stub.asInterface(service);
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_get_goodsorder).setOnClickListener(this);
        findViewById(R.id.btn_add_goods).setOnClickListener(this);
        textView = findViewById(R.id.text_show_goods);
        connectService();
    }

    private void connectService() {
        Intent intent = new Intent();
        intent.setPackage("com.baijimao.goodsaidlserver");
        intent.setAction("com.baijimao.goodsaidlserver.action");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_goodsorder:
                if (connected) {
                    try {
                        goodsList = iGoodsOrderItf.getGoodsList();
                        StringBuilder stringBuilder = new StringBuilder();
                        for (Goods goods : goodsList) {
                            stringBuilder.append(goods.toString() + "\n");
                        }
                        textView.setText(stringBuilder);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_add_goods:
                if (connected) {
                    Goods goods = new Goods("西瓜", 25);
                    try {
                        iGoodsOrderItf.addGoods(goods);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
