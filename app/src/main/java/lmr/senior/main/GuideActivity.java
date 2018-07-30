package lmr.senior.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{

  private ViewPager vp;
  private ViewPagerAdapter vpAdapter;
  private List<View> views;
  // 底部小点图片
  private ImageView[] dots;
  // 记录当前选中位置
  private int currentIndex;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_guide);
    //初始化页面
    initViews();
    //初始化底部小点
    initDots();
  }

  private void initViews() {
    LayoutInflater inflater = LayoutInflater.from(this);

    views = new ArrayList<View>();
    //初始化引导图片列表
    views.add(inflater.inflate(R.layout.layout1, null));
    views.add(inflater.inflate(R.layout.layout2, null));
    views.add(inflater.inflate(R.layout.layout3, null));
    views.add(inflater.inflate(R.layout.layout4, null));
    //初始化Adapter
    vpAdapter = new ViewPagerAdapter(views,this);
    vp=(ViewPager)findViewById(R.id.g_viewpager);
    vp.setAdapter(vpAdapter);
    //绑定回调
    vp.setOnPageChangeListener(this);
  }

  private  void initDots(){
    LinearLayout ll=(LinearLayout)findViewById(R.id.ll);
    dots=new ImageView[views.size()];
    //循环取得小点图片
    for(int i=0;i<views.size();i++){
      dots[i]=(ImageView)ll.getChildAt(i);
      dots[i].setEnabled(true);//都设为灰色
    }
    currentIndex=0;
    dots[currentIndex].setEnabled(false);//设置为白色，即选中状态
  }

  private void setCurrentDot(int position){
    if(position<0 || position>views.size()-1
        || currentIndex==position){
      return;
    }
    dots[position].setEnabled(false);
    dots[currentIndex].setEnabled(true);

    currentIndex=position;
  }

  @Override
  public void onPageScrolled(int i, float v, int i1) {

  }
  // 当新的页面被选中时调用
  @Override
  public void onPageSelected(int i) {
    // 设置底部小点选中状态
    setCurrentDot(i);

  }

  @Override
  public void onPageScrollStateChanged(int i) {

  }
}
