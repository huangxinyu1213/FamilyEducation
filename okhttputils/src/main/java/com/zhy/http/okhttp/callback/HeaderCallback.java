package com.zhy.http.okhttp.callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by mxh on 2017/6/26.
 */

public class HeaderCallback extends Callback{
     private boolean hasFile = false;

    @Override
    public Object parseNetworkResponse(Response response, int id) throws Exception {
         hasFile = response.isSuccessful();
        return null;
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(Object response, int id) {

    }
}
