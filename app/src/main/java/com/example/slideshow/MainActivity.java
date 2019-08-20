package com.example.slideshow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bigkoo.convenientbanner.ConvenientBanner;

public class MainActivity extends AppCompatActivity {

    /*
    * create by 智辉
    * time:2019/8/20
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSlideShow();//轮播图图片初始化
    }

    /**轮播图片
     * */
    private void initSlideShow(){
        SlideShowPicture slideShowPicture2=new SlideShowPicture(this);
        ConvenientBanner convenientBanner=(ConvenientBanner)findViewById(R.id.convenientBanner);
        //哪一个页面需要轮播图就传入对应的数值，可以应用在不同的页面中，这里我就选择1
        slideShowPicture2.slideShow(convenientBanner);
    }
}
