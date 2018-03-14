package com.kosboss.gogift

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

/**
 * Created by hp on 014 14.03.2018.
 */
class DataBaseHelper
/**
 * Constructor
 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
 * @param context
 */
(private val myContext: Context) : SQLiteOpenHelper(myContext, DB_NAME, null, 1) {

    private var myDataBase: SQLiteDatabase? = null

    init {
        this.createDataBase()
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    fun createDataBase() {
        try {
            val dbExist = checkDataBase()

            if (dbExist) {
                //do nothing - database already exist
            } else {
                //By calling this method and empty database will be created into the default system path
                //of your application so we are gonna be able to overwrite that database with our database.
                this.readableDatabase


                copyDataBase()

            }
        } catch (e: Exception) {

        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private fun checkDataBase(): Boolean {

        var checkDB: SQLiteDatabase? = null

        try {
            val myPath = DB_PATH + DB_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)

        } catch (e: SQLiteException) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close()

        }

        return checkDB != null
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private fun copyDataBase() {

        try {
            //Open your local db as the input stream
            val myInput = myContext.assets.open(DB_NAME)

            // Path to the just created empty db
            val outFileName = DB_PATH + DB_NAME

            //Open the empty db as the output stream
            val myOutput = FileOutputStream(outFileName)

            //transfer bytes from the inputfile to the outputfile
            val buffer = ByteArray(1024)
            var length: Int
            while ((myInput.read(buffer)) > 0) {
                length = myInput.read(buffer)
                myOutput.write(buffer, 0, length)
            }

            //Close the streams
            myOutput.flush()
            myOutput.close()
            myInput.close()
        } catch (e: Exception) {
            //catch exception
        }

    }

    @Throws(SQLException::class)
    fun openDataBase(): SQLiteDatabase? {

        //Open the database
        val myPath = DB_PATH + DB_NAME
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE)
        return myDataBase

    }

    @Synchronized override fun close() {

        if (myDataBase != null) {
            myDataBase!!.close()
        }

        super.close()

    }

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
            copyDataBase()
        }
    }

    companion object {

        //The Android's default system path of your application database.
        //replace com.binarybricks.shippingwithsqllite with you Application package nae
        //This should be same as which you used package section in your manifest
        private val DB_PATH = "/data/data/com.kosboss.gogift/databases/"

        //replace this with name of your db file which you copied into asset folder
        private val DB_NAME = "Gifts"
    }


}
