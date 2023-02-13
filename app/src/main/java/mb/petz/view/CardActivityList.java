package mb.petz.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import dagger.hilt.android.AndroidEntryPoint;
import mb.petz.R;
import mb.petz.adapter.CardListAdapter;
import mb.petz.databinding.ActivityCardListBinding;
import mb.petz.model.CardModel;
import mb.petz.util.Utils;
import mb.petz.viewmodel.CardViewModelList;

@AndroidEntryPoint
public class CardActivityList extends AppCompatActivity {
    private ActivityCardListBinding binding;
    private CardViewModelList       cardViewModelList;
    private CardListAdapter         cardListAdapter;
    private LinearLayoutManager     cardListLayout;
    private List<CardModel>         cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_list);
        cardViewModelList = new ViewModelProvider(this).get(CardViewModelList.class);

        binding.refreshLayout.setOnRefreshListener(() -> {
           observers();
           binding.refreshLayout.setRefreshing(false);
           Toast.makeText(getApplication(), R.string.list_updated, Toast.LENGTH_LONG).show();
        });

        cardViewModelList.setTitle();
        cardViewModelList.loadCardBySet("Hall of Fame");

        observers();
    }

    public void observers(){

        cardViewModelList.getTitle().observe(this, s -> binding.txtTitle.setText(s));

        cardViewModelList.getCardBySet().observe(this, cardModels -> {

            cardList = new ArrayList<>();
            cardList.addAll(cardModels);

            cardListAdapter = new CardListAdapter();
            cardListAdapter.getList(cardList);
            binding.recycleListCards.setAdapter(cardListAdapter);

            cardListLayout = new LinearLayoutManager(CardActivityList.this);
            binding.recycleListCards.setLayoutManager(cardListLayout);

            binding.pbProgressbar.setVisibility(View.GONE);
            binding.txtLoading.setVisibility(View.GONE);
        });
    }

    @Override
    public void onBackPressed() {
        Utils.messageExit(CardActivityList.this);
    }
}