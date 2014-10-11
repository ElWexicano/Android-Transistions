package ie.iamshanedoyle.funtransistions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Activity performs a simple fade animation using a ViewPropertyAnimator.
 */
public class FadeAnimationActivity extends Activity implements Runnable {

    private TextView mTextViewTitle;
    private boolean mIsFadingOut;
    
    private static final int DURATION = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_animation);

        mTextViewTitle = (TextView) findViewById(R.id.text_view_title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTextViewTitle.removeCallbacks(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_transitions) {
            startActivity(new Intent(this, TransitionsAnimationsActivity.class));

            return true;
        } else if (id == R.id.action_iteration) {
            startActivity(new Intent(this, IterationAnimationActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {
        if (mIsFadingOut) {
            mTextViewTitle.animate().alpha(0).setDuration(DURATION);
            mTextViewTitle.setText(R.string.slan);
            mTextViewTitle.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
            mTextViewTitle.setAllCaps(false);
        }
        else {
            mTextViewTitle.animate().alpha(1).setDuration(DURATION);
            mTextViewTitle.setText(R.string.dia_dhuit);
            mTextViewTitle.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            mTextViewTitle.setAllCaps(true);
        }

        mIsFadingOut =! mIsFadingOut;

        mTextViewTitle.postDelayed(this, DURATION);
    }
}
