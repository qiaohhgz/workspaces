package ibatis;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/25/13
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestMain {
    private int getTheClassOfBudgets(String verticalName, String headingName) {
        final String CHANGE_BUDGET_TAG = "$$MORE$$";
        final int MIN_MOST_BUDGET = 500;
        final int MIN_MORE_BUDGET = 300;
        final int MIN_COMMON_BUDGET = 200;
        String[] topVerticalWords = {"Cleaning", "Cleaning", "Cleaning", "Cleaning",
                "Contractors Other", "Contractors Plumbers", "Contractors Roofers",
                "Heating & Air Conditioning", "Heating & Air Conditioning", "Insurance",
                "Professional Other", "Professional Other", "Real Estate", CHANGE_BUDGET_TAG, "Cleaning",
                "Contractors Electricians", "Contractors Exterior", "Contractors Other", "Doctors",
                "Locksmiths", "Moving & Storage", "Moving & Storage", "Pest Control", "Legal Other"};
        String[] topCategoryWords = {"Fire Damage", "Flood Damage", "Water Damage",
                "Mold and Mildew Removal", "Waterproofing Contractors", "Plumber",
                "Roofing Contractors", "Air Conditioning", "General HVAC", "Insurance",
                "Financial Services", "Accountants", "Mortgages", CHANGE_BUDGET_TAG, "Carpet Cleaning",
                "Electrical Contractors", "Siding Contractors", "Garages", "Chiropractors",
                "Locksmith", "Moving Companies", "Movers", "Exterminator", "Bail Bonds"};
        int budgetOfClass = MIN_COMMON_BUDGET;
        if ("Lawyers".equals(verticalName)) {
            budgetOfClass = MIN_MOST_BUDGET;
        } else if ("Dentists".equals(verticalName)) {
            budgetOfClass = MIN_MORE_BUDGET;
        } else {
            int budget = MIN_MOST_BUDGET;
            for (int i = 0; i < topVerticalWords.length; i++) {
                if (topVerticalWords[i].equals(CHANGE_BUDGET_TAG)) {
                    budget = MIN_MORE_BUDGET;
                    continue;
                }
                if (verticalName.equals(topVerticalWords[i]) && headingName.equals(topCategoryWords[i])) {
                    budgetOfClass = budget;
                    break;
                }
            }
        }
        return budgetOfClass;
    }
}
