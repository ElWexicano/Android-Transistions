package ie.iamshanedoyle.funtransistions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class MovingBackgroundActivity extends Activity {

    private static final int RightToLeft = 1;
    private static final int LeftToRight = 2;
    private static final int DURATION = 5000;

    private ValueAnimator mCurrentAnimator;
    private final Matrix mMatrix = new Matrix();
    private ImageView mImageView;
    private float mScaleFactor;
    private int mDirection = RightToLeft;
    private RectF mDisplayRect = new RectF();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving_background);

        mImageView = (ImageView) findViewById(R.id.imageView);


        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mScaleFactor = (float)  mImageView.getHeight() / (float) mImageView.getDrawable().getIntrinsicHeight();
                mMatrix.postScale(mScaleFactor, mScaleFactor);
                mImageView.setImageMatrix(mMatrix);
                animate();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.moving_background, menu);
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
        } else if (id == R.id.action_animated) {
            startActivity(new Intent(this, AnimatedActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void animate() {
        updateDisplayRect();
        if(mDirection == RightToLeft) {
            animate(mDisplayRect.left, mDisplayRect.left - (mDisplayRect.right - mImageView.getWidth()));
        } else {
            animate(mDisplayRect.left, 0.0f);
        }
    }

    private void animate(float from, float to) {
        mCurrentAnimator = ValueAnimator.ofFloat(from, to);
        mCurrentAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();

                mMatrix.reset();
                mMatrix.postScale(mScaleFactor, mScaleFactor);
                mMatrix.postTranslate(value, 0);

                mImageView.setImageMatrix(mMatrix);

            }
        });
        mCurrentAnimator.setDuration(DURATION);
        mCurrentAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(mDirection == RightToLeft) {
                    mDirection = LeftToRight;
                } else {
                    mDirection = RightToLeft;
                }

                animate();
            }
        });
        mCurrentAnimator.start();
    }

    private void updateDisplayRect() {
        mDisplayRect.set(0, 0, mImageView.getDrawable().getIntrinsicWidth(),
                mImageView.getDrawable().getIntrinsicHeight());
        mMatrix.mapRect(mDisplayRect);
    }
}
