package com.example.seanmedlin.bakingtime.utilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * These utilities will be used to communicate with the network
 */
public class NetworkUtils {

    /**
     * Builds the URL used to query.
     *
     * @param urlStr the URL in String format
     * @return the url in URL format
     */
    public static URL buildUrl(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
