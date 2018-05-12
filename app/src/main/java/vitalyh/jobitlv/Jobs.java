package vitalyh.jobitlv;

/**
 * An {@link Jobs} object contains information about jobs in IT in Latvia.
 */
public class Jobs {

    /**
     * Job title
     */
    private final String mTitle;

    /**
     * Name of the company
     */
    private final String mCompany;

    /**
     * Type of the job
     */
    private final String mJobType;

    /**
     * Seniority level of the candidate
     */
    private final String mSeniority;

    /**
     * Website URL of the job offering
     */
    private final String mUrl;

    /**
     * Constructs a new {@link Jobs} object.
     *
     * @param title is the job' title
     * @param company is the name of the company
     * @param jobType is the type of the job
     * @param seniority is the seniority level of the candidat
     * @param url     is the website URL to the job offering
     */
    public Jobs(String title, String company, String jobType, String seniority, String url) {
        mTitle = title;
        mCompany = company;
        mJobType = jobType;
        mSeniority = seniority;
        mUrl = url;
    }

    /**
     * Returns the title of the job
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the name of the company.
     */
    public String getCompany() {
        return mCompany;
    }

    /**
     * Returns the type of the job
     */
    public String getJobType() {
        return mJobType;
    }

    /**
     * Returns the seniority level
     */
    public String getSeniority() {
        return mSeniority;
    }

    /**
     * Returns the URL of the job offering
     */
    public String getUrl() {
        return mUrl;
    }
}