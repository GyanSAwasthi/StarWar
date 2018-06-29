package in.penzebra.gyan.com.starwar.interfacemvp;


import java.util.List;

public interface IResultView<T> {

    //onCreate
    void showResult(T listDO);

    //onMessage display
    void onDisplayMessage(String message);


}
