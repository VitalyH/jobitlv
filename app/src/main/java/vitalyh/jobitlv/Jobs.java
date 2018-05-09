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
     * Experience of the candidate
     */
    private final String mExperience;

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
     * @param experience is the expected experience
     * @param url     is the website URL to the job offering
     */
    public Jobs(String title, String company, String jobType, String experience, String url) {
        mTitle = title;
        mCompany = company;
        mJobType = jobType;
        mExperience = experience;
        mUrl = url;
    }

    /**
     * Returns the name of the company.
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
     * Returns the name of the company.
     */
    public String getJobType() {
        return mJobType;
    }

    /**
     * Returns the name of the company.
     */
    public String getExperience() {
        return mExperience;
    }

    /**
     * Returns the URL of the job offering
     */
    public String getUrl() {
        return mUrl;
    }
}