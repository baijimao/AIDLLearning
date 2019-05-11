package com.baijimao.goodsaidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: baijimao
 * @date: 2019/5/11
 * Description:
 */
public class Goods implements Parcelable {

    private String name;

    private double price;

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    protected Goods(Parcel in) {
        name = in.readString();
        price = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
    }

    public void readFromParcel(Parcel dest) {
        name = dest.readString();
        price = dest.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Goods> CREATOR = new Creator<Goods>() {
        @Override
        public Goods createFromParcel(Parcel in) {
            return new Goods(in);
        }

        @Override
        public Goods[] newArray(int size) {
            return new Goods[size];
        }
    };
}
