package net.ezmovil.betterflytest.rest;


import android.app.Activity;
import android.os.AsyncTask;

import net.ezmovil.betterflytest.info.infoACKS;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by juan on 19/Sep/2020.
 */

public class Rickandmortyapi extends AsyncTask<String, String, String> {

    private Activity activity;

    public Rickandmortyapi(Activity activity) {
        this.activity = activity;
    }
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                out.close();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }

        infoACKS _infoACKS = new infoACKS();
        _infoACKS.setAck(responseString);
        return null;
    }

    protected void onPostExecute(Long result) {
        return;
    }

}