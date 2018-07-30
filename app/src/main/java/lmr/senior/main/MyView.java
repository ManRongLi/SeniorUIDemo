package lmr.senior.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {

  private static final String TAG="MyView";
  private Paint mPaint;
  private Bitmap mBitmap;
  private Path mPath;
  private RectF mOval;

  public MyView(Context context, AttributeSet attrs){
    super(context,attrs);

    mPaint=new Paint();

    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(6);
    mPaint.setColor(Color.BLUE);

    mBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.img0);

    mOval=new RectF(805,805,195,195);

    initPath();
  }

  private void initPath(){

    mPath=new Path();
    int x1=500,y1=5;
    int x2=900,y2=900;
    int x3=100,y3=900;

    mPath.moveTo(x1,y1);
    mPath.lineTo(x2,y2);
    mPath.lineTo(x3,y3);
    mPath.lineTo(x1,y1);

  }

  @Override
  protected void onMeasure(int widthMea,int heightMea){

    int mode=MeasureSpec.getMode(widthMea);
    int size=MeasureSpec.getSize(widthMea);
    Log.d(TAG,"onMeasure Mode "+(mode>>30)+" "+size);
    super.onMeasure(widthMea,heightMea);
    Log.d(TAG,"onMeasure");
  }

  @Override
  public void layout(int l,int t,int r,int b){

    super.layout(l,t,r,b);
    Log.d(TAG,"layout");

  }

  @Override
  protected void onDraw(Canvas canvas){

    Log.d(TAG,"onDraw");
    //剪裁
    canvas.clipPath(mPath);
    //画直线
    int startX=5,startY=100;
    int stopX=195,stopY=100;
    canvas.drawLine(startX,startY,stopX,stopY,mPaint);
    //画圆
    int cx=100,cy=100;
    int radius=80;
    canvas.drawCircle(cx,cy,radius,mPaint);
    //画空心圆
    canvas.drawCircle(cx,cy,2,mPaint);
    //画图片
    canvas.drawBitmap(mBitmap,500,5,mPaint);
    //画三角形
    canvas.drawPath(mPath,mPaint);
    //画扇形
    int startAngle = -90;
    int sweepAngle=45;
    boolean useCenter=false;//是否画出扇形的两边
    canvas.drawArc(mOval,startAngle,sweepAngle,useCenter,mPaint);

  }
}
