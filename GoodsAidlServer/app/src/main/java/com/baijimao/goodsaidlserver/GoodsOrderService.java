package com.baijimao.goodsaidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GoodsOrderService extends Service {
    private static final String TAG = "Server";

    List<Goods> goodsList;

    public GoodsOrderService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        goodsList = new ArrayList<>();
        initGoodsOrder();
    }

    private void initGoodsOrder() {
        Goods goods = new Goods("可口可乐", 3);
        goodsList.add(goods);
        goods = new Goods("乐事薯片", 12.8);
        goodsList.add(goods);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return stub;
    }

    private final IGoodsOrderItf.Stub stub = new IGoodsOrderItf.Stub() {
        @Override
        public List<Goods> getGoodsList() throws RemoteException {
            return goodsList;
        }

        @Override
        public void addGoods(Goods goods) throws RemoteException {
            if (goods != null) {
                goodsList.add(goods);
            } else {
                Log.e(TAG, "传过来一个空商品");
            }
        }
    };
}
