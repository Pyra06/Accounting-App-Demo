package com.example.accountbookv1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DataBaseHandler(private var context: Context) : SQLiteOpenHelper(context, "MyDB.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTableMA = "CREATE TABLE " + "UsersMA" + " (" +
                "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name" + " VARCHAR(100), " +
                "type" + " CHAR(1))"

        val createTableMDT = "CREATE TABLE " + "UsersMDT" + " (" +
                "trnsID" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id" + " INTEGER, " +
                "date" + " VARCHAR(20), " +
                "note" + " VARCHAR, " +
                "amount" + " INTEGER, " +
                "type" + " CHAR(1))"

        db?.execSQL(createTableMA)
        db?.execSQL(createTableMDT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertDataMA(name: String, type: String) {
        val result = this.readableDatabase.rawQuery("Select * from UsersMA where (name,type) = ('$name', '$type')", null)

        try {
            if(result.count == 0) {
                try {
                    this.writableDatabase.execSQL("Insert into UsersMA(name,type) values ('$name', '$type')")
                    this.writableDatabase.close()
                } catch (e: Exception) {
                    Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Entry Exist", Toast.LENGTH_SHORT).show()
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    fun insertDataMDT(id: Int, date: String, note: String, type: String, amount: String) {
        try {
            this.writableDatabase.execSQL("Insert into UsersMDT(id,date,note,type,amount) values ($id,'$date','$note','$type','$amount')")
            this.writableDatabase.close()
            Toast.makeText(context, "Transaction Added", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Super Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    fun getAccountID(name: String, type: String) : ArrayList<UserID> {
        val result = this.readableDatabase.rawQuery("Select * from UsersMA where (name,type) = ('$name','$type')", null)
        val userList = ArrayList<UserID>()

        try{
            if(result.count == 0) {
                Toast.makeText(context, "No Country To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserID()
                    user.id = result.getInt(result.getColumnIndex("id"))
                    userList.add(user)
                }
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        this.readableDatabase.close()
        return userList
    }

    fun getAccountList() : ArrayList<UserSpinner> {
        val result = this.readableDatabase.rawQuery("Select * from UsersMA", null)
        val userList = ArrayList<UserSpinner>()

        try {
            if(result.count == 0) {
            } else {
                while (result.moveToNext()){
                    val user = UserSpinner()
                    user.name = result.getString(result.getColumnIndex("name"))
                    user.type = result.getString(result.getColumnIndex("type"))
                    userList.add(user)
                }
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        return userList
    }

    fun getAccountDetailsMACase1(idFrom: Int, idTo: Int, name: String, type: String) : ArrayList<UserMRA> {
        val result = this.readableDatabase.rawQuery("Select * from UsersMA where type like '%$type%' and name like '%$name%' and id between $idFrom and $idTo", null)
        val userList = ArrayList<UserMRA>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No Accounts To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserMRA()
                    user.id = result.getInt(result.getColumnIndex("id"))
                    user.name = result.getString(result.getColumnIndex("name"))
                    user.type = result.getString(result.getColumnIndex("type"))
                    userList.add(user)
                }
            }
            result.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        this.readableDatabase.close()
        return userList
    }

    fun getAccountDetailsMACase2(id: Int, name: String, type: String) : ArrayList<UserMRA> {
        val result = this.readableDatabase.rawQuery("Select * from UsersMA where type like '%$type%' and name like '%$name%' and id = $id", null)
        val userList = ArrayList<UserMRA>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No Accounts To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserMRA()
                    user.id = result.getInt(result.getColumnIndex("id"))
                    user.name = result.getString(result.getColumnIndex("name"))
                    user.type = result.getString(result.getColumnIndex("type"))
                    userList.add(user)
                }
            }
            result.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        this.readableDatabase.close()
        return userList
    }


    fun getAccountDetailsMACase3(name: String, type: String) : ArrayList<UserMRA> {
        val result = this.readableDatabase.rawQuery("Select * from UsersMA where type like '%$type%' and name like '%$name%'", null)
        val userList = ArrayList<UserMRA>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No Accounts To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserMRA()
                    user.id = result.getInt(result.getColumnIndex("id"))
                    user.name = result.getString(result.getColumnIndex("name"))
                    user.type = result.getString(result.getColumnIndex("type"))
                    userList.add(user)
                }
            }
            result.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        this.readableDatabase.close()
        return userList
    }

    fun getAccountDetailsMDT(id: Int) : ArrayList<UserMACom> {
        val result = this.readableDatabase.rawQuery("Select * from UsersMA where id = $id", null)
        val userList = ArrayList<UserMACom>()

        try {
            if(result.count == 0) {
                Toast.makeText(context, "No Accounts To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserMACom()
                    user.name = result.getString(result.getColumnIndex("name"))
                    user.type = result.getString(result.getColumnIndex("type"))
                    userList.add(user)
                }
            }
            result.close()
            this.readableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        return userList
    }

    fun getUserMA(nCtx : Context) : ArrayList<UserMA> {
        val result = this.readableDatabase.rawQuery("Select * from UsersMA", null)
        val userList = ArrayList<UserMA>()

        try {
            if(result.count == 0) {
                Toast.makeText(nCtx, "No Accounts To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserMA()
                    user.id = result.getInt(result.getColumnIndex("id"))
                    user.name = result.getString(result.getColumnIndex("name"))
                    user.type = result.getString(result.getColumnIndex("type"))
                    userList.add(user)
                }
                Toast.makeText(nCtx, "${result.count} Accounts Found", Toast.LENGTH_SHORT).show()
            }
            result.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
        this.readableDatabase.close()
        return userList
    }

    fun getUserMDT(nCtx : Context) : ArrayList<UserMDT> {
        val result = this.readableDatabase.rawQuery("Select * from UsersMDT", null)
        val userList = ArrayList<UserMDT>()

        try {
            if(result.count == 0) {
                Toast.makeText(nCtx, "No Transactions To Show", Toast.LENGTH_SHORT).show()
            } else {
                while (result.moveToNext()){
                    val user = UserMDT()
                    user.id = result.getInt(result.getColumnIndex("id"))
                    user.trnsID = result.getInt(result.getColumnIndex("trnsID"))
                    user.date = result.getString(result.getColumnIndex("date"))
                    user.note = result.getString(result.getColumnIndex("note"))
                    user.amount = result.getInt(result.getColumnIndex("amount"))
                    user.type = result.getString(result.getColumnIndex("type"))
                    userList.add(user)
                }
                Toast.makeText(nCtx, "${result.count} Accounts Found", Toast.LENGTH_SHORT).show()
            }
            result.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went extra Wrong", Toast.LENGTH_SHORT).show()
        }
        this.readableDatabase.close()
        return userList
    }

    fun editDataMA(id: Int, name: String, type: String){
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("type", type)

        try {
            this.writableDatabase.update("UsersMA", cv, "id = ?", arrayOf(id.toString()))
            this.writableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    fun editDataMDT(id: Int, amount: Int, note: String){
        val cv = ContentValues()
        cv.put("amount", amount)
        cv.put("note", note)

        try {
            this.writableDatabase.update("UsersMDT", cv, "trnsID = ?", arrayOf(id.toString()))
            this.writableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteDataMDT(id: Int) {
        try {
            this.writableDatabase.execSQL("Delete from UsersMDT where trnsID = $id")
            this.writableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteDataMA(id: Int) {
        try {
            this.writableDatabase.execSQL("Delete from UsersMA where id = $id")
            this.writableDatabase.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }
}