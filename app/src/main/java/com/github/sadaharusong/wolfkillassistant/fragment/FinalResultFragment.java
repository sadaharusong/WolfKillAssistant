package com.github.sadaharusong.wolfkillassistant.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.sadaharusong.wolfkillassistant.R;
import com.github.sadaharusong.wolfkillassistant.activity.MainActivity;
import com.github.sadaharusong.wolfkillassistant.activity.SetRoleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author sadaharusong
 * @date 2017/12/10 0010.
 * Github : https://github.com/sadaharusong
 * Email : jacksomangel@163.com
 */


public class FinalResultFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.result_button)
    Button mResultButton;

    @BindView(R.id.replay_button)
    Button mReplayButton;

    @BindView(R.id.reset_button)
    TextView mResetButton;

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
        mResultButton.setOnClickListener(this);
        mReplayButton.setOnClickListener(this);
        mResetButton.setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.result_button:
                resultDo();
                break;
            case R.id.replay_button:
                replayDo();
                break;
            case R.id.reset_button:
                resetDo();
                break;
            default:
        }
    }

    private void resultDo(){
        String result;
        if (GameFragmentManager.thisRoundPosition.size() > 0){
            StringBuilder resultNum = new StringBuilder();
            for (int a : GameFragmentManager.thisRoundPosition){
                resultNum = resultNum.append("[ " +(a + 1) + "] ");
            }
            result = getString(R.string.final_result_dead, resultNum);
        }else {
            result = getString(R.string.final_result_safe);
        }
        mResultTextView.setVisibility(View.VISIBLE);
        mResultTextView.setText(result);
    }

    private void replayDo(){
        SetRoleActivity.enter(getActivity());
    }

    private void resetDo() {
        Intent intent = new Intent(getActivity() , MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
