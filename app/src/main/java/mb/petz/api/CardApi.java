package mb.petz.api;

import java.util.List;
import mb.petz.model.CardModel;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface CardApi {
    @GET("/cards/sets/{set}")
    Observable<List<CardModel>> getCardsBySet(@Path("set") String set);
}