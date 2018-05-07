package vitalyh.jobitlv;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Jobs>> {

    /**
     * URL for jobs data.
     */
    private static final String JOBS_REQUEST_URL =
            "JSOB URL";

    /**
     * Constant value for the jobs loader ID.
     */
    private static final int JOBS_LOADER_ID = 1;

    /**
     * Adapter for the list of jobs.
     */
    private JobsAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize floating action button (FAB)
        // Setup listener
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            // Refresh a jobs by pushing the FAB
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Find a reference to the ListView in the layout
        ListView jobsListView = findViewById(R.id.list_jobs);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        jobsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of jobs as input
        mAdapter = new JobsAdapter(this, new ArrayList<Jobs>());

        // Set the adapter on the ListView
        // so the list can be populated in the user interface
        jobsListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser.
        jobsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current jobs.
                Jobs currentJobs = mAdapter.getItem(position);

                // Convert the String URL into a URI object.
                if (currentJobs != null) {
                    Uri jobsUri = Uri.parse(currentJobs.getUrl());

                    // Create a new intent to view the jobs URI
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, jobsUri);

                    // Check whatever is an activity available that can respond to the intent
                    PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(websiteIntent,
                            PackageManager.MATCH_DEFAULT_ONLY);
                    boolean isIntentSafe = activities.size() > 0;

                    if (isIntentSafe) {
                        // Send the intent to launch a new activity
                        startActivity(websiteIntent);
                    } else {
                        // If there is no web-browser
                        // Show Toast message with warning
                        Toast.makeText(getApplicationContext(), getString(R.string.no_browser), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        // Prevent null pointer exception in getActiveNetworkInfo()
        if (connMgr != null) {
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // If there is a network connection, fetch data
            if (networkInfo != null && networkInfo.isConnected()) {
                // Get a reference to the LoaderManager, in order to interact with loaders.
                LoaderManager loaderManager = getLoaderManager();

                // Initialize the loader.
                loaderManager.initLoader(JOBS_LOADER_ID, null, this);

            } else {
                // Otherwise, display error
                // Hide loading indicator so error message will be visible
                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.GONE);

                // Update empty state with no connection error message
                mEmptyStateTextView.setText(R.string.no_internet_connection);
            }
        } else {
            // Otherwise, display error with no connection error message
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    @Override
    public Loader<List<Jobs>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // getString retrieves a String value from the preferences
        // The second parameter is the default value
        //TODO implement settings like this

        // This one for order of displaying Jobs
        //String orderByValue = sharedPreferences.getString(
        //        getString(R.string.settings_order_by_key),
        //        getString(R.string.settings_order_by_default));




        Uri baseUri = Uri.parse(JOBS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        // Additional query parameters.
        // Allow users to customize them via Settings
        // Liek this
        //uriBuilder.appendQueryParameter(API_KEY, API_KEY_VALUE);

        return new JobsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Jobs>> loader, List<Jobs> jobs) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No jobs found."
        mEmptyStateTextView.setText(R.string.no_jobs);

        // Clear the adapter of previous data
        mAdapter.clear();

        // If there is a valid list of Jobs, then add them to the adapter's data set
        if (jobs != null && !jobs.isEmpty()) {
            mAdapter.addAll(jobs);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Jobs>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }


    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
