package com.maning.gankmm.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.maning.gankmm.R;
import com.maning.gankmm.skin.SkinManager;
import com.maning.gankmm.ui.adapter.ImagesAdapter;
import com.maning.gankmm.ui.base.BaseActivity;
import com.maning.gankmm.ui.iView.IImageView;
import com.maning.gankmm.ui.presenter.impl.ImagePresenterImpl;
import com.maning.gankmm.utils.IntentUtils;
import com.maning.gankmm.utils.MySnackbar;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class ImagesActivity extends BaseActivity implements IImageView {

    private static final String TAG = ImagesActivity.class.getSimpleName();
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tv_showNum)
    TextView tvShowNum;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab_speed_dial)
    FabSpeedDial fabSpeedDial;

    private Context mContext;

    private ArrayList<String> mDatas = new ArrayList<>();
    private int startIndex;
    private ImagesAdapter imageAdapter;

    private ImagePresenterImpl imagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        ButterKnife.bind(this);
        mContext = this;

        initMyToolBar();

        imagePresenter = new ImagePresenterImpl(this, this);

        initIntent();

        tvShowNum.setText(String.valueOf((startIndex + 1) + "/" + mDatas.size()));

        //初始化ViewPager
        initViewPager();

        initMenuListener();

    }

    private void initMenuListener() {
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_save:
                        // 先判断是否有权限。
                        if(AndPermission.hasPermission(ImagesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            // 有权限，直接do anything.
                            imagePresenter.saveImage();
                        } else {
                            // 申请权限。
                            AndPermission.with(ImagesActivity.this)
                                    .requestCode(100)
                                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    .send();
                        }

                        break;
                    case R.id.action_share:
                        int currentItem = viewPager.getCurrentItem();
                        String picUrl = mDatas.get(currentItem);
                        IntentUtils.startAppShareText(ImagesActivity.this, "GankMM图片分享", "分享图片：" + picUrl);
                        break;
                    case R.id.action_wallpaper:
                        imagePresenter.setWallpaper();
                        break;
                }
                return false;
            }
        });
    }

    private void initMyToolBar() {
        int currentSkinType = SkinManager.getCurrentSkinType(this);
        if (SkinManager.THEME_DAY == currentSkinType) {
            initToolBar(toolbar, getString(R.string.gank_page_title_girls), R.drawable.icon_arrow_back);
        } else {
            initToolBar(toolbar, getString(R.string.gank_page_title_girls), R.drawable.icon_arrow_back_night);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewPager() {
        imageAdapter = new ImagesAdapter(mContext, mDatas);
        viewPager.setAdapter(imageAdapter);
        if (startIndex > 0) {
            viewPager.setCurrentItem(startIndex);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvShowNum.setText(String.valueOf((position + 1) + "/" + mDatas.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIntent() {
        //获取传递的数据
        Intent intent = getIntent();
        mDatas = intent.getStringArrayListExtra(IntentUtils.ImageArrayList);
        startIndex = intent.getIntExtra(IntentUtils.ImagePositionForImageShow, 0);
    }


    @Override
    public void showBaseProgressDialog(String msg) {
        showProgressDialog(msg);
    }

    @Override
    public void hideBaseProgressDialog() {
        dissmissProgressDialog();
    }

    @Override
    public void showBasesProgressSuccess(String msg) {
        showProgressSuccess(msg);
    }

    @Override
    public void showBasesProgressError(String msg) {
        showProgressError(msg);
    }

    @Override
    public Bitmap getCurrentImageViewBitmap() {
        return imageAdapter.getCurrentImageViewBitmap();
    }

    @Override
    protected void onDestroy() {
        //清空集合
        if (mDatas != null) {
            mDatas.clear();
            mDatas = null;
        }
        imagePresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，其它的交给AndPermission吧，最后一个参数是PermissionListener。
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if(requestCode == 100) {
                MySnackbar.makeSnackBarBlack(toolbar, "权限申请成功");
                imagePresenter.saveImage();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(ImagesActivity.this, deniedPermissions)) {
                // 第二种：用自定义的提示语。
                 AndPermission.defaultSettingDialog(ImagesActivity.this, 300)
                 .setTitle("权限申请失败")
                 .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                 .setPositiveButton("好，去设置")
                 .show();
            }
        }
    };

}
