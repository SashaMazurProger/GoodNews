package com.example.sasham.goodnews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.utils.SharedPreferencesHelper;
import com.hbb20.CountryCodePicker;


public class ArticlesSettingsFragment extends Fragment {

    private CountryCodePicker codePicker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_articles_settings, container, false);

        codePicker = rootView.findViewById(R.id.country_code_picker);
        codePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                String codeName = codePicker.getSelectedCountryNameCode();
                String key = getString(R.string.articles_settings_country_key);
                SharedPreferencesHelper.setSharedPreferenceString(ArticlesSettingsFragment.this.getContext(), key, codeName);
            }
        });
        return rootView;
    }
}
