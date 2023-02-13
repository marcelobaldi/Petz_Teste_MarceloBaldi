package mb.petz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import dagger.hilt.android.AndroidEntryPoint;
import mb.petz.view.CardActivityList;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            finish();
            Intent intent = new Intent(MainActivity.this, CardActivityList.class);
            ActivityOptionsCompat actOpt = ActivityOptionsCompat.makeCustomAnimation(
                    MainActivity.this, R.anim.fade_in, R.anim.sliding_right);
            ActivityCompat.startActivity(MainActivity.this, intent, actOpt.toBundle());

        }, 4000);

    }
}

//Observations
//- Gradle:         Avoid Update,                       Because Couldn't Play
//- Dependency:     Avoid Update,                       Because Couldn't Play
//- Model:          Used Private and SerializedName,    but Someone Prefer Public and Directly Json
//- Adapter:        Used Holder Inner Join,             but Someone Prefer Separated File Holder
//- AndroidStudio:  Upgrade Last Version,               Electric Eel | 2022.1.1 Patch 1
//* Others:         Gradle Synchronize, Clean, Rebuild, Invalidate Caches, Etc;

//Technology Applied
//- BackEnd:                Java
//- FrontEnd:               Xml
//- FrameWorks:             JetPack
//- Design Patterns:        MVVM
//- Dependency Injection:   Hilt
//- Asynchronous Tasks:     Live Data Observer, RxJava
//- Api Consumer:           Retrofit, Interceptor, Glide
//- List:                   RecyclerView, CardView, SwipeRefresh
//- Animations:             SplashScreen, SlidingActivity, ProgressBar (Code and Glide)
//* Others:                 ViewBinding, DataBinding, ConstraintLayout, Alert Dialog If Exit, Etc;

//Technology Others
//- BackEnd:                Kotlin
//- FrontEnd:               Compose
//- Design Patterns:        MVVM Clean Architecture
//- Asynchronous Tasks:     Coroutines
//- Dependency Injection:   Koin

//Best practices - I
//- Constants:              Only by Constants File  (Create Manually)
//- Strings:                Only by String File
//- Colors:                 Only by Colors File

//Best practices - II
//- Code:                   English, Indented, No Comments, No Empty Lines, Correct Nomenclatures;
//- Java:                   Lambda;
//- XML:                    Text Size (sp) and Others Size (dp);
//- Images:                 Only Used Images;
//- Imports:                Only Used Imports;
//- Manifest:               Only Used Activities;
//* Nomenclatures:          Class (PascalCase), Method (camelCase), CONSTANT_NAME, xml_name;