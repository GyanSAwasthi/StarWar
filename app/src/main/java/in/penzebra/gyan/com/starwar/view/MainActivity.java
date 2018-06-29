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
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;



import in.penzebra.gyan.com.starwar.R;
import in.penzebra.gyan.com.starwar.interfacemvp.IResultView;
import in.penzebra.gyan.com.starwar.model.ResponseResult;
import in.penzebra.gyan.com.starwar.presenter.AuthenticateImpl;
import in.penzebra.gyan.com.starwar.utillity.ConnectionDetector;
import in.penzebra.gyan.com.starwar.utillity.RecyclerTouchListener;
import in.penzebra.gyan.com.starwar.view.adapter.CustomAdapter;


public class MainActivity extends AppCompatActivity implements IResultView {
    Context context;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private static ArrayList<ResponseResult.Result> responseResultList;
    private ProgressDialog dialog;
    Boolean isinternetConnection = false;
    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSyn = (Button) findViewById(R.id.btnsyn);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        dialog = new ProgressDialog(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        context = this;
        connectionDetector = new ConnectionDetector(context);
        isinternetConnection = connectionDetector.isInternetOn();
        if (isinternetConnection) {
            responseResultList.clear();
            callApi();

        }else{
            Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }


        btnSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connectionDetector = new ConnectionDetector(context);
                isinternetConnection = connectionDetector.isInternetOn();
                if (isinternetConnection) {
                    responseResultList.clear();
                    callApi();

                }else{
                    Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void callApi() {
        AuthenticateImpl authenticateImpl = new AuthenticateImpl(context, this);
        authenticateImpl.callAPI();
        dialog.setMessage("Loading, please wait.");
        dialog.show();
    }


    @Override
    public void showResult(Object listDO) {
        final int count=0;
        responseResultList = ((ResponseResult) listDO).getResults();
        dialog.dismiss();
        CustomAdapter adapter = new CustomAdapter(this, ((ResponseResult) listDO).getResults());
        recyclerView.setAdapter(adapter);



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(count==0) {
                    Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
                    intent.putExtra("name", responseResultList.get(position).getName());
                    intent.putExtra("height", responseResultList.get(position).getHeight());
                    intent.putExtra("mass", responseResultList.get(position).getMass());
                    intent.putExtra("dateNtime", responseResultList.get(position).getCreated());


                    startActivity(intent);
                }

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
