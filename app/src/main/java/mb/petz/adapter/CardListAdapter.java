package mb.petz.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import mb.petz.R;
import mb.petz.databinding.AdapterCardListBinding;
import mb.petz.model.CardModel;
import mb.petz.util.Utils;
import mb.petz.view.CardActivity;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardListHolder> {
    private Context         context;
    private List<CardModel> cardList;

    @SuppressLint("NotifyDataSetChanged")
    public void getList(List<CardModel> list){
        cardList = new ArrayList<>();
        cardList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    @NonNull @Override
    public CardListHolder onCreateViewHolder(@NonNull ViewGroup p, int viewType){
        LayoutInflater inflate     = LayoutInflater.from(p.getContext());
        AdapterCardListBinding adp = AdapterCardListBinding.inflate(inflate, p, false);
        context = p.getContext();
        return new CardListHolder(adp);
    }

    @Override
    public void onBindViewHolder(@NonNull CardListHolder h, @SuppressLint("RecyclerView") int p) {
        CardModel cardModel = cardList.get(p);
        h.bindingAdapter.nameAdpt.setText(cardModel.getName());

        Utils.loadImage(h.bindingAdapter.imageAdpt, cardModel.getImg(),
                Utils.getProgressDrawable(h.bindingAdapter.imageAdpt.getContext()));

        h.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, CardActivity.class);
            intent.putExtra("key_card", cardList.get(p));
            ActivityOptionsCompat actOpt = ActivityOptionsCompat.makeCustomAnimation(
                    context, R.anim.fade_in, R.anim.sliding_right);
            ActivityCompat.startActivity(context, intent, actOpt.toBundle());
        });
    }

    public static class CardListHolder extends RecyclerView.ViewHolder{
        public AdapterCardListBinding bindingAdapter;

        public CardListHolder(AdapterCardListBinding adpBinding) {
            super(adpBinding.getRoot());
            this.bindingAdapter = adpBinding;
        }
    }
}