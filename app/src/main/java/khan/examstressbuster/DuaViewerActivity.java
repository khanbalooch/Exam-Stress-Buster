package khan.examstressbuster;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.preference.Preference;
import android.support.design.widget.TabLayout;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

public class DuaViewerActivity extends AppCompatActivity //implements SharedPreferences.OnSharedPreferenceChangeListener
{

    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private SQLiteDatabase db;
    private Drawer drawer;

    private String [][] duas = {
            {       "الفاتحة",
                    "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ {1} الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ {2} الرَّحْمَٰنِ الرَّحِيمِ {3} مَالِكِ يَوْمِ الدِّينِ {4} إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ {5} اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ {6} صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ {7}(آمين)",
                    "[1] In the name of Allah, the Beneficent, the Merciful.[2] All praise is due to Allah, the Lord of the Worlds. [3] The Beneficent, the Merciful. [4] Master of the Day of Judgment. [5] Thee do we serve and Thee do we beseech for help. [6] Keep us on the right path. [7] The path of those upon whom Thou hast bestowed favors. Not (the path) of those upon whom Thy wrath is brought down, nor of those who go astray.(Ameen)",
                    "Surah 1, Ayat 1-7",
                    "0"},

            {       "Durrod o Salawat",
                    "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّد كَمَا صَلَّيْتَ عَلَى إِبْرَاهِيمَ وَعَلَى آلِ إِبْرَاهِيم إِنَّكَ حَمِيدٌ مَجِيد- اللَّهُمَّ بَارِكْ عَلَى مُحَمَّدٍ، وَعَلَى آلِ مُحَمَّد كَمَا بَارَكْتَ عَلَى إِبْرَاهِيمَ وَعَلَى آلِ إِبْرَاهِيم إِنَّكَ حَمِيدٌ مَجِيد",
                    "O Allah, let Your Peace come upon Muhammad and the family of Muhammad, as you have brought peace to Ibrahim and his family. Truly, You are Praiseworthy and Glorious. Allah, bless Muhammad and the family of Muhammad, as you have blessed Ibrahim and his family .Truly, You are Praiseworthy and Glorious.",
                    "",
                    "1"},

            {       "Supplication 3",
                    "وَإِذَا سَأَلَكَ عِبَادِي عَنِّي فَإِنِّي قَرِيبٌ ۖ أُجِيبُ دَعْوَةَ الدَّاعِ إِذَا دَعَانِ ۖ فَلْيَسْتَجِيبُوا لِي وَلْيُؤْمِنُوا بِي لَعَلَّهُمْ يَرْشُدُونَ {186}",
                    "[186] And when My servants ask you concerning Me, then surely I am very near; I answer the prayer of the suppliant when he calls on Me, so they should answer My call and believe in Me that they may walk in the right way.",
                    "Surah 2,Ayat 186",
                    "2"},

            {       "Supplication 4",
                    "فَإِنْ تَوَلَّوْا فَقُلْ حَسْبِيَ اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ ۖ عَلَيْهِ تَوَكَّلْتُ ۖ وَهُوَ رَبُّ الْعَرْشِ الْعَظِيمِ {129}",
                    "[129] But if they turn back, say: Allah is sufficient for me, there is no god but He; on Him do I rely, and He is the Lord of mighty power.",
                    "Surah 9, Ayat 129",
                    "3"},

            {       "Supplication 5",
                    "حَسْبُنَا اللَّهُ وَنِعْمَ الْوَكِيلُ {173}",
                    "[173] Allah is sufficient for us and most excellent is the Protector.",
                    "Surah 3,Ayat 173",
                    "4"},

            {       "Supplication 6",
                    "فَنِعْمَ الْمَوْلَىٰ وَنِعْمَ النَّصِيرُ {78}",
                    "[78]how excellent the Guardian and how excellent the Helper!",
                    "Surah 22,Ayat 78",
                    "5"},

            {       "Supplication 7",
                    "قَالَ رَبِّ اشْرَحْ لِي صَدْرِي {25} وَيَسِّرْ لِي أَمْرِي {26} وَاحْلُلْ عُقْدَةً مِنْ لِسَانِي {27} يَفْقَهُوا قَوْلِي {28}",
                    "[25] He said: O my Lord! Expand my breast for me, [26] And make my affair easy to me, [27] And loose the knot from my tongue, [28] (That) they may understand my word;",
                    "Surah 20,Ayat 25-28",
                    "6"},

            {       "Supplication 8",
                    "إِذَا جَاءَ نَصْرُ اللَّهِ وَالْفَتْحُ {1} وَرَأَيْتَ النَّاسَ يَدْخُلُونَ فِي دِينِ اللَّهِ أَفْوَاجًا {2} فَسَبِّحْ بِحَمْدِ رَبِّكَ وَاسْتَغْفِرْهُ ۚ إِنَّهُ كَانَ تَوَّابًا {3}",
                    "[1] When there comes the help of Allah and the victory, [2] And you see men entering the religion of Allah in companies, [3] Then celebrate the praise of your Lord, and ask His forgiveness; surely He is oft-returning (to mercy).",
                    "Surah 110, Ayat 1-3",
                    "7"},

            {       "Supplication 9",
                    "رَبَّنَا لَا تُزِغْ قُلُوبَنَا بَعْدَ إِذْ هَدَيْتَنَا وَهَبْ لَنَا مِنْ لَدُنْكَ رَحْمَةً ۚ إِنَّكَ أَنْتَ الْوَهَّابُ {8}",
                    "[8] Our Lord! make not our hearts to deviate after Thou hast guided us aright, and grant us from Thee mercy; surely Thou art the most liberal Giver.",
                    "Surah 3,Ayat 8",
                    "8"},

            {       "Supplication 10",
                    "قُلِ اللَّهُمَّ مَالِكَ الْمُلْكِ تُؤْتِي الْمُلْكَ مَنْ تَشَاءُ وَتَنْزِعُ الْمُلْكَ مِمَّنْ تَشَاءُ وَتُعِزُّ مَنْ تَشَاءُ وَتُذِلُّ مَنْ تَشَاءُ ۖ بِيَدِكَ الْخَيْرُ ۖ إِنَّكَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ {26}",
                    "[26] Say: O Allah, Master of the Kingdom! Thou givest the kingdom to whomsoever Thou pleasest and takest away the kingdom from whomsoever Thou pleasest, and Thou exaltest whom Thou pleasest and abasest whom Thou pleasest in Thine hand is the good; surety, Thou hast power over all things.",
                    "Surah 3, Ayat 26",
                    "9"},
    };
    //@Override
    /*protected void onRestart()
    {
        super.onRestart();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        System.out.println("ON CREATE resume VIEW");
    }*/
    private void setUpDrawer()
    {
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.child).build();

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar).withDisplayBelowStatusBar(true)
                .withCloseOnClick(true)
                .withActionBarDrawerToggleAnimated(true)

                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withSliderBackgroundColor(Color.LTGRAY)
                .withAccountHeader(headerResult)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem)
                    {

                        switch ((int) drawerItem.getIdentifier())
                        {

                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                                drawer.closeDrawer();
                                openPage((int)drawerItem.getIdentifier() );
                                break;
                            case 100:
                                drawer.closeDrawer();
                                openSetting();
                                break;
                            case 200:
                                drawer.closeDrawer();
                                break;
                            case 300:
                                drawer.closeDrawer();
                                break;
                        }
                        return true;
                    }
                })
                .build();

        Cursor cursor;

        cursor =    db.rawQuery("Select * from "+DuaContract.DuaEntry.DUA_TABLE,null);
        cursor.moveToFirst();
        List<IDrawerItem> secItems = new ArrayList<IDrawerItem>();

        cursor =    db.rawQuery("Select * from "+DuaContract.DuaEntry.DUA_TABLE,null);
        cursor.moveToFirst();

        for(int i=0; i< cursor.getCount(); i++)
        {
            String name = cursor.getString( cursor.getColumnIndex(DuaContract.DuaEntry.DUA_NAME ) );
            secItems.add( new SecondaryDrawerItem().withIdentifier(i).withName(name) );
            cursor.moveToNext();

        }
        PrimaryDrawerItem duasItem = new PrimaryDrawerItem().withName("Supplications").withSubItems(secItems);

        drawer.addItem(duasItem.withIdentifier(-1).withIcon(R.drawable.dua) ) ;
        drawer.addItem(new DividerDrawerItem());
        drawer.addItem(new PrimaryDrawerItem().withName("Settings").withIdentifier(100).withIcon(R.drawable.settings)  );
        drawer.addItem(new PrimaryDrawerItem().withName("Help").withIdentifier(200).withIcon(R.drawable.help));
        drawer.addItem(new PrimaryDrawerItem().withName("Contact").withIdentifier(300).withIcon(R.drawable.contact));

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dua_viewer_layout);

        DBHelper helper = new DBHelper(this);
        db = helper.getWritableDatabase();

        try
        {
            db.execSQL("delete from "+ DuaContract.DuaEntry.DUA_TABLE );
            ContentValues cv = new ContentValues();
            for(int i=0; i<duas.length; i++ )
            {
                cv.put(DuaContract.DuaEntry.DUA_NAME ,            duas[i][0]);
                cv.put(DuaContract.DuaEntry.COL_DUA_TEXT_ARABIC , duas[i][1]);
                cv.put(DuaContract.DuaEntry.COL_DUA_TEXT_ENGLISH ,duas[i][2]);
                cv.put(DuaContract.DuaEntry.COL_DUA_TEXT_URDU ,   "");
                cv.put(DuaContract.DuaEntry.COL_DUA_REFERENCE ,   duas[i][3]);

                db.insert(DuaContract.DuaEntry.DUA_TABLE,null,cv);
                ///cv.clear();
            }


        }catch(Exception e)
        {
            e.printStackTrace();
        }

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        setUpDrawer();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void openPage(int pageNo)
    {
        viewPager.setCurrentItem(pageNo ,true);
    }
    private void openSetting()
    {
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }
    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Cursor cursor;

            cursor =    db.rawQuery("Select * from "+DuaContract.DuaEntry.DUA_TABLE,null);
            cursor.moveToFirst();




            for(int i=0; i< cursor.getCount(); i++)
            {
                String name = cursor.getString( cursor.getColumnIndex(DuaContract.DuaEntry.DUA_NAME ) );
                Bundle args = new Bundle();
                args.putString(DuaContract.DuaEntry.DUA_NAME, name );
                //secItems.add( new SecondaryDrawerItem().withIdentifier(i).withName(name) );
                DuaFragment df = new DuaFragment();
                df.setArguments(args);
                adapter.addFragment(df, name);
                cursor.moveToNext();

            }

        viewPager.setAdapter(adapter);
    }

    /*@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }*/
}