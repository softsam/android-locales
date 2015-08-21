package com.orange.androidlocales;

import android.content.res.Configuration;
import android.widget.Toast;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import java.util.Locale;

/**
 * Change locale. Summoned via an intent, with extras (locale and country). Both are optional, will do nothing if none is provided.
 */
@EActivity
public class ChangeLocaleActivity extends BaseActivity {

    @Extra("language")
    String language;

    @Extra("country")
    String country;

    @AfterExtras
    public void updateLocaleAndCountry() {

        try {
            ActivityManagerNative manager = new ActivityManagerNative();
            Configuration configuration = manager.getConfiguration();
            Locale locale = requestedLocale(configuration);
            configuration.locale = locale;
            manager.updateConfiguration(configuration);
            Toast.makeText(this, String.format("Locale updated to %s_%s", language, country), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, String.format("Failed to updated locale to %s_%s", language, country), Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }

    private Locale requestedLocale(Configuration configuration) {
        if (language == null) {
            language = configuration.locale.getLanguage();
        }
        if (country == null) {
            country = configuration.locale.getCountry();
        }
        return new Locale(language, country);
    }
}
