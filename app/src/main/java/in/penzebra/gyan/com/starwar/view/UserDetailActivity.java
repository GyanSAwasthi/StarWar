package in.penzebra.gyan.com.starwar.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import in.penzebra.gyan.com.starwar.R;

public class UserDetailActivity extends AppCompatActivity  {


    private TextView mTvHeader;
    private TextView mTvname;
    private TextView mTvheight;
    private TextView mTvmass;
    private TextView mTvdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_detail);
        initView();
        getData();


    }

    private void getData() {
        mTvname.setText(getIntent().getStringExtra("name"));
        mTvheight.setText(getIntent().getStringExtra("height"));
        mTvmass.setText(getIntent().getStringExtra("mass"));
        mTvdate.setText(getIntent().getStringExtra("dateNtime"));
    }

    private void initView() {
        mTvname = (TextView) findViewById(R.id.tvname);
        mTvheight = (TextView) findViewById(R.id.tvheight);
        mTvmass = (TextView) findViewById(R.id.tvmass);
        mTvdate = (TextView) findViewById(R.id.tvdate);
    }


}
