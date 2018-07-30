package lmr.senior.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends Activity implements GestureDetector.OnGestureListener,
    View.OnTouchListener{
  private GestureDetector detector;
  private ViewFlipper myViewFlipper;
  private int[] imgs={R.drawable.img0,R.drawable.img1,R.drawable.img2,
      R.drawable.img3,R.drawable.img4,R.drawable.img5};
  private int displayedChildIndex =0;

  private Button treasure_btn;
  private Button garbage_btn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_view_flipper);

    myViewFlipper=(ViewFlipper)findViewById(R.id.viewflipper);
    treasure_btn=(Button)findViewById(R.id.treasure_chest_btn);
    garbage_btn=(Button)findViewById(R.id.garbage_btn);
    myViewFlipper.setOnTouchListener(this);
    myViewFlipper.setLongClickable(true);
    // myViewFlipper.setAutoStart(true);// 设置是否自动播放，默认不自动播放
    detector=new GestureDetector(this);
    addFlipperView();
  }

  /**
   * 向FlipperView中动态添加View
   * */
  private void addFlipperView(){
    for(int i=0;i<imgs.length;i++){
      View view= LayoutInflater.from(this).inflate(R.layout.myviewfliper,null);
      TextView title=(TextView)view.findViewById(R.id.view_title);
      title.setText("头像");
      ImageView iv=(ImageView)view.findViewById(R.id.view_image);
      iv.setBackgroundResource(imgs[i]);
      myViewFlipper.addView(view);

    }
  }

  @Override
  public boolean onDown(MotionEvent motionEvent) {
    return false;
  }

  @Override
  public void onShowPress(MotionEvent motionEvent) {

  }

  @Override
  public boolean onSingleTapUp(MotionEvent motionEvent) {
    return false;
  }

  @Override
  public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
    return false;
  }

  @Override
  public void onLongPress(MotionEvent motionEvent) {

  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy) {
    // 返回当前正在显示的子视图的索引
    displayedChildIndex=myViewFlipper.getDisplayedChild();
    final ImageView iv=(ImageView)myViewFlipper.getChildAt(
        displayedChildIndex).findViewById(R.id.view_image);
    if(e2.getX()-e1.getX() >= 100  && e1.getY()-e2.getY() >= 100){
      AnimationSet outAnim=(AnimationSet) AnimationUtils.loadAnimation(ViewFlipperActivity.this,
          R.anim.collect_treasure);
      iv.startAnimation(outAnim);
      outAnim.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

          showNextView();
          AnimationSet downAnim=(AnimationSet)AnimationUtils.loadAnimation(ViewFlipperActivity.this,
              R.anim.collect_end);
          treasure_btn.setAnimation(downAnim);
          iv.clearAnimation();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
      });
    }else if(e1.getX()-e2.getX() >= 100  && e2.getY()-e1.getY() >= 100){
      AnimationSet outAnim=(AnimationSet)AnimationUtils.loadAnimation(
          ViewFlipperActivity.this,R.anim.garbage_throw);
      iv.startAnimation(outAnim);
      outAnim.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
        showNextView();
        AnimationSet leftAnim=(AnimationSet)AnimationUtils.loadAnimation(
            ViewFlipperActivity.this,R.anim.garbage_end);
        garbage_btn.setAnimation(leftAnim);
        iv.clearAnimation();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
      });
    }else if(e1.getX() - e2.getX() >= 100){
      showNextView();
    }else if (e2.getX() - e1.getX() >= 100) {
      showPreviousView();
    }
    return false;
  }

  private void showPreviousView() {
    myViewFlipper.setInAnimation(AnimationUtils.loadAnimation(ViewFlipperActivity.this,
        R.anim.slide_right_in));
    myViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(ViewFlipperActivity.this,
        R.anim.slide_right_out));
    myViewFlipper.showPrevious();
  }

  private void showNextView() {
    myViewFlipper.setInAnimation(AnimationUtils.loadAnimation(ViewFlipperActivity.this,
        R.anim.slide_left_in));
    myViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(ViewFlipperActivity.this,
        R.anim.slide_left_out));
    myViewFlipper.showNext();

  }

  //入该函数后会进入case MotionEvent.ACTION_UP这个路径，从而调用onFling函数。
  @Override
  public boolean onTouch(View view, MotionEvent motionEvent) {
    return detector.onTouchEvent(motionEvent);
  }
}
