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

    /**
     * Constructs a new {@link JobsAdapter}.
     *
     * @param context of the app
     * @param jobs    is the list of jobs, which is the data source of the adapter
     */
    public JobsAdapter(Context context, List<Jobs> jobs) {
        super(context, 0, jobs);
    }

    // Initialize ViewHolder as a cache mechanism for storing Views
    public static class ViewHolder {
        TextView titleView;
        TextView companyView;
        TextView jobTypeView;
        TextView seniorityView;
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

            // Display the title of the job in that TextView
            holder.titleView = listItemView.findViewById(R.id.title);
            holder.titleView.setText(currentJobs.getTitle());

            // Display the title of the job in that TextView
            holder.companyView = listItemView.findViewById(R.id.company);
            holder.companyView.setText(currentJobs.getCompany());

            // Display the title of the job in that TextView
            holder.jobTypeView = listItemView.findViewById(R.id.job_type);
            holder.jobTypeView.setText(currentJobs.getJobType());

            // Display the title of the job in that TextView
            holder.seniorityView = listItemView.findViewById(R.id.seniority);
            holder.seniorityView.setText(currentJobs.getSeniority());

        }
        return listItemView;
    }
}