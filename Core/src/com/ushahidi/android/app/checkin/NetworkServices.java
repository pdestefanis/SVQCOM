
package com.ushahidi.android.app.checkin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import android.text.TextUtils;
import android.util.Log;

import com.ushahidi.android.app.Preferences;
import com.ushahidi.android.app.net.MainHttpClient;

/**
 * Created by IntelliJ IDEA. User: Ahmed Date: 2/10/11 Time: 4:34 PM To change
 * this template use File | Settings | File Templates.
 */
public class NetworkServices {

    public static ArrayList<String> fileName = new ArrayList<String>();

    private static MultipartEntity entity = new MultipartEntity();

    public static String postToOnline(String IMEI, String domainName, String checkinDetails,
            ArrayList<String> filename, String firstname, String lastname, String email, double latitude,
            double longitude) {
        HashMap<String, String> myParams = new HashMap<String, String>();

        // Build the HTTP response
        StringBuilder urlBuilder = new StringBuilder(domainName);
        urlBuilder.append("/api");
        myParams.put("task", "checkin");
        myParams.put("action", "ci");
        myParams.put("mobileid", IMEI);
        myParams.put("lat", String.valueOf(latitude));
        myParams.put("lon", String.valueOf(longitude));
        myParams.put("message", checkinDetails);
        myParams.put("firstname", firstname);
        myParams.put("lastname", lastname);
        myParams.put("email", email);

        // Specify the file name
        for (int i = 0; i < filename.size(); i++) {
        	myParams.put("filename" + i, filename.get(i));
		}

        try {
            return PostFileUpload(urlBuilder.toString(), myParams);
        } catch (IOException e) {

            return null;
        }
    }

    public static String PostFileUpload(String URL, HashMap<String, String> params)
            throws IOException {
        Log.i("NeworkServices", "Posting Checkins online");

        entity = new MultipartEntity();

        if (params != null) {
            Log.i("NeworkServices", "UploadFile " + params.size());
            entity.addPart("task", new StringBody(params.get("task")));
            entity.addPart("action", new StringBody(params.get("action")));
            entity.addPart("mobileid", new StringBody(params.get("mobileid")));
            entity.addPart("lat", new StringBody(params.get("lat")));
            entity.addPart("lon", new StringBody(params.get("lon")));
            entity.addPart("message", new StringBody(params.get("message")));
            entity.addPart("firstname", new StringBody(params.get("firstname")));
            entity.addPart("lastname", new StringBody(params.get("lastname")));
            entity.addPart("email", new StringBody(params.get("email")));

            if (!TextUtils.isEmpty(params.get("filename")) || !(params.get("filename").equals(""))) {
                    Log.i("NeworkServices", "Posting file online");
                    entity.addPart("photo", new FileBody(new File(params.get("filename"))));
            }

            return MainHttpClient.SendMultiPartData(URL, entity);
        }
        return null;
    }

    public static String getCheckins(String URL, String mobileId, String checkinId) {
        final HttpResponse response;
        StringBuilder fullUrl = new StringBuilder(URL);
        fullUrl.append("/api");
        fullUrl.append("?task=checkin");
        fullUrl.append("&action=get_ci");
        fullUrl.append("&sort=desc");
        fullUrl.append("&sqllimit=" + Preferences.totalReports);
        if (mobileId != null)
            fullUrl.append("&mobileid=" + mobileId);
        if (checkinId != null)
            fullUrl.append("&id=" + checkinId);

        try {
            response = MainHttpClient.GetURL(fullUrl.toString());
            if (response == null) {
                return null;
            }
            return MainHttpClient.GetText(response);

        } catch (MalformedURLException e) {

            return null;
        } catch (IOException e) {

            return null;
        }
    }

    public static String GetText(InputStream in) {
        String text = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 1024);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            text = sb.toString();
        } catch (final Exception ex) {
        } finally {
            try {
                in.close();
            } catch (final Exception ex) {
            }
        }
        return text;
    }
}
