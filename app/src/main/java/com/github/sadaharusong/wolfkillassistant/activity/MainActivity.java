package com.github.sadaharusong.wolfkillassistant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.adapter.RoleAdapter;
import com.github.sadaharusong.wolfkillassistant.listener.OnItemClickListener;
import com.github.sadaharusong.wolfkillassistant.model.Role;
import com.github.sadaharusong.wolfkillassistant.util.TextUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.iv_reduce)
    ImageView mIvReduce;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    @BindView(R.id.et_count)
    EditText mEtCount;
    @BindView(R.id.rv_role)
    RecyclerView mRv;
    @BindView(R.id.tv_close_eye)
    TextView mTvCloseEye;

    RoleAdapter mAdapter;
    List<Role> mList;
    Set<Role> mRoleSet;
    boolean isNeedSignLover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        mRoleSet = new HashSet<>();
        isNeedSignLover = false;
        String[] roleArray = getResources().getStringArray(R.array.role_list);
        for (String roleStr : roleArray) {
            Role role = new Role();
            role.setName(roleStr);
            role.setSelected(false);
            mList.add(role);
        }
        mAdapter = new RoleAdapter(this);
        mAdapter.setOnItemClickListener(this);
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRv.setAdapter(mAdapter);
        mAdapter.setData(mList);

        mEtCount.setSelection(mEtCount.getText().toString().length());

        mEtCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = mEtCount.getText().toString().trim();
                if (TextUtils.isStartFromZero(str)){
                    String newStr = str.replaceAll("^(0+)", "1");
                    mEtCount.setText(newStr);
                }
                mEtCount.setSelection(mEtCount.getText().toString().length());
            }
        });
    }

    @OnClick({R.id.iv_add, R.id.iv_reduce, R.id.tv_close_eye})
    void onClick(View v) {
        String count = mEtCount.getText().toString().trim();
        int currentCount = Integer.valueOf(count);
        switch (v.getId()) {
            case R.id.iv_add:
                currentCount++;
                mEtCount.setText(String.valueOf(currentCount));
                break;

            case R.id.iv_reduce:
                if (currentCount <= 1){
                    return;
                }
                currentCount--;
                mEtCount.setText(String.valueOf(currentCount));
                break;

            case R.id.tv_close_eye:
                if (mRoleSet.isEmpty()) return;
                ArrayList<Role> list = new ArrayList<>(mRoleSet);
                for (Role role : list) {
                    if (role.getName().equals(getString(R.string.cupid))) {
                        isNeedSignLover = true;
                    }
                }

                Role villager = new Role();
                villager.setName(getString(R.string.villager));
                list.add(0, villager);

                Role werewolf = new Role();
                werewolf.setName(getString(R.string.werewolf));
                list.add(1, werewolf);
                SetRoleActivity.enter(this, list, currentCount, isNeedSignLover);
                isNeedSignLover = false;
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        String name = mList.get(position).getName();
        boolean isSelect = mList.get(position).isSelected();
        if (isSelect) {
            mRoleSet.add(mList.get(position));
        }else {
            Iterator<Role> iterator = mRoleSet.iterator();
            while (iterator.hasNext()) {
                Role role = iterator.next();

                if (role.getName().equals(name)) {
                    iterator.remove();
                }
            }
        }
    }
}
