package ie.iamshanedoyle.funtransistions;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Activity performs a simple iteration animation using a ValueAnimator.
 */
public class IterationAnimationActivity extends Activity {

    private String[] mNumbers;
    private int mPosition = 0;
    private TextView mTextViewWord;

    private static final int DURATION_PER_NUMBER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteration_animation);

        mNumbers = getResources().getStringArray(R.array.numbers);

        mTextViewWord = (TextView) findViewById(R.id.text_view_word);

        ValueAnimator positionAnim = ObjectAnimator.ofInt(this, "wordPosition", 0, 20);
        positionAnim.setDuration(DURATION_PER_NUMBER * 20);
        positionAnim.setRepeatCount(ValueAnimator.INFINITE);
        positionAnim.setRepeatMode(ValueAnimator.RESTART);
        positionAnim.start();
    }

    public void setWordPosition(int position) {
        mPosition = position;
        mTextViewWord.setText(mNumbers[mPosition]);
    }

    public int getWordPosition() {
        return mPosition;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.iteration_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_transitions) {
            startActivity(new Intent(this, TransitionsAnimationsActivity.class));

            return true;
        } else if (id == R.id.action_fade) {
            startActivity(new Intent(this, FadeAnimationActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
