package ru.igorsharov.yand_test;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetJSON {

    private static final String LOG_TAG = "GetJSON";
    private static final String API_KEY = "trnsl.1.1.20170325T140225Z.5fb87348c9fc5b7a.56c642ead6f88545a2539c4e67fad415f8d7d87b";

    private String getJSONString(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        String result = response.body().string();

        return result;
    }

    public void fetchItems() {
        try {
            String url = Uri.parse("https://translate.yandex.net/api/v1.5/tr.json/translate?")
                    .buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    .appendQueryParameter("text", "hello world")
                    .appendQueryParameter("lang", "en-ru")
                    .build().toString();
            String jsonString = getJSONString(url);
//            JSONObject jsonBody = new JSONObject(jsonString);
            System.out.println("JSON answer: " + jsonString);
        } catch (IOException ioe) {
            Log.e(LOG_TAG, "ОШИБКА ЗАГРУЗКИ ДАННЫХ", ioe);
        }
//        catch (JSONException joe) {
//        }
    }
}
