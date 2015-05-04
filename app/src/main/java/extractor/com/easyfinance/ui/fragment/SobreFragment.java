package extractor.com.easyfinance.ui.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * @author Muryllo Tiraza
 */
public class SobreFragment extends Fragment{

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Toast.makeText(activity, "Sobre Fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
