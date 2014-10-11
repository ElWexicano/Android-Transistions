package ie.iamshanedoyle.funtransistions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Activity performs a simple transition animation using a Transition and Scene.
 */
public class TransitionsAnimationsActivity extends Activity {

    private Scene mScene1, mScene2;

    private Transition mTransition;

    private boolean mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_start_transition);

        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base_layout);

        ViewGroup startViewGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.frame_start_transition, baseLayout, false);

        ViewGroup endViewGroup = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.frame_end_transition, baseLayout, false);

        mScene1 = new Scene(baseLayout, startViewGroup);
        mScene2 = new Scene(baseLayout, endViewGroup);

        mTransition = new AutoTransition();
        mTransition.setDuration(5000);
        mTransition.setInterpolator(new AccelerateDecelerateInterpolator());

        mStart = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.transistions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_fade) {
            startActivity(new Intent(this, FadeAnimationActivity.class));

            return true;
        } else if (id == R.id.action_iteration) {
            startActivity(new Intent(this, IterationAnimationActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void performTransition(View v) {
        if(mStart) {
            TransitionManager.go(mScene2, mTransition);
            mStart = false;
        }
        else {
            TransitionManager.go(mScene1, mTransition);
            mStart = true;
        }
    }
}
