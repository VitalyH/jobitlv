package vitalyh.jobitlv;

/**
 * An {@link Jobs} object contains information about jobs in IT in Latvia.
 */
public class Jobs {

    /**
     * Name of the company
     */
    private final String mCompany;

    //TODO more variables

    /**
     * Website URL of the job offering
     */
    private final String mUrl;

    /**
     * Constructs a new {@link Jobs} object.
     *
     * @param company is the name of the company
        //TODO more params
     * @param url     is the website URL to the job offering
     */
    public Jobs(String company, .... url) {
        //TODO finish the constructor
        mCompany = company;

        mUrl = url;
    }

    /**
     * Returns the name of the company.
     */
    public String getCompany() {
        return mCompany;
    }

    //TODO more getSomething

    /**
     * Returns the URL of the job offering
     */
    public String getUrl() {
        return mUrl;
    }
}