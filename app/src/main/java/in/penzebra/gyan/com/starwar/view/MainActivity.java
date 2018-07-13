package in.penzebra.gyan.com.starwar.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    public static ArrayList<ResponseResult.Result> responseResultList = null;
    private ProgressDialog dialog;
    Boolean isinternetConnection = false;
    ConnectionDetector connectionDetector;
    ImageButton btnSync;
    int currentPage = 1;
    int totalPages = 9;
    boolean scorllLastFlag = false;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSync = (ImageButton) findViewById(R.id.btnSyc);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        dialog = new ProgressDialog(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        context = this;

        syncData();
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage <= totalPages) {

                    syncData();


                }

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                if (currentPage > totalPages) {
                    return;
                }

                if (!recyclerView.canScrollVertically(1)) {


                    if (currentPage <= totalPages) {

                        scorllLastFlag = true;
                        //callApi();
                        syncData();

                        // Toast.makeText(context, "" + currentPage, Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });


    }

    private void syncData() {
        connectionDetector = new ConnectionDetector(context);
        isinternetConnection = connectionDetector.isInternetOn();
        if (isinternetConnection) {

            callApi();

        } else {
            Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }


    private void callApi() {
        try {
            AuthenticateImpl authenticateImpl = new AuthenticateImpl(this);
            authenticateImpl.callAPI(currentPage);

            Log.e("currentPage", "" + currentPage);
            dialog.setMessage("Loading, please wait.");
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showResult(Object listDO) {
        try {
            dialog.dismiss();
            if (responseResultList != null) {

                for (int x = 0; x < ((ResponseResult) listDO).getResults().size(); x++) {
                    responseResultList.add(((ResponseResult) listDO).getResults().get(x));
                }
                adapter = new CustomAdapter(this, responseResultList);
                recyclerView.setAdapter(adapter);
                if (currentPage == 1) {
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            } else {
                responseResultList = ((ResponseResult) listDO).getResults();
                adapter = new CustomAdapter(this, responseResultList);
                recyclerView.setAdapter(adapter);
                /*if (currentPage == 1) {
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }*/
            }
            currentPage = currentPage + 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisplayMessage(String message) {
        try {
            dialog.dismiss();
            if (message == null)
                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
