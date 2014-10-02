package ie.iamshanedoyle.funtransistions;

import android.app.Activity;
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


public class TransitionsActivity extends Activity {

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transistions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
