package com.wasu.winton.mynetwork.utils;

/**
 * Created by Administrator on 2016/1/25.
 */

    import android.telephony.TelephonyManager;
    import android.content.Context;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;

    public class  NetworkStateTools {

        /**
         * @Fields NETWORKTYPE_INVALID : TODO(不可用网络)
         */
        public static final int  NETWORKTYPE_INVALID=0;

        public static final int NETWORKTYPE_WIFI=1;

        public static final int NETWORKTYPE_2G = 2;

        public static final int NETWORKTYPE_3G = 3;


        public static int getNetworkType(Context context){

            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();

            if(ni != null &&ni.isConnected()){
                String type = ni.getTypeName();
                if("WIFI".equals(type)){
                    return NETWORKTYPE_WIFI;
                }
                if("MOBILE".equals(type)){
                    if(isFastMobileNetwork(context)){
                        return NETWORKTYPE_3G;
                    }
                    else{
                        return NETWORKTYPE_2G;
                    }
                }

            }

            return NETWORKTYPE_INVALID;
        }

        public static boolean isFastMobileNetwork(Context context){
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            switch (telephonyManager.getNetworkType()) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return true; // ~ 400-7000 kbps
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    return true; // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    return true; // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return true; // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return false; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return true; // ~ 10+ Mbps
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    return false;
                default:
                    return false;
            }
        }

}
