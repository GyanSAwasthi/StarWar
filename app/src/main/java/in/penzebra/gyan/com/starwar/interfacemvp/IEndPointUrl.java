package in.penzebra.gyan.com.starwar.interfacemvp;


import com.google.gson.JsonObject;

import in.penzebra.gyan.com.starwar.model.ResponseResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IEndPointUrl {



    @GET("people/")
    @Headers({"Content-Type: application/json"})
    Call<ResponseResult> getUserList(@Query("page") int pageNo);
}
