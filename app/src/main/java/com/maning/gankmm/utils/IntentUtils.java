package com.maning.gankmm.utils;

import android.content.Context;
import android.content.Intent;

import com.maning.gankmm.activity.AboutActivity;
import com.maning.gankmm.activity.DayShowActivity;
import com.maning.gankmm.activity.ImagesActivity;
import com.maning.gankmm.activity.SettingActivity;
import com.maning.gankmm.activity.WebActivity;

import java.util.ArrayList;

/**
 * Created by maning on 16/3/3.
 * <p/>
 * 页面跳转
 */
public class IntentUtils {

    public static final String ImagePositionForImageShow = "PositionForImageShow";
    public static final String ImageArrayList = "BigImageArrayList";
    public static final String WebTitleFlag = "WebTitleFlag";
    public static final String WebTitle = "WebTitle";
    public static final String WebUrl = "WebUrl";
    public static final String DayDate = "DayDate";

    public static void startToImageShow(Context context, ArrayList<String> mDatas, int position) {
        Intent intent = new Intent(context.getApplicationContext(), ImagesActivity.class);
        intent.putStringArrayListExtra(ImageArrayList, mDatas);
        intent.putExtra(ImagePositionForImageShow, position);
        context.startActivity(intent);
    }

    public static void startToWebActivity(Context context, String titleFlag, String title,String url) {
        Intent intent = new Intent(context.getApplicationContext(), WebActivity.class);
        intent.putExtra(WebTitleFlag, titleFlag);
        intent.putExtra(WebTitle, title);
        intent.putExtra(WebUrl, url);
        context.startActivity(intent);
    }

    public static void startAboutActivity(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), AboutActivity.class);
        context.startActivity(intent);
    }

    public static void startSettingActivity(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), SettingActivity.class);
        context.startActivity(intent);
    }

    public static void startDayShowActivity(Context context,String date) {
        Intent intent = new Intent(context.getApplicationContext(), DayShowActivity.class);
        intent.putExtra(DayDate,date);
        context.startActivity(intent);
    }

}
