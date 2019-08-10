package com.example.enterc.practiceinter.Model.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckConnect {
    Context context;

    public CheckConnect(Context context) {
        this.context = context;
    }

    public boolean checkInternetConnection() {

        ConnectivityManager connManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
           // Toast.makeText(context, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isConnected()) {
           // Toast.makeText(context, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!networkInfo.isAvailable()) {
            //Toast.makeText(context, "Network not available", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
