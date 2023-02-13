package mb.petz.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import mb.petz.model.CardModel;
import mb.petz.repository.CardRepository;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

@HiltViewModel
public class CardViewModelList extends ViewModel {
    private final CardRepository            cardRepository;
    private List<CardModel>                 cardList;
    private CompositeSubscription           compositeSubscription;
    public MutableLiveData<String>          titleLiveData;
    public MutableLiveData<List<CardModel>> cardListLiveData;
    public MutableLiveData<String>          cardErrorLiveData;

    @Inject
    public CardViewModelList(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void setTitle() {
        titleLiveData = new MutableLiveData<>();
        titleLiveData.postValue("Lista de Cartões");
    }
    public MutableLiveData<String> getTitle() { return titleLiveData; }

    public void loadCardBySet(String set) {
        compositeSubscription = new CompositeSubscription();
        cardList              = new ArrayList<>();
        cardListLiveData      = new MutableLiveData<>();
        cardErrorLiveData     = new MutableLiveData<>();

        compositeSubscription
            .add(cardRepository.getCardsBySet(set)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<CardModel>>() {
                @Override public void onCompleted() {}

                @Override public void onError(Throwable e) {
                    cardErrorLiveData.postValue(e.getMessage());
                }

                @Override public void onNext(List<CardModel> cardModelsX) {
                    cardList.addAll(cardModelsX);
                    cardListLiveData.postValue(cardList);
                }
            })
        );
    }
    public MutableLiveData<List<CardModel>> getCardBySet(){ return cardListLiveData; }
}