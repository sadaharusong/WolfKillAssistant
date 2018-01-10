package com.github.sadaharusong.wolfkillassistant.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.sadaharusong.wolfkillassistant.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sadaharusong
 * @date 2017/12/10 0010.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */


public class FinalResultFragment extends Fragment{
    @BindView(R.id.result_button)
    Button mResultButton;

    @BindView(R.id.result_text)
    TextView mResultTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.final_game_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result;
                if (FragmentJumpManager.thisRoundPosition.size() > 0){
                    StringBuilder resultNum = new StringBuilder();
                    for (int a : FragmentJumpManager.thisRoundPosition){
                        resultNum = resultNum.append("[ " +(a + 1) + "] ");
                    }
                    result = getString(R.string.final_result_dead, resultNum);
                }else {
                    result = getString(R.string.final_result_safe);
                }
                mResultTextView.setVisibility(View.VISIBLE);
                mResultTextView.setText(result);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
