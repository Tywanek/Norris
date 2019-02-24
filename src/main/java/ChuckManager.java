import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


public class ChuckManager {

    public static void joke() {

        String rawResponse = null;
        try {
            rawResponse = getResponse("https://api.chucknorris.io/jokes/search?query=chees");
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<NorrisJoke> list = getJokeList(rawResponse);
        System.out.println(list.get(0).getValue());
        System.out.println("Size: " + list.size());
        System.out.println(maxSize(list));
        System.out.println(minSize(list));
    }

    private static String maxSize(List<NorrisJoke> list) {
        String result = "";
        for (NorrisJoke joke : list) {
            if (joke.getValue().length() > result.length()) {
                result = joke.getValue();
            }
        }
        return result;
    }

    private static String minSize(List<NorrisJoke> list) {
        String result = list.get(0).getValue();
        for (NorrisJoke joke : list) {
            if (joke.getValue().length() < result.length()) {
                result = joke.getValue();
            }
        }
        return result;
    }

    private static String getResponse(String urlQueryString) throws Exception {
        URL url = new URL(urlQueryString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/4.76");
        connection.connect();
        InputStream inStream = connection.getInputStream();
        return new Scanner(inStream, "UTF-8").useDelimiter("\\Z").next();
    }

    private static String getJokeOnly(String rawJSON) {
        Gson gson = new Gson();
        NorrisJoke norrisJoke = gson.fromJson(rawJSON, NorrisJoke.class);
        return norrisJoke.getValue();
    }

    private static List<NorrisJoke> getJokeList(String rawJSON) {
        Gson gson = new Gson();
        NorrisJokeList norrisJokeList = gson.fromJson(rawJSON, NorrisJokeList.class);
        return norrisJokeList.getResult();
    }

}
