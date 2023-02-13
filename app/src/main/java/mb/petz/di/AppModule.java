package mb.petz.di;

import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import mb.petz.api.AuthInterceptor;
import mb.petz.api.CardApi;
import mb.petz.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    public Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                                .baseUrl(Constants.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .client(callConfigurations())
                                .build();
    }

    private OkHttpClient callConfigurations() {
        AuthInterceptor authInterceptor = new AuthInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(1, TimeUnit.MINUTES);
        builder.connectTimeout(1, TimeUnit.MINUTES);
        builder.writeTimeout(1, TimeUnit.MINUTES);

        builder.addInterceptor(authInterceptor);

        return builder.build();
    }

    @Singleton
    @Provides
    public CardApi provideCardApi(Retrofit retrofit){
        return retrofit.create(CardApi.class);
    }
}