package ie.iamshanedoyle.funtransistions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Activity performs a simple slide animation. Similar to Vine.
 */
public class AnimatedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);

        setContentView(R.layout.activity_animated_activty);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animated_activty, menu);
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
        } else if (id == R.id.action_fade) {
            startActivity(new Intent(this, FadeAnimationActivity.class));

            return true;
        } else if (id == R.id.action_background) {
            startActivity(new Intent(this, MovingBackgroundActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();


        overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
    }
}
