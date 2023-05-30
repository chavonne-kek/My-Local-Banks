package sg.edu.rp.c346.id22017979.mylocalbanks;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvBank1, tvBank2, tvBank3;
    String web = "";
    String num = "";
    String bank = "";


    boolean[] favourites = {false, false, false};

    ArrayList<TextView> tvList = new ArrayList<TextView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar;
        actionBar = getSupportActionBar();


        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#70B9B1"));

        actionBar.setBackgroundDrawable(colorDrawable);

        tvBank1 = findViewById(R.id.textViewBank1);
        tvBank2 = findViewById(R.id.textViewBank2);
        tvBank3 = findViewById(R.id.textViewBank3);

        tvList.add(tvBank1);
        tvList.add(tvBank2);
        tvList.add(tvBank3);

        registerForContextMenu(tvBank1);
        registerForContextMenu(tvBank2);
        registerForContextMenu(tvBank3);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        int groupId = 0;
        if (v == tvBank1) {
            groupId = 0;
            bank = getString(R.string.dbs);
            web = "https://www.dbs.com.sg";
            num = "18001111111";
        } else if (v == tvBank2) {
            groupId = 1;
            bank = getString(R.string.ocbc);
            web = "https://www.ocbc.com";
            num = "18003633333";
        } else {
            groupId = 2;
            bank = getString(R.string.uob);
            web = "https://www.uob.com.sg";
            num = "18002222121";
        }
        menu.add(groupId, 0, 0, "Website");
        menu.add(groupId, 1, 1, "Contact the bank");
        menu.add(groupId,2,2,"Toggle Favourite");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        int groupId = item.getGroupId();
        TextView textView = (TextView) tvList.get(groupId);
        if (id == 0) {
            Uri uri = Uri.parse(web);
            Intent intentWeb = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intentWeb);
        } else if (id == 1) {
            Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
            startActivity(intentCall);
        } else {
            if (favourites[groupId] == false) {
                favourites[groupId] = true;
                textView.setTextColor(Color.parseColor("#FF0000"));
            } else {
                favourites[groupId] = false;
                textView.setTextColor(Color.parseColor("#000000"));
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.englishSelection) {
            tvBank1.setText(getString(R.string.dbs));
            tvBank2.setText(getString(R.string.ocbc));
            tvBank3.setText(getString(R.string.uob));
        } else {
            tvBank1.setText(getString(R.string.dbs_cn));
            tvBank2.setText(getString(R.string.ocbc_cn));
            tvBank3.setText(getString(R.string.uob_cn));
        }

        return super.onOptionsItemSelected(item);
    }

}