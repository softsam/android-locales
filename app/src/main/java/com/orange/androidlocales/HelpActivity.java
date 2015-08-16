package com.orange.androidlocales;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Help activity. Run by default, when the application is launched, displays some documentation on how to use this application.
 */
@EActivity(R.layout.help_activity)
public class HelpActivity extends BaseActivity {

    @ViewById(R.id.more_help)
    TextView moreHelpView;

    @AfterViews
    void activateMoreHelpLink() {
        moreHelpView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
