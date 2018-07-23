package khan.examstressbuster;

import android.support.annotation.Nullable;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ActionBar actionBar = this.getSupportActionBar();

        // Set the action bar back button to look like an up button
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
     }
}
