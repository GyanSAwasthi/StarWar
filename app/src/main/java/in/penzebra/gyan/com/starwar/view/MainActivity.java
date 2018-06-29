package in.penzebra.gyan.com.starwar.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


import in.penzebra.gyan.com.starwar.R;
import in.penzebra.gyan.com.starwar.interfacemvp.IResultView;
import in.penzebra.gyan.com.starwar.model.ResponseResult;
import in.penzebra.gyan.com.starwar.presenter.AuthenticateImpl;
import in.penzebra.gyan.com.starwar.utillity.RecyclerTouchListener;
import in.penzebra.gyan.com.starwar.view.adapter.CustomAdapter;


public class MainActivity extends AppCompatActivity implements IResultView {
    Context context;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private static ArrayList<ResponseResult.Result> responseResultList;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        dialog = new ProgressDialog(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        context = this;
        callApi();


    }

    private void callApi() {
        AuthenticateImpl authenticateImpl = new AuthenticateImpl(context, this);
        authenticateImpl.callLoginAPI();
        dialog.setMessage("Loading, please wait.");
        dialog.show();
    }


    @Override
    public void showResult(Object listDO) {
        responseResultList = ((ResponseResult) listDO).getResults();
        dialog.dismiss();
        CustomAdapter adapter = new CustomAdapter(this, ((ResponseResult) listDO).getResults());
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
                intent.putExtra("name", responseResultList.get(position).getName());
                intent.putExtra("height", responseResultList.get(position).getHeight());
                intent.putExtra("mass", responseResultList.get(position).getMass());
                intent.putExtra("dateNtime", responseResultList.get(position).getCreated());
                startActivity(intent);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public void onDisplayMessage(String message) {
        dialog.dismiss();
        if (message == null)
            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }


}
