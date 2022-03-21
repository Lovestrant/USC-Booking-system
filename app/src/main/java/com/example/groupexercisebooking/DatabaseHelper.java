package com.example.groupexercisebooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "ExerciseDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Authentication(id INTEGER  primary key autoincrement,regNo TEXT)");
        DB.execSQL("CREATE TABLE Bookings(id INTEGER  primary key autoincrement,regNo TEXT,date TEXT,numOfSessions TEXT,status TEXT,status TEXT)");
        DB.execSQL("CREATE TABLE Ratings(id INTEGER  primary key autoincrement,regNo TEXT,date TEXT,numOfSessions TEXT,rating TEXT)");
        DB.execSQL("CREATE TABLE Timetable(id INTEGER  primary key autoincrement,date TEXT,first TEXT, second TEXT, third TEXT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists Authentication");
        DB.execSQL("DROP TABLE if exists Bookings");
        DB.execSQL("DROP TABLE if exists Ratings");
        DB.execSQL("DROP TABLE if exists Timetable");
    }


    //method to insert dta to Db
    public Boolean insertBookings(String regNo, String date, String numOfSessions, String status) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // contentValues.put("id", id);
        contentValues.put("regNo", regNo);
        contentValues.put("date", date);
        contentValues.put("numOfSessions", numOfSessions);
        contentValues.put("status", status);
        long result = DB.insert("Bookings", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean insertRatings(String id, String regNo, String date, String numOfSessions, String rating)  {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

         contentValues.put("id", id);
        contentValues.put("regNo", regNo);
        contentValues.put("date", date);
        contentValues.put("numOfSessions", numOfSessions);
        contentValues.put("rating", rating);
        long result = DB.insert("Ratings", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean insertTimetable(String date, String first, String second, String third, String status) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("date", date);
        contentValues.put("first", first);
        contentValues.put("second", second);
        contentValues.put("third", third);
        contentValues.put("status", status);

        long result = DB.insert("Timetable", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean insertAuthData(String regNo) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("regNo", regNo);
        long result = DB.insert("Authentication", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
//
//    public Boolean updateUserData(String id,String phone,String name,String itemList, String Total) {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id",Integer.parseInt(id));
//        contentValues.put("sellerPhone", phone);
//        contentValues.put("name", name);
//        contentValues.put("Total", Total);
//        contentValues.put("itemList", itemList);
//
//        long result = DB.update("DebtorsListTable", contentValues,"id=?",
//                new String[] {id});
//
//        if (result >0) {
//            return true;
//        } else {
//            return false;
//        }
//
//
//    }
//    public Boolean deleteUserdata(String id){
//        SQLiteDatabase DB=this.getWritableDatabase();
//
//        Cursor cursor=DB.rawQuery("Select * from DebtorsListTable where id=?", new String[]{id});
//        if(cursor.getCount()>0) {
//            long result = DB.delete("DebtorsListTable", "id=?", new String[]{id});
//            if (result == -1) {
//                return false;
//            } else {
//                return true;
//            }
//        }else{
//            return false;
//        }
//    }

    public Cursor getTimetableData() {
        String status = "NotYet";
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("Select * from Timetable where id !=?",new String[]{status});
        return cursor;
    }

    public Cursor getTimetableDataById(String Id) {

        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("Select * from Timetable where id=?",new String[] {Id});
        return cursor;
    }


    public Cursor getAuthenticationData(String regNo) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("Select * from Authentication  where regNo=?",new String[]{regNo});
        return cursor;
    }


    public Cursor getBookings(String regNo) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("Select * from Bookings  where regNo=?",new String[]{regNo});
        return cursor;
    }

    public Cursor getBookingsById(String id) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("Select * from Bookings  where id=?",new String[]{id});
        return cursor;
    }

    public Cursor getBookingsReport(String game) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor=DB.rawQuery("Select * from Bookings where numOfSessions =?",new String[]{game});
        return cursor;
    }
//    public Cursor getRatingsReport(String rating) {
//        SQLiteDatabase DB = this.getReadableDatabase();
//        Cursor cursor=DB.rawQuery("Select * from Ratings where rating =?",new String[]{rating});
//        return cursor;
//    }
    public Boolean checkUser(String regNo) {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * from authentication where regNo = ?",
                new String[] {regNo});
        if(cursor.getCount() >0) {
            return true;
        }else{
            return false;
        }
    }

    public int checkBookings(String game) {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * from Bookings where numOfSessions = ?",
                new String[] {game});
        if(cursor.getCount() >0) {
            return cursor.getCount();
        }else{
            return -1;
        }
    }
    public int getBookingsByExercise(String exercise, String date) {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * from Bookings where numOfSessions = ? and date=?",
                new String[] {exercise,date});
        if(cursor.getCount() >0) {
            return cursor.getCount();
        }else{
            return -1;
        }
    }

    public int getBookingsByExerciseByReg(String regNo,String exercise,String date) {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * from Bookings where regNo = ? and numOfSessions=? and date =?",
                new String[] {regNo,exercise,date});
        if(cursor.getCount() >0) {
            return cursor.getCount();
        }else{
            return -1;
        }
    }



    public Cursor checkRatings() {
        String id = "";
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = null;
        if (DB != null) {
            cursor = DB.rawQuery("Select * from Ratings where id != ?",
                    new String[]{id});
        }
        return cursor;
    }




    public Boolean updateTableTimeTable(String id, String date, String first, String second, String third, String status) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",Integer.parseInt(id));
        contentValues.put("date",date);
        contentValues.put("first",first);
        contentValues.put("second",second);
        contentValues.put("third",third);
        contentValues.put("status", status);


        long result = DB.update("Timetable", contentValues,"id=?",
                new String[] {id});

        if (result >0) {
            return true;
        } else {
            return false;
        }


    }

    public Boolean deleteUserdata(int id){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from Bookings where id=?", new String[]{String.valueOf(id)});
        if(cursor.getCount()>0) {
            long result = DB.delete("Bookings", "id=?", new String[]{String.valueOf(id)});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
}
