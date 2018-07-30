package lmr.senior.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {

  public MyViewGroup(Context context){

    this(context,null);
  }
  public MyViewGroup(Context context,AttributeSet attrs){

    super(context,attrs);
  }
  public MyViewGroup(Context context,AttributeSet attrs,int defStyleAttr){

    super(context,attrs,defStyleAttr);
  }

  @Override
  protected void onLayout(boolean arg0,int arg1,int arg2,int arg3,int arg4){

    int cCount=getChildCount();
    int cWidth=0;
    int cHeight=0;
    MarginLayoutParams cParams=null;

    for(int i=0;i<cCount;i++) {
      View childView = getChildAt(i);
      cWidth = childView.getMeasuredWidth();
      cHeight = childView.getMeasuredHeight();
      cParams = (MarginLayoutParams) childView.getLayoutParams();

      int cl=0,ct=0,cr=0,cb=0;
      switch (i){
        case 0:
          cl=cParams.leftMargin;
          ct=cParams.topMargin;
          break;
        case 1:
          cl=getWidth()-cWidth-cParams.leftMargin-cParams.rightMargin;
          ct=cParams.topMargin;
          break;
        case 2:
          cl=cParams.leftMargin;
          ct=getHeight()-cHeight-cParams.bottomMargin;
          break;
        case 3:
          cl=getWidth()-cWidth-cParams.leftMargin-cParams.rightMargin;
          ct=getHeight()-cHeight-cParams.bottomMargin;
          break;
      }
      cr=cl+cWidth;
      cb=cHeight+ct;
      childView.layout(cl,ct,cr,cb);
    }

  }

  @Override
  public ViewGroup.LayoutParams  generateLayoutParams(AttributeSet attrs){

    return new ViewGroup.MarginLayoutParams(getContext(),attrs);
  }

  @Override
  protected void onMeasure(int widthMea,int heightMea){

    int widthMode=MeasureSpec.getMode(widthMea);
    int heightMode=MeasureSpec.getMode(heightMea);
    int sizeWidth=MeasureSpec.getSize(widthMea);
    int sizeHeight=MeasureSpec.getSize(heightMea);

    //计算出所有的childView的宽和高
    measureChildren(widthMea,heightMea);

    /**记录如果是wrap_content设置的宽和高
     * */
    int width=0;
    int height=0;

    int cCount=getChildCount();

    int cWidth=0;
    int cHeight=0;
    MarginLayoutParams cParams=null;

    //用于计算左边两个childview的高度
    int lHeight =0;
    //用于计算右边两个childview的高度，最终高度取二者之间最大值
    int rHeight =0;
    //用于计算上边两个childview的宽度
    int tWidth=0;
    //用于计算下边两个childview的宽度,最终宽度取二者之间最大值
    int bWidth =0;

    /*根据childview计算出的宽和高以及设置的margin计算容器的宽和高，主要用于容器是wrap_content时
    * */
    for(int i=0;i<cCount;i++)
    {
      View childView=getChildAt(i);
      cWidth=childView.getMeasuredWidth();
      cHeight=childView.getMeasuredHeight();
      cParams=(MarginLayoutParams)childView.getLayoutParams();

      //上面两个childview
      if(i==0 || i==1){
        tWidth+=cWidth+cParams.leftMargin+cParams.rightMargin;
      }
      if(i==2 || i==3){
        bWidth+=cWidth+cParams.leftMargin+cParams.rightMargin;
      }
      if(i==0 || i==2){
        lHeight+=cHeight+cParams.topMargin+cParams.bottomMargin;
      }
      if(i==1 || i==3){
        rHeight+=cHeight+cParams.topMargin+cParams.bottomMargin;
      }

      width=Math.max(tWidth,bWidth);
      height=Math.max(lHeight,rHeight);
      /*如果是wrap_content，则设置为我们计算的值，否则直接设置为父容器计算的值
      * */
      setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth:width,
          (heightMode == MeasureSpec.EXACTLY) ? sizeHeight:height);
    }

  }


}
