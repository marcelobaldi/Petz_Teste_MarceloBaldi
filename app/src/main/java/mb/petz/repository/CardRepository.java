package mb.petz.repository;

import java.util.List;
import javax.inject.Inject;
import mb.petz.api.CardApi;
import mb.petz.model.CardModel;
import rx.Observable;

public class CardRepository {
    private final CardApi cardApi;

    @Inject
    public CardRepository(CardApi cardApi) {
        this.cardApi = cardApi;
    }

    public Observable<List<CardModel>> getCardsBySet(String set) {
        return cardApi.getCardsBySet(set);
    }
}