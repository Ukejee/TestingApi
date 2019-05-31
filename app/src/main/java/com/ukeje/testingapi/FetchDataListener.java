package com.ukeje.testingapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author .: Ukeje Emeka
 * @email ..: ukejee3@gmail.com
 * @created : 5/28/19
 */
public interface FetchDataListener {

    void onFetchComplete(JSONArray data);

    void onFetchFailure(String msg);

    void onFetchStart();
}
