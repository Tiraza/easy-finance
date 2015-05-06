package extractor.com.easyfinance.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import extractor.com.easyfinance.ui.activity.MainActivity;

/**
 * @author Muryllo Tiraza
 */
public class ReceitasFragment extends Fragment{

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.mToolbar.setTitle("Receitas");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Fragment Receitas", Toast.LENGTH_SHORT).show();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
