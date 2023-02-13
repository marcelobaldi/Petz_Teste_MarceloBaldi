package mb.petz.api;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @Override public Response intercept(Chain chain) throws IOException {
        Request.Builder currentRequest = chain.request().newBuilder();

        currentRequest.addHeader(
                "X-RapidAPI-Key", "4c038f69c0msh9b5ec6794415707p107b6cjsn31ff3ecfc7cf"
        ).addHeader(
                "X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com"
        );

        return chain.proceed( currentRequest.build() );
    }
}