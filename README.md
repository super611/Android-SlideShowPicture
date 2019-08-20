# Android-SlideShowPicture
*ConvenientBanner实现的轮播图的效果，我也学习一下！*
先上一张效果图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190820161638221.gif)

想必大家都看到过电商之类的App都会用到这样的样式吧当然我这里比较简化，但是也同样实现了图片轮播的效果，别的就不说了马上开工~
 **1.添加依赖包**
```
   //轮播图
    implementation 'com.bigkoo:convenientbanner:2.0.5'
```

 **2.轮播图的效果封装在SlideShowPicture.java文件中，想在哪里引用都可以**
```java
package com.example.slideshow;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SlideShowPicture implements AdapterView.OnItemClickListener, OnItemClickListener {

    Context context=null;
    public SlideShowPicture(Context context){
        this.context=context;
    }

    public void slideShow(ConvenientBanner convenientBanner){
        ArrayList<Integer> localImages = new ArrayList<Integer>();

        //获取本地的图片
        for (int position = 1; position <=4; position++) {
            localImages.add(getResId("icon_" + position, R.drawable.class));
        }

        //开始自动翻页
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        },localImages)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(3000)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置点击监听事件
                .setOnItemClickListener(this)
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);

        //设置翻页的效果，不需要翻页效果可用不设
        //setPageTransformer(Transformer.DefaultTransformer);
        //集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。

    }

    //图片的点击事件
    @Override
    public void onItemClick(int position) {
        Toast.makeText(context, "点击图片" + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //为了方便改写，来实现复杂布局的切换
    private class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，不一定是Image，任何控件都可以进行翻页
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}

```

 3.**Mainactivity.java里来引用轮播样式**
```java
/**轮播图片
     * */
    private void initSlideShow(){
        SlideShowPicture slideShowPicture2=new SlideShowPicture(this);
        ConvenientBanner convenientBanner=(ConvenientBanner)findViewById(R.id.convenientBanner);
        slideShowPicture2.slideShow(convenientBanner);
    }
```
 4.**activity.xml布局文件**
 

```java
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#2000"
    tools:context=".MainActivity">

    <com.bigkoo.convenientbanner.ConvenientBanner xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/convenientBanner"
        android:layout_width="0dp"
        android:layout_height="200dp"
        app:canLoop="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.0" />

</android.support.constraint.ConstraintLayout>
```
