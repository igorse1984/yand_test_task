package ru.igorsharov.yand_test;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetJSON {

    private static final String LOG_TAG = "GetJSON";
    private static final String API_KEY = "trnsl.1.1.20170325T140225Z.5fb87348c9fc5b7a.56c642ead6f88545a2539c4e67fad415f8d7d87b";

    // вспомогательный метод, HTTP провайдер
    private String getJSONString(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        String result = response.body().string();

        return result;
    }

    // основной метод класса, делает запрос на сервер для перевода введеного текста
    public ArrayList<String> fetchItems(String str) {
        ArrayList<String> al = new ArrayList<>();
        try {
            // компоновка url для запроса
            String url = Uri.parse("https://translate.yandex.net/api/v1.5/tr.json/translate?")
                    .buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    .appendQueryParameter("text", str)
                    .appendQueryParameter("lang", "en-ru")
                    .build().toString();

            String translate = jsonParser(getJSONString(url));


            al.add(translate);

            System.out.println("JSON answer: " + getJSONString(url));


        } catch (IOException ioe) {
            Log.e(LOG_TAG, "ОШИБКА ЗАГРУЗКИ ДАННЫХ", ioe);
        } catch (JSONException joe) {
            Log.e(LOG_TAG, "ОШИБКА ПОЛУЧЕНИЯ JSON", joe);
        }
        return al;

    }

    // раскладывает полученный JSON строковый ответ
    private String jsonParser(String jsonString) throws JSONException {
        JSONObject jsonBody = new JSONObject(jsonString);
//        String translate = jsonBody.getString("text");

        return translate;
    }

}
