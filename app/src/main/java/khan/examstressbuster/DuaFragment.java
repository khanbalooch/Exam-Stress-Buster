package khan.examstressbuster;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khan on 27-Feb-17.
 */

public class DuaFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = (View ) inflater.inflate(R.layout.dua_page_layout,container,false);

        recyclerView = (RecyclerView) v.findViewById(R.id.cardList);

        LinearLayoutManager llm = new LinearLayoutManager( getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        recyclerView.setAdapter(new DuaCardAdapter( getList() ));

        return v;
    }

    private List<DuaInfo> getList()
    {
        List<DuaInfo> duas = new ArrayList<DuaInfo>();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean show_reference = preferences.getBoolean(getString(R.string.pref_references_key_reference),true);
        boolean show_arabic = preferences.getBoolean(getString( R.string.pref_language_key_arabic),false);
        boolean show_english = preferences.getBoolean(getString( R.string.pref_language_key_english),false);

        DBHelper helper = new DBHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        String sname = (String)getArguments().get(DuaContract.DuaEntry.DUA_NAME);
        String [] args = {sname};

        Cursor cursor = db.rawQuery("select * from "+ DuaContract.DuaEntry.DUA_TABLE + " where "+DuaContract.DuaEntry.DUA_NAME+"= ? ",args);
        cursor.moveToFirst();

        String name = cursor.getString ( cursor.getColumnIndex(DuaContract.DuaEntry.DUA_NAME) );
        String text = cursor.getString ( cursor.getColumnIndex(DuaContract.DuaEntry.COL_DUA_TEXT_ARABIC) );
        String reference = show_reference? cursor.getString ( cursor.getColumnIndex(DuaContract.DuaEntry.COL_DUA_REFERENCE) ):"";
        String english = cursor.getString ( cursor.getColumnIndex(DuaContract.DuaEntry.COL_DUA_TEXT_ENGLISH) );

        if(show_arabic && show_english)
        {
            System.out.println("both");
            duas.add( new DuaInfo(name,text,reference) );
            duas.add(new DuaInfo("English Translation",english,reference));
        }else if(show_arabic)
        {
            System.out.println("arabic");
            duas.add( new DuaInfo(name,text,reference) );
        }else if(show_english)
        {
            System.out.println("english");
            duas.add(new DuaInfo("English Translation",english,reference));
        }

        preferences.registerOnSharedPreferenceChangeListener(this);
        return duas;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        recyclerView.setAdapter(new DuaCardAdapter(getList()));
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister VisualizerActivity as an OnPreferenceChangedListener to avoid any memory leaks.
        PreferenceManager.getDefaultSharedPreferences(this.getActivity())
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}