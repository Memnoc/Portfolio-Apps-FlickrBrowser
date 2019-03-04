package com.smartdroidesign.flickrbrowser;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;

public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d(TAG, "onCreate: starts");

        activateToolbar(true);
        Log.d(TAG, "onCreate: ends");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: starts");
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // SearchManager provides access to the system search services
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // Getting a reference to the SearchView widget embedded in the toolbar (app_bar_search)
        mSearchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        // Get the SearchManager to retrieve the searchable info from searchable.xml
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        // Set the info into their SearchView widget to configure it
        mSearchView.setSearchableInfo(searchableInfo);
        Log.d(TAG, "onCreateOptionsMenu: " + getComponentName().toString());
        Log.d(TAG, "onCreateOptionsMenu: hint is" + mSearchView.getQueryHint());
        Log.d(TAG, "onCreateOptionsMenu: searchable info is " + searchableInfo.toString());

        mSearchView.setIconified(false);

        Log.d(TAG, "onCreateOptionsMenu: returned " + true);

        return true;
    }
}
