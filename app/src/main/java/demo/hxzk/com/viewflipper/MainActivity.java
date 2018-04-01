package demo.hxzk.com.viewflipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewFlipper mViewFlipper;
    Button btn_stop;
    Button btn_start;
    Button btn_currentView;

    View childView;


    TextView adOne;

    TextView adTwo;


//    //数据源
    LinkedList<String> listData;
    LinkedList<String> listDataTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewFlipper = findViewById(R.id.viewflipper);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_currentView=findViewById(R.id.btn_currentView);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_currentView.setOnClickListener(this);

       listData = new LinkedList<>();
        listDataTwo = new LinkedList<>();
        //添加数据
        for (int i = 0; i < 10; i++) {
            listData.add(getTel());
            listDataTwo.add(getTel());

            //子布局view
            childView = View.inflate(this, R.layout.item_viewflipper, null);
            //垂直广告第一个textview
            adOne = childView.findViewById(R.id.tv_advertisingone);
            adOne.setOnClickListener(this);
            //垂直广告第二个textview
            adTwo = childView.findViewById(R.id.tv_advertisingtwo);
            adTwo.setOnClickListener(this);
            //设置内容
            adOne.setText(i+1 +"期中奖号码是:" + listData.get(i));
            adTwo.setText(i+1 +"期中奖号码是:" +listDataTwo.get(i));
            //添加到ViewFlipper
            mViewFlipper.addView(childView);
        }


        btn_stop = findViewById(R.id.btn_stop);
        btn_start = findViewById(R.id.btn_start);
    }

    /**
     * 随机返回手机号码
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153"
            .split(",");

    private static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + thrid;
    }

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_stop:
                mViewFlipper.stopFlipping();//  停止View的切换


                break;
            case R.id.btn_start:
                mViewFlipper.startFlipping();//开始View的切换，而且会循环进行

                break;

      case R.id.tv_advertisingone:
          int currentChildIndex=mViewFlipper.getDisplayedChild();
          String currentIndexContent =listData.get(currentChildIndex);
          Toast.makeText(MainActivity.this,"您点击的是:"+currentIndexContent,Toast.LENGTH_LONG).show();

          break;
      case R.id.tv_advertisingtwo:
          int currentChildIndexTwo=mViewFlipper.getDisplayedChild();
          String currentIndexContentTwo =listDataTwo.get(currentChildIndexTwo);
          Toast.makeText(MainActivity.this, "您点击的是:"+currentIndexContentTwo,Toast.LENGTH_LONG).show();

          break;

                case R.id.btn_currentView:
                    //获取当前ViewFlipper展现view的下标和数据源对应
                   int currentChild=mViewFlipper.getDisplayedChild();

                    //获取点击数据方式一:
                    //  String currentContent=listData.get(currentChild);

                    //获取点击数据方式二:
                    View currentView =mViewFlipper.getCurrentView();
                    TextView mTextView =currentView.findViewById(R.id.tv_advertisingone);
                    TextView mTextViewTwo =currentView.findViewById(R.id.tv_advertisingtwo);
                     String currentContent=mTextView.getText().toString();
                     String currentContentTwo=mTextViewTwo.getText().toString();
                     Toast.makeText(MainActivity.this,"您点击的是:"+currentContent+"和"+currentContentTwo,Toast.LENGTH_LONG).show();
break;

        }
    }
}
