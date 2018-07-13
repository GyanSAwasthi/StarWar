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

    public AuthenticateImpl(IResultView iResultView) {
        try {
            // this.context = context;
            this.iResultViewListener = iResultView;
            endPointsAPI = NetworkClient.getClient().create(IEndPointUrl.class);
            networkHandler = new NetworkHandler(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @SuppressWarnings("unchecked")
    public void callAPI(int currentpage) {

        Call authenticateResponseCall = endPointsAPI.getUserList(currentpage);
        authenticateResponseCall.enqueue(networkHandler.EnqueueRequest(NetworkConstants.RESPONSE_TYPE.RETRIEVE_TYPE));


    }


    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(Object responseBody, int responseType) {
        try {
            ResponseResult authenticateModel = (ResponseResult) responseBody;

            switch (responseType) {

                case NetworkConstants.RESPONSE_TYPE.RETRIEVE_TYPE:
                    iResultViewListener.showResult(responseBody);

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onError(String message) {

        iResultViewListener.onDisplayMessage(message);
    }
}
