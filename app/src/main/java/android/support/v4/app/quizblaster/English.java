package android.support.v4.app.quizblaster;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class English extends AppCompatActivity {

    public ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);
        img1= (ImageView)findViewById(R.id.pt);
        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(English.this,ExamPageC.class);
                startActivity(i);
            }
        });


    }
}
