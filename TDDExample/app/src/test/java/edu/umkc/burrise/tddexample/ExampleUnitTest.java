package edu.umkc.burrise.tddexample;

import org.junit.Test;

import org.junit.Assert;

/**
 * To work on unit tests, switch the Test Artifact in
 * the Build Variants view.
 */
public class ExampleUnitTest {
    // This is the default test that gets
    // generated with all android projects.
    @Test
    public void addition_isCorrect() throws Exception {
        Assert.assertEquals(4, 2 + 2);
    }

    // Simple unit test. No dependencies.
    @Test
    public void verifyCreditGrantedForHighScore() throws Exception {
        CreditRatingModel model = new CreditRatingModel(800);
        Assert.assertTrue("High credit score yet credit denied",
            model.grantCredit());
    }
}