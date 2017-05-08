package com.maning.gankmm.ui.activity.mob;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.maning.gankmm.R;
import com.maning.gankmm.bean.mob.MobPhoneAddressEntity;
import com.maning.gankmm.http.GankApi;
import com.maning.gankmm.http.MyCallBack;
import com.maning.gankmm.skin.SkinManager;
import com.maning.gankmm.ui.base.BaseActivity;
import com.maning.gankmm.ui.view.MClearEditText;
import com.maning.gankmm.utils.GankUtils;
import com.maning.gankmm.utils.KeyboardUtils;
import com.maning.gankmm.utils.MySnackbar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手机号码归属地查询
 */
public class PhoneAddressActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_query)
    Button btnQuery;
    @Bind(R.id.editTextPhone)
    MClearEditText editTextPhone;
    @Bind(R.id.tv_operator)
    TextView tvOperator;
    @Bind(R.id.tv_city_name)
    TextView tvCityName;
    @Bind(R.id.tv_city_code)
    TextView tvCityCode;
    @Bind(R.id.tv_zip_code)
    TextView tvZipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_address);
        ButterKnife.bind(this);

        initMyToolBar();

    }

    private void initMyToolBar() {
        int currentSkinType = SkinManager.getCurrentSkinType(this);
        if (SkinManager.THEME_DAY == currentSkinType) {
            initToolBar(toolbar, "手机号码归属地查询", R.drawable.gank_ic_back_white);
        } else {
            initToolBar(toolbar, "手机号码归属地查询", R.drawable.gank_ic_back_night);
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


    @OnClick(R.id.btn_query)
    public void btnQuery() {

        KeyboardUtils.hideSoftInput(this);

        //获取手机号码
        String phoneNumber = editTextPhone.getText().toString();

        if (TextUtils.isEmpty(phoneNumber)) {
            MySnackbar.makeSnackBarRed(toolbar, "手机号码不能为空");
            return;
        }

        if (!GankUtils.isMobile(phoneNumber)) {
            MySnackbar.makeSnackBarRed(toolbar, "手机号码格式不正确");
            return;
        }

        showProgressDialog("正在查询...");
        GankApi.queryPhoneAddress(phoneNumber, 0x001, new MyCallBack() {
            @Override
            public void onSuccess(int what, Object result) {
                dissmissProgressDialog();
                if (result != null) {
                    MobPhoneAddressEntity mobPhone = (MobPhoneAddressEntity) result;
                    tvOperator.setText(mobPhone.getOperator());
                    tvCityName.setText(mobPhone.getProvince() + " " + mobPhone.getCity());
                    tvCityCode.setText(mobPhone.getCityCode());
                    tvZipCode.setText(mobPhone.getZipCode());
                }
            }

            @Override
            public void onSuccessList(int what, List results) {

            }

            @Override
            public void onFail(int what, String result) {
                dissmissProgressDialog();
            }
        });

    }

}
