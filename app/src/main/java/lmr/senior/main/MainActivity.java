package lmr.senior.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btn_viewpager).setOnClickListener(this);
    findViewById(R.id.btn_viewgroup).setOnClickListener(this);
    findViewById(R.id.btn_myview).setOnClickListener(this);
    findViewById(R.id.btn_oval).setOnClickListener(this);
    findViewById(R.id.btn_ring).setOnClickListener(this);
    findViewById(R.id.btn_selector).setOnClickListener(this);
    findViewById(R.id.btn_animation).setOnClickListener(this);
    findViewById(R.id.btn_layerlist).setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.btn_viewpager:
        Intent i=new Intent(MainActivity.this,ViewPagerActivity.class);
        startActivity(i);
        break;
      case R.id.btn_viewgroup:
        Intent t=new Intent(MainActivity.this,ViewGroupActivity.class);
        startActivity(t);
        break;
      case R.id.btn_myview:
        Intent r=new Intent(MainActivity.this,MyViewActivity.class);
        startActivity(r);
        break;
      case R.id.btn_oval:
        Intent tr=new Intent(MainActivity.this,OvalActivity.class);
        startActivity(tr);
        break;
      case R.id.btn_ring:
        Intent qr=new Intent(MainActivity.this,RingActivity.class);
        startActivity(qr);
        break;
      case R.id.btn_selector:
        Intent ay=new Intent(MainActivity.this,SelectorActivity.class);
        startActivity(ay);
        break;
      case R.id.btn_animation:
        Intent ar=new Intent(MainActivity.this,AnimationActivity.class);
        startActivity(ar);
        break;
      case R.id.btn_layerlist:
        Intent at=new Intent(MainActivity.this,LayerListActivity.class);
        startActivity(at);
        break;
    }
  }
}
