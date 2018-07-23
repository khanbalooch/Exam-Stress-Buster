package khan.examstressbuster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Khan on 27-Feb-17.
 */

public class DBHelper extends SQLiteOpenHelper
{

    static String dbName = "Dua.db";
    static int dbVersion = 3;

    public DBHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        System.out.println("OnCREATE");
        final String createTable = "CREATE TABLE " + DuaContract.DuaEntry.DUA_TABLE +"("+
                DuaContract.DuaEntry.DUA_NAME              + " TEXT," +
                DuaContract.DuaEntry.COL_DUA_TEXT_ARABIC   + " TEXT," +
                DuaContract.DuaEntry.COL_DUA_TEXT_ENGLISH  + " TEXT," +
                DuaContract.DuaEntry.COL_DUA_TEXT_URDU     + " TEXT," +
                DuaContract.DuaEntry.COL_DUA_REFERENCE     + " TEXT);" ;

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + DuaContract.DuaEntry.DUA_TABLE);
    }
}
