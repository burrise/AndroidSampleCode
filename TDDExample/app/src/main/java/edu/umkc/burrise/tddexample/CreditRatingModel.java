package edu.umkc.burrise.tddexample;

public class CreditRatingModel {
    // 800 or above is excellent.
    private int creditScore;
    public CreditRatingModel (int creditScore) {
        this.creditScore = creditScore;
    }

    public boolean grantCredit() {
        if (creditScore >= 800)
            return true;
        else
            return false;
    }
}

