package br.com.extractor.easyfinance.ui.configuracoes;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.arquitetura.preference.Preferences;
import br.com.extractor.easyfinance.ui.ActivityCommunication;
import br.com.extractor.easyfinance.ui.FragmentCommunication;

public class ConfiguracoesFragment extends PreferenceFragment implements FragmentCommunication, Preference.OnPreferenceChangeListener {

    private ActivityCommunication activityCommunication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        configPreference(findPreference(Preferences.USERNAME));
    }

    private void configPreference(Preference preference) {
        String value = Preferences.getString(preference.getKey(), "");
        preference.setOnPreferenceChangeListener(this);
        preference.setSummary(value);
        preference.setDefaultValue(value);
    }

    @Override
    public boolean hasPendencies() {
        return false;
    }

    @Override
    public void freePendencies() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCommunication = (ActivityCommunication) activity;
        } catch (Exception e) {
            throw new ClassCastException(activity.getClass().getSimpleName() + " must implement "
                    + ActivityCommunication.class.getSimpleName());
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        Preferences.putString(preference.getKey(), (String) o);
        preference.setSummary((String) o);
        preference.setDefaultValue(o);
        activityCommunication.updateValues();
        return false;
    }

}
