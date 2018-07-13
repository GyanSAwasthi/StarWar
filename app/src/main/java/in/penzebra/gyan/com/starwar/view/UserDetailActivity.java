package in.penzebra.gyan.com.starwar.view;

import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Date;

import in.penzebra.gyan.com.starwar.R;

public class UserDetailActivity extends AppCompatActivity  {


    private TextView mTvHeader;
    private TextView mTvname;
    private TextView mTvheight;
    private TextView mTvmass;
    private TextView mTvdate;
    float heightMtrs;
    String finalDateString = "";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        initView();
        dataConverion();
        getData();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void dataConverion() {
        String height = getIntent().getStringExtra("height");
        heightMtrs = Float.parseFloat(height)/100;
        String date = getIntent().getStringExtra("dateNtime");
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
            SimpleDateFormat sdfnewformat = new SimpleDateFormat("dd-MM-yyyy");
            finalDateString = sdfnewformat.format(convertedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getData() {
        mTvname.setText(getIntent().getStringExtra("name"));
        mTvheight.setText(String.valueOf(heightMtrs));
        mTvmass.setText(getIntent().getStringExtra("mass"));
        mTvdate.setText(finalDateString);
    }

    private void initView() {
        mTvname = (TextView) findViewById(R.id.tvname);
        mTvheight = (TextView) findViewById(R.id.tvheight);
        mTvmass = (TextView) findViewById(R.id.tvmass);
        mTvdate = (TextView) findViewById(R.id.tvdate);
    }


}
