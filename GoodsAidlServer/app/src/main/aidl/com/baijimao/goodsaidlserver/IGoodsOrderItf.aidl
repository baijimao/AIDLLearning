// IGoodsOrderItf.aidl
package com.baijimao.goodsaidlserver;

// Declare any non-default types here with import statements
import com.baijimao.goodsaidlserver.Goods;

interface IGoodsOrderItf {

    List<Goods> getGoodsList();

    void addGoods(in Goods goods);
}
