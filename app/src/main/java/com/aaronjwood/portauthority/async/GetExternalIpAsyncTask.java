package com.aaronjwood.portauthority.async;

import android.os.AsyncTask;

import com.aaronjwood.portauthority.response.MainAsyncResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetExternalIpAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "GetExternalIpAsyncTask";
    private static final String EXTERNAL_IP_SERVICE = "http://whatismyip.akamai.com/";
    private MainAsyncResponse delegate;

    /**
     * Constructor to set the delegate
     *
     * @param delegate Called when the external IP has been fetched
     */
    public GetExternalIpAsyncTask(MainAsyncResponse delegate) {
        this.delegate = delegate;
    }

    /**
     * Fetch the external IP address
     *
     * @param params
     * @return External IP address
     */
    @Override
    protected String doInBackground(Void... params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(EXTERNAL_IP_SERVICE);
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpget);
        }
        catch(ClientProtocolException e) {
            return "Couldn't get your external IP";
        }
        catch(IOException e) {
            return "Couldn't get your external IP";
        }

        String ip;
        HttpEntity entity = response.getEntity();

        try {
            ip = EntityUtils.toString(entity);
        }
        catch(ParseException e) {
            return "Couldn't get your external IP";
        }
        catch(IOException e) {
            return "Couldn't get your external IP";
        }

        return ip;
    }

    /**
     * Calls the delegate when the external IP has been fetched
     *
     * @param result External IP address
     */
    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
