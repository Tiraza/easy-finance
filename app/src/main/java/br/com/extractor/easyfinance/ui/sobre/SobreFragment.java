package br.com.extractor.easyfinance.ui.sobre;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluejamesbond.text.DocumentView;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.ui.FragmentCommunication;
import br.com.extractor.easyfinance.util.LoadResources;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SobreFragment extends Fragment implements FragmentCommunication {

    @Bind(R.id.txt_license)
    DocumentView txtLicense;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sobre_fragment, container, false);
        ButterKnife.bind(this, rootView);

        txtLicense.setText(LoadResources.loadLicense(getActivity()));

        return rootView;
    }

    @OnClick(R.id.btn_developers)
    public void onClickDevelopers() {

    }

    @OnClick(R.id.btn_libraries)
    public void onClickLibraries() {

    }

    @Override
    public boolean hasPendencies() {
        return false;
    }

    @Override
    public void freePendencies() {

    }

}
