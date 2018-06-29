package in.penzebra.gyan.com.starwar.presenter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;


import in.penzebra.gyan.com.starwar.interfacemvp.IEndPointUrl;
import in.penzebra.gyan.com.starwar.interfacemvp.INetworkResult;
import in.penzebra.gyan.com.starwar.interfacemvp.IResultView;
import in.penzebra.gyan.com.starwar.model.ResponseResult;
import in.penzebra.gyan.com.starwar.networkclient.NetworkClient;
import in.penzebra.gyan.com.starwar.networkclient.NetworkConstants;
import in.penzebra.gyan.com.starwar.networkclient.NetworkHandler;
import retrofit2.Call;

public class AuthenticateImpl implements INetworkResult {


    private IEndPointUrl endPointsAPI;
    private Context context;
    private NetworkHandler networkHandler;
    private IResultView iResultViewListener;

    public AuthenticateImpl(Context context, IResultView iResultView) {
        this.context = context;
        this.iResultViewListener = iResultView;
        endPointsAPI = NetworkClient.getClient().create(IEndPointUrl.class);
        networkHandler = new NetworkHandler(this);

    }



    @SuppressWarnings("unchecked")
    public void callLoginAPI() {

        Call authenticateResponseCall = endPointsAPI.getUserList();
        authenticateResponseCall.enqueue(networkHandler.EnqueueRequest(NetworkConstants.RESPONSE_TYPE.RETRIEVE_TYPE));


    }



    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(Object responseBody, int responseType) {

       ResponseResult authenticateModel = (ResponseResult) responseBody;

        switch (responseType) {

            case NetworkConstants.RESPONSE_TYPE.RETRIEVE_TYPE:
                iResultViewListener.showResult(responseBody);

                break;
        }
    }


    @Override
    public void onError(String message) {

        iResultViewListener.onDisplayMessage(message);
    }
}
