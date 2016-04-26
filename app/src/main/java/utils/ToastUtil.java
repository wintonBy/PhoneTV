package utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wasu.winton.phonetv.BaseApplication;
import com.wasu.winton.phonetv.R;


/**
 * Created by winton on 2016/3/14.
 */
public class ToastUtil {

    private static Context mContext = BaseApplication.getInstance(); //ApplicationContext唯一
    private static LayoutInflater inflater = LayoutInflater.from(mContext);
    private static View mToastView = inflater.inflate(R.layout.toastview, null);
    private static TextView msgView = (TextView) mToastView.findViewById(R.id.tv_msg_text);

    private static final int TYPE_CODE_SUCCESS = 0x01;
    private static final int TYPE_CODE_ERROR = 0x02;
    private static final int COLOR_SUCCESS = mContext.getResources().getColor(R.color.toast_msg_status_success);
    private static final int COLOR_ERROR = mContext.getResources().getColor(R.color.toast_msg_status_error);
    private static final int DEFAULT_TIME_DELAY = 50;// 单位：毫秒

    private static Toast toast;
    private static Handler handler;

    /**
     * @ToDo（显示Success消息）
     */
    public static void showSuccessMsg(int strId){
        if(mContext == null){
            return;
        }
        try{
            showSuccessMsg(mContext.getString(strId));
        }catch (Resources.NotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * @Todo(显示警告消息)
     * @param strId
     */
    public static void showErrorMsg(int strId){
        if(mContext == null){
            return;
        }
        try{
            showErrorMsg(mContext.getString(strId));
        }catch (Resources.NotFoundException e){
            e.printStackTrace();
        }
    }
    public static void showSuccessMsg(String msg){
        showMsg(TYPE_CODE_SUCCESS,msg);
    }
    public static void showErrorMsg(String msg){
        showMsg(TYPE_CODE_ERROR,msg);
    }

    private static void showMsg(final int typeCode, final String msg){
        if(mContext == null || !ApplicationUtil.isForeground(mContext) ||msg == null){
            return;
        }

        if(toast == null){
            toast = new Toast(mContext);
        }
        if(handler == null){
            handler = new Handler();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int msgBgColor = 0;
                switch (typeCode){

                    case TYPE_CODE_ERROR:
                        msgBgColor = COLOR_ERROR;
                        break;
                    case TYPE_CODE_SUCCESS:
                    default:
                        msgBgColor = COLOR_SUCCESS;
                        break;
                }
                msgView.setBackgroundColor(msgBgColor);
                msgView.setText(msg);
                toast.setView(mToastView);
                toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL,0,0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        },DEFAULT_TIME_DELAY);

    }

}
