package mb.petz.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import mb.petz.R;
import mb.petz.databinding.ActivityCardBinding;
import mb.petz.model.CardModel;
import mb.petz.util.Utils;
import mb.petz.viewmodel.CardViewModel;

public class CardActivity extends AppCompatActivity {
    private ActivityCardBinding binding;
    private CardViewModel       cardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card);
        cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            CardModel cardModel = (CardModel) intent.getExtras().getSerializable("key_card");

            binding.setObjCardModel(cardModel);

            Utils.loadImage(binding.imgImage1, cardModel.getImg(), Utils.getProgressDrawable(
                    binding.imgImage1.getContext()
            ));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        back();
    }

    public void back(){
        Intent intent = new Intent(CardActivity.this, CardActivityList.class);
        ActivityOptionsCompat actOpt = ActivityOptionsCompat.makeCustomAnimation(
                CardActivity.this, R.anim.sliding_left, R.anim.fade_out);
        ActivityCompat.startActivity(CardActivity.this, intent, actOpt.toBundle());
    }
}