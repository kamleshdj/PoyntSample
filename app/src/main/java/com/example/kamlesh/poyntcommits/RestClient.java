package com.example.kamlesh.poyntcommits;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.HttpUrl;

/*
Make this Singleton instance ( or use Application ) .. the httpclient is similar to concept in C# and should reuse the client
Can use Dagger for injecting dependencies..
*/
public class RestClient {

    private static RestClient instance = null;
    private OkHttpClient client;

    private RestClient() {
        if(instance != null) {
            throw new RuntimeException("Use getInstance to get a Instance of this class !");
        }

        client = new OkHttpClient();
    }

    public static RestClient getInstance() {
        if(instance == null) {
            instance = new RestClient();
        }

        return instance;
    }

    public String get(String endPoint, Map<String, String> params) throws Exception {
        HttpUrl.Builder httpBuilder = buildUrl(endPoint, params);

        Request request = new Request.Builder().url(httpBuilder.build()).build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public String post() throws Exception {
        // Not yet implemented exception..
        throw new NoSuchMethodException();
    }

    protected HttpUrl.Builder buildUrl(String endPoint, Map<String, String> params) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(endPoint).newBuilder();
        if(params != null) {
            for(Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }

        return httpBuilder;
    }
}
