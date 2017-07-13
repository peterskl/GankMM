package com.maning.gankmm.ui.activity.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.maning.gankmm.R;
import com.maning.gankmm.skin.SkinManager;
import com.maning.gankmm.ui.base.BaseActivity;
import com.maning.gankmm.utils.AppDateMgr;
import com.maning.gankmm.utils.DialogUtils;
import com.maning.gankmm.utils.MySnackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 编辑用户信息页面
 */
public class EditUserInfoActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.avatar)
    CircleImageView mAvatar;
    @Bind(R.id.tv_sex)
    TextView mTvSex;
    @Bind(R.id.tv_birth)
    TextView mTvBirth;
    @Bind(R.id.tv_signature)
    TextView mTvSignature;

    private TimePickerView mTimePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        ButterKnife.bind(this);

        initMyToolBar();


        initPickerView();
    }

    private void initPickerView() {
        //时间选择器
        mTimePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //日期转换
                String chooseDateStr = AppDateMgr.parseYyyyMmDd(date);
                mTvBirth.setText(chooseDateStr);
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .setSubmitColor(mContext.getResources().getColor(R.color.main_color))
                .setCancelColor(mContext.getResources().getColor(R.color.main_color))
                .build();
    }

    private void initMyToolBar() {
        int currentSkinType = SkinManager.getCurrentSkinType(this);
        if (SkinManager.THEME_DAY == currentSkinType) {
            initToolBar(mToolbar, "编辑资料", R.drawable.gank_ic_back_white);
        } else {
            initToolBar(mToolbar, "编辑资料", R.drawable.gank_ic_back_night);
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


    @OnClick(R.id.rl_item_avatar)
    public void rl_item_avatar() {

    }

    @OnClick(R.id.rl_item_user_name)
    public void rl_item_user_name() {
        MySnackbar.makeSnackBarGreen(mToolbar, "不能修改用户名");
    }

    @OnClick(R.id.rl_item_user_sex)
    public void rl_item_user_sex() {
        DialogUtils.showMyListDialog(mContext, "性别", R.array.sexs, new DialogUtils.OnDialogListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                mTvSex.setText(text);
            }
        });
    }

    @OnClick(R.id.rl_item_user_birth)
    public void rl_item_user_birth() {
        String birth = mTvBirth.getText().toString();
        if (TextUtils.isEmpty(birth) || birth.equals("未填写")) {
            mTimePickerView.setDate(Calendar.getInstance());
        } else {
            //日期转换
            Calendar selectedDate = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = sdf.parse(birth);
            } catch (ParseException e) {
                e.printStackTrace();
                date = new Date();
            }
            selectedDate.setTime(date);
            mTimePickerView.setDate(selectedDate);
        }
        mTimePickerView.show();
    }

    @OnClick(R.id.rl_item_user_signature)
    public void rl_item_user_signature() {

    }


}
