package lmr.senior.main;

import android.app.Activity;
import android.os.Bundle;

public class ViewGroupActivity extends Activity {

  //private Context mContext;
  private MyViewGroup myViewGroupContainer;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_group);
    //myViewGroupContainer=(MyViewGroup)findViewById(R.id.customViewGroup);
    //setContentView(new MyViewGroup(this));
  }

}
