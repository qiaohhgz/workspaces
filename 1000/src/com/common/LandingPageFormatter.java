package com.common;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: chokkh01
 * Date: Jun 4, 2009
 * Time: 5:55:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class LandingPageFormatter {

    private String urlPrefix;
    private String urlSuffix;

    /**
     * For the landing page, for the first ad group, we will use the advertiser name and generate the URL.
     * If the advertiser name is "Tim's Plumbing & Heating". We will generate the URL as "http://www.wrpage.com/Tim's-Plumbing-And-Heating/".
     * What we do here is, we take the advertiser name "Tim's Plumbing & Heating" and replace the spaces and special characters with "-" (dash).
     * Also we need to replace the & with 'and'. Special characters we need to replace with dash are `~!@#$%^*()<>/\[];:,."{}? .
     * After this we need to append "http://www.wrpage.com/" to the start of the newly generated advertiser name.
     *
     * @param advertiserName
     * @param mbId
     * @return
     */
    public String getFormattedLandingPageURL(String advertiserName, int mbId) {
        String destURL = null;
        if (advertiserName != null) {
            //advertiserName = advertiserName.replaceAll("'", "");
            Pattern pattern = Pattern.compile("[`~!@#\\$%\\^\\*\\(\\)<>/\\\\\\[\\];:,\\.\"\\{\\}\\?\\s]");
            Matcher matcher = pattern.matcher(advertiserName);
            String formattedString = matcher.replaceAll("-");
            formattedString = formattedString.replaceAll("&", "and");
            String wrPageUrlPrefix = urlPrefix;
            String wrPageUrlSuffix = urlSuffix;
            destURL = wrPageUrlPrefix + formattedString + "-" + mbId + wrPageUrlSuffix;
        }
        return destURL;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }
}
