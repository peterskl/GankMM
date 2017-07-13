package com.maning.gankmm.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.maning.gankmm.R;
import com.maning.gankmm.ui.base.BaseActivity;
import com.maning.gankmm.utils.DialogUtils;
import com.maning.gankmm.utils.UserUtils;
import com.maning.librarycrashmonitor.utils.StatusBarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends BaseActivity {

    @Bind(R.id.avatar)
    CircleImageView mAvatar;
    @Bind(R.id.ll_content)
    LinearLayout mLlContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);

        StatusBarUtil.setTranslucentForImageView(this, 20, mLlContent);
    }


    @OnClick(R.id.btn_edit_info)
    public void btn_edit_info() {
        startActivity(new Intent(this, EditUserInfoActivity.class));
    }


    @OnClick(R.id.btn_back)
    public void btn_back() {
        this.finish();
    }


    @OnClick(R.id.btn_quit_login)
    public void btn_quit_login() {
        //退出登录
        DialogUtils.showMyDialog(mContext, "退出提示", "确定要退出当前用户吗?", "退出", "取消", new DialogUtils.OnDialogClickListener() {
            @Override
            public void onConfirm() {
                quitLogin();
            }

            @Override
            public void onCancel() {

            }
        });

    }

    private void quitLogin() {
        //清空登录信息
        UserUtils.quitLogin();
        this.finish();
    }

}
