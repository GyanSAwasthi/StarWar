package in.penzebra.gyan.com.starwar.interfacemvp;



public interface INetworkResult<T> {

    void onSuccess(T responseBody, int responseType);

    void onError(String message);


}
