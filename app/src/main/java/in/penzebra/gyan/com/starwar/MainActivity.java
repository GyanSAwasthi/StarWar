//package in.penzebra.gyan.com.starwar;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity implements IUserList{
//    private static ListPresenter lPresenter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        if (lPresenter == null) {//first time creating activity = create presenter
//            lPresenter = new ListPresenter();
//        }
//    }
//
//
//    @Override
//    public void onSuccess() {
//
//    }
//
//    @Override
//    public void onError(String error) {
//
//    }
//
//    @Override
//    public void showProgress(boolean show) {
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (isFinishing()) {//activity finishing, release reference to presenter
//            lPresenter = null;
//        }
//    }
//}
