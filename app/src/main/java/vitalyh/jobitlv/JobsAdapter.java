package vitalyh.jobitlv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * An {@link JobsAdapter} knows how to create a list item layout for each "piece" of job
 * a list of {@link Jobs} objects).
 * These list item layouts will be provided to an adapter
 */

public class JobsAdapter extends ArrayAdapter<Jobs> {

    private static final String DATE_SEPARATOR = "T";
    private static final String TIME_SEPARATOR = "Z";

    /**
     * Constructs a new {@link JobsAdapter}.
     *
     * @param context of the app
     * @param jobs    is the list of jobs, which is the data source of the adapter
     */
    public JobsAdapter(Context context, List<Jobs> jobs) {
        super(context, 0, jobs);
    }

    // Initialize ViewHolder as a cache mechanism
    // for storing Views
    public static class ViewHolder {
        //TODO initialize views
        // TextView titleView;

    }

    /**
     * Returns a list item view that displays information about the jobs at the given position in the list
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.jobs_list_item, parent, false);
        }

        // Find the job at the given position in the list of jobs
        Jobs currentJobs = getItem(position);

        // Create new ViewHolder for text views.
        ViewHolder holder = new ViewHolder();

        if (currentJobs != null) {
            //TODO Holder views
            // Display the title of the job in that TextView
            //holder.titleView = listItemView.findViewById(R.id.jobs_title);
            //holder.titleView.setText(currentJobs.getJobsTitle());



            // Variables for the Date field transformation.
            String originalDate = currentJobs.getJobsDate();
            String dateOffset = "";
            String dateLeftover;
            String timeOffset = "";

            if (originalDate.contains(DATE_SEPARATOR)) {
                // Split the string into different parts (as an array of Strings)
                // based on the "T" text. We expect an array of 2 Strings, where
                // the first String will be "date" and the second String will be "time".
                String[] date = originalDate.split(DATE_SEPARATOR);
                dateOffset = date[0];
                dateLeftover = date[1];

                // Do the same with "leftover" of date, which contains time and separator "Z"
                String[] time = dateLeftover.split(TIME_SEPARATOR);
                timeOffset = time[0];
            }

            // Find the TextView with view ID location
            holder.dateView = listItemView.findViewById(R.id.jobs_date);

            // Display the date and time and "," between them
            String dateTimeWithSeparator = dateOffset + getContext().getString(R.string.date_time_separator) + timeOffset;
            holder.dateView.setText(dateTimeWithSeparator);
        }
        return listItemView;
    }
}