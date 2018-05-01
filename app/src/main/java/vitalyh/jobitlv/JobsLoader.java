package vitalyh.jobitlv;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

/**
 * Loads a list of jobs by using an AsyncTask to perform the
 * network request to the given URL.
 */

public class JobsLoader extends AsyncTaskLoader<List<Jobs>> {

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link JobsLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public JobsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Jobs> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of jobs.
        return QueryUtils.fetchJobsData(mUrl);
    }
}