package com.github.sadaharusong.wolfkillassistant.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.adapter.RolAdapter;
import com.github.sadaharusong.wolfkillassistant.adapter.SetRoleAdapter;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.model.GameInfo;
import com.github.sadaharusong.wolfkillassistant.model.Role;
import com.github.sadaharusong.wolfkillassistant.model.RoleMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 * @author sadaharusong
 * @date 2017/9/17 0017
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */
public class SetRoleActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final String ROLE_LIST = "role_list";
    private static final String ROLE_COUNT = "role_count";
    private static final String IS_NEED_SIGN_LOVER = "isNeedSignLover";
    private static Map<Integer, Role> mRoleMap = new HashMap<>();

    private static final int LEFT_DRAWER = 0x00;
    private static final int RIGHT_DRAWER = 0x01;

    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.rv_role)
    RecyclerView mRvRole;
    @BindView(R.id.lv_role_start)
    ListView mLvStart;
    @BindView(R.id.lv_role_end)
    ListView mLvEnd;
    @BindView(R.id.tv_confirm_start)
    TextView mTvConfirmStart;
    @BindView(R.id.tv_confirm_end)
    TextView mTvConfirmEnd;

    @BindView(R.id.tv_sign_status_start)
    TextView mtvSignStatusStart;

    @BindView(R.id.tv_sign_status_end)
    TextView mtvSignStatusEnd;

    @BindView(R.id.start)
    TextView mStart;

    private List<Role> mList;
    private RolAdapter mRolAdapter;
    private SetRoleAdapter mAdapter;

    private List<Role> mRoleList;
    private Role mGameRole;
    private int mPlayerCount;
    private int mCurrentPos;

    private List<TextView> mTextViewsStart;
    private List<TextView> mTextViewsEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_role);
        ButterKnife.bind(this);

        mRoleList = getIntent().getParcelableArrayListExtra(ROLE_LIST);
        mPlayerCount = getIntent().getIntExtra(ROLE_COUNT, -1);
        initData();
    }

    private void initData() {

        mGameRole = new Role();
        mGameRole.setName(getString(R.string.default_role));
        mGameRole.setSetted(false);
        mList = new ArrayList<>();
        if (mPlayerCount != -1) {
            for (int i = 0; i < mPlayerCount; i++) {
                Role role = new Role();
                role.setName(getString(R.string.default_role));
                role.setSetted(false);
                mList.add(role);
            }
        }
        mCurrentPos = 0;
        mAdapter = new SetRoleAdapter(this);
        mRvRole.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        mRvRole.setAdapter(mAdapter);
        mAdapter.setData(mList);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onItemClick(int position) {

                mCurrentPos = position;
                mGameRole = mList.get(mCurrentPos);
                boolean leftSide = (mList.size() % 2 == 0 && position < mList.size() / 2);
                boolean rightSide = (mList.size() % 2 != 0 && position < mList.size() / 2 + 1);
                if ( leftSide || rightSide) {
                    if (mDl.isDrawerOpen(Gravity.RIGHT)) {
                        mDl.closeDrawer(Gravity.RIGHT);
                    } else {
                        mtvSignStatusEnd.setText(String.format(getString(R.string.sing_player_status), (position + 1)));
                        mRolAdapter.setRole(mGameRole);
                        mDl.openDrawer(Gravity.RIGHT);
                    }
                } else {
                    if (mDl.isDrawerOpen(Gravity.LEFT)) {
                        mDl.closeDrawer(Gravity.LEFT);
                    } else {
                        mtvSignStatusStart.setText(String.format(getString(R.string.sing_player_status), (position + 1)));
                        mRolAdapter.setRole(mGameRole);
                        mDl.openDrawer(Gravity.LEFT);
                    }
                }
            }
        });

        mRolAdapter = new RolAdapter(this, mRoleList, mGameRole);
        mLvEnd.setAdapter(mRolAdapter);
        mLvEnd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGameRole = mRoleList.get(position);
                mRolAdapter.setRole(mGameRole);
            }
        });

        mLvStart.setAdapter(mRolAdapter);
        mLvStart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGameRole = mRoleList.get(position);
                mRolAdapter.setRole(mGameRole);
            }
        });

        mDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mTextViewsEnd = new ArrayList<>();
        mTextViewsStart = new ArrayList<>();

        for (int i = 0; i < mTextViewsStart.size(); i++) {
            mTextViewsStart.get(i).setTag((i + 1));
        }

        for (int i = 0; i < mTextViewsEnd.size(); i++) {
            mTextViewsEnd.get(i).setTag((i + 1));
        }
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public void onBackPressed() {
        if (mDl.isDrawerOpen(Gravity.LEFT)) {
            mDl.closeDrawer(Gravity.LEFT);
            return;
        }

        if (mDl.isDrawerOpen(Gravity.RIGHT)) {
            mDl.closeDrawer(Gravity.RIGHT);
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(R.string.message_clear_data)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SetRoleActivity.super.onBackPressed();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressLint("RtlHardcoded")
    @OnClick({R.id.tv_confirm_start, R.id.tv_confirm_end, R.id.start})
    void onBtnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm_start:
                confirmSetRole();
                if (mDl.isDrawerOpen(Gravity.LEFT)) {
                    mDl.closeDrawer(Gravity.LEFT);
                }
                break;

            case R.id.tv_confirm_end:
                confirmSetRole();
                if (mDl.isDrawerOpen(Gravity.RIGHT)) {
                    mDl.closeDrawer(Gravity.RIGHT);
                }
                break;
            case R.id.start:
                if (RoleMap.getInstance().getRoleMap().size() == mPlayerCount){
                    Intent intent = new Intent(this, GameActivity.class);
                    this.startActivity(intent);
                }else {
                    Toast.makeText(SetRoleActivity.this, "角色还没输入完毕，不可开始！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void confirmSetRole() {
        if (mGameRole.getName().equals(getString(R.string.default_role))){
            Toast.makeText(SetRoleActivity.this, getString(R.string.role_not , mCurrentPos + 1), Toast.LENGTH_SHORT).show();
        }else {
            RoleMap.getInstance().addRole(mCurrentPos, mGameRole);
            mList.get(mCurrentPos).setName(getResources().getString(R.string.role_ok));
            mList.get(mCurrentPos).setSetted(true);
            mAdapter.notifyDataSetChanged();
        }

    }

    public static void enter(Context context) {
        Intent intent = new Intent(context, SetRoleActivity.class);
        intent.putParcelableArrayListExtra(ROLE_LIST, (ArrayList<? extends Parcelable>) GameInfo.getInstance().getRoleList());
        intent.putExtra(ROLE_COUNT, GameInfo.getInstance().getPlayerCount());
        intent.putExtra(IS_NEED_SIGN_LOVER, GameInfo.getInstance().isNeedLover());
        context.startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        for (TextView tv : mTextViewsStart) {
            tv.setSelected(false);
        }
        for (TextView tv : mTextViewsEnd) {
            tv.setSelected(false);
        }
    }

}
