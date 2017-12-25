package com.github.sadaharusong.wolfkillassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sadaharusong on 2017/9/17 0017.
 * 角色model
 */

public class Role implements Parcelable {

    private String name;
    private boolean isSelected;
    private boolean isSetted;
    private boolean lover;
    private boolean isDead;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSetted() {
        return isSetted;
    }

    public void setSetted(boolean set) {
        isSetted = set;
    }

    public void setisDead(boolean dead) {
        isDead = dead;
    }

    public boolean getisDead() {
        return isDead;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSetted ? (byte) 0 : (byte) 1);
        dest.writeByte(this.lover ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDead ? (byte) 1 : (byte) 0);
    }

    public Role() {
    }

    protected Role(Parcel in) {
        this.name = in.readString();
        this.isSelected = in.readByte() != 0;
        this.isSetted = in.readByte() != 0;
        this.lover = in.readByte() != 0;
        this.isDead = in.readByte() !=0;
    }

    public static final Creator<Role> CREATOR = new Creator<Role>() {
        @Override
        public Role createFromParcel(Parcel source) {
            return new Role(source);
        }

        @Override
        public Role[] newArray(int size) {
            return new Role[size];
        }
    };
}
