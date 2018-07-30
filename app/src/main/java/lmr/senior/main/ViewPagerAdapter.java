package lmr.senior.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

 //界面列表
  private List<View> views;
  private Activity activity;
  private static final String sharedpreferences_name="first_pref";

  public ViewPagerAdapter(List<View> views,Activity activity){

    this.views=views;
    this.activity=activity;
  }

  //销毁a位置的界面
  @Override
  public void destroyItem(View v,int a,Object b){
    ((ViewPager)v).removeView(views.get(a));
  }

  // 获得当前界面数
  @Override
  public int getCount() {

    if(views !=null){
      return views.size();
    }
    return 0;
  }

  // 初始化arg1位置的界面
  @Override
  public Object instantiateItem(View arg0,int arg1){
    ((ViewPager)arg0).addView(views.get(arg1),0);
    if(arg1 == views.size()-1){
      Button mStartWeiboImageButton=(Button)arg0
          .findViewById(R.id.iv_start_weibo);
      mStartWeiboImageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         //设置已经引导
          setGuided();
          goHome();
        }
      });
    }
    return views.get(arg1);
  }

  private void goHome() {
    // 跳转
    Intent intent = new Intent(activity, MainActivity.class);
    activity.startActivity(intent);
    activity.finish();
  }

  /*设置已经引导过了，下次启动不用再次引导
   * **/
  private void setGuided() {
    SharedPreferences preferences = activity.getSharedPreferences(
        sharedpreferences_name, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    // 存入数据
    editor.putBoolean("isFirstIn", false);
    // 提交修改
    editor.commit();

  }

  // 判断是否由对象生成界面
  @Override
  public boolean isViewFromObject(View view, Object o) {
    return (view == o);
  }
}
