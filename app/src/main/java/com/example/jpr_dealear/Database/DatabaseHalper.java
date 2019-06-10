
package com.example.jpr_dealear.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.jpr_dealear.DealerModel;
import com.example.jpr_dealear.User;

import java.util.ArrayList;
import java.util.List;


public class
DatabaseHalper extends SQLiteOpenHelper {


    public DatabaseHalper(Context context) {
        super(context, "Login.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(user_id INTEGER primary key AUTOINCREMENT,user_name text,password text,cnfrmpassword text)");
        db.execSQL("create table dealears(d_id INTEGER primary key AUTOINCREMENT, dealearsname text ,phonenumber text,user_id INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }

    // inserting in database
    public boolean insertuser(String user_name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", user_name);
        contentValues.put("password", password);

        Long ins = db.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }


    //checking if userid exists
    public Boolean chkuserid(String user_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where user_name=?", new String[]{user_name});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }


    public User loginuser(String user_name,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user=null;
        Cursor cursor = db.rawQuery("select * from user where user_name=?", new String[]{user_name});
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()){
                 user = new User();
                user.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
                user.setUser_name(cursor.getString(cursor.getColumnIndex("user_name")));
            }

        }
        return user;
    }
//


    /**
     * Dealers CRUD
     */

    //inserd dealer method
    // getDelaers
    // updatyeDealers
    //deleteDealers
    public boolean insertdealears(DealerModel dealerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dealearsname", dealerModel.getDealearsname());
        contentValues.put("phonenumber", dealerModel.getPhonenumber());
        contentValues.put("user_id", dealerModel.getUserid());
        Long id = db.insert("dealears", null, contentValues);
        if (id == -1) return false;
        else return true;


    }

    public Boolean chkdealears(String dealearsname, String phonenumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from dealears where dealearsname=? and phonenumber=?", new String[]{dealearsname, phonenumber});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<DealerModel> getAllDealears(int userid) {
        //List to return all dealers in database
        List<DealerModel> dealearsList = new ArrayList<>();

        //Get readable database
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from dealears where user_id=?", new String[]{String.valueOf(userid)});
        if (cursor.moveToFirst()) {
            do {
                DealerModel dealear = new DealerModel();
                dealear.setD_id(cursor.getInt(cursor.getColumnIndex("d_id")));
                dealear.setDealearsname(cursor.getString(cursor.getColumnIndex("dealearsname")));
                dealear.setPhonenumber(cursor.getString(cursor.getColumnIndex("phonenumber")));
                dealearsList.add(dealear);
            } while (cursor.moveToNext());

        }
        return dealearsList;

    }


    //update Dealears
    public int update_dealears(DealerModel dealerModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dealearsname", dealerModel.getDealearsname());
        contentValues.put("phonenumber", dealerModel.getPhonenumber());


        int updated= db.update("dealears",contentValues,"d_id=?",new String[]{ String.valueOf(dealerModel.getD_id())});

        return updated;

    }

}








