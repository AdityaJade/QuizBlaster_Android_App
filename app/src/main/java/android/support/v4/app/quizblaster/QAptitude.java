package android.support.v4.app.quizblaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class QAptitude extends AppCompatActivity {

    public ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qaptitude);

        img1=findViewById(R.id.pt);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QAptitude.this,ExamPageA.class);
                startActivity(i);
            }
        });
    }
}
