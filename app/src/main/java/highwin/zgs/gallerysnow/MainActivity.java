package highwin.zgs.gallerysnow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import highwin.zgs.gallerysnow.view.GalleryView;
import highwin.zgs.gallerysnow.view.ImageAdapter;

public class MainActivity extends Activity {


    private GalleryView gallery;
    private ImageAdapter adapter;
    private int mCurrentPosition = 0;
    private List<Photo> mPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        gallery = (GalleryView) findViewById(R.id.mygallery);
        mPhotos = new ArrayList<>();
        adapter = new ImageAdapter(this);
        adapter.createReflectedImages();
        gallery.setAdapter(adapter);
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            mPhotos.add(new Photo(i, false));
        }
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //   zoom(view, false);
                if (mCurrentPosition != position) {
                    for (int i = 0; i < mPhotos.size(); i++) {
                        if (mPhotos.get(i).isZoom()) {
                            zoom(view, false);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPosition = position;
                Toast.makeText(MainActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
                if (!mPhotos.get(position).isZoom()) {
                    zoom(view, true);
                    mPhotos.get(position).setZoom(true);
                } else {
                    zoom(view, false);
                    mPhotos.get(position).setZoom(false);
                }
              /*  if (!mCount[position]) {
                    zoom(view, true);
                    mCount[position] = true;
                } else {
                    zoom(view, false);
                    mCount[position] = false;
                }*/
            }
        });
    }

    /**
     * 放大和缩小
     *
     * @param imageView
     * @param io
     */
    private void zoom(View imageView, boolean io) {
        if (imageView instanceof ImageView) {
            ImageView iv = (ImageView) imageView;
            ScaleAnimation scaleAnimation = new ScaleAnimation(io ? 1f : 2f, io ? 2f : 1f, io ? 1f : 2f, io ? 2f : 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(io ? 500 : 500);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            iv.startAnimation(scaleAnimation);
        }

    }
}
