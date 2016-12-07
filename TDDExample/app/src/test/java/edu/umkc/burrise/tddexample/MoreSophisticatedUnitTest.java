package edu.umkc.burrise.tddexample;

import android.util.Log;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * This example demonstrates the @Before
 *   and @After attributes.
 */
// http://stackoverflow.com/questions/3693626/how-to-run-test-methods-in-specific-order-in-junit4
// If you want tests to run in a certain order,
// add the following. It will sort the test
// methods by the method name, in lexicographic order,
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MoreSophisticatedUnitTest {

    // Using StringBuffer because when I tried
    // System.out.println statements they did not
    // always print in teh correct order. Threading?
    private static StringBuffer output;

    // Method can have any name. Attribute @BeforeClass
    //   causes the method to execute once before all
    //   test cases run.
    @BeforeClass
    public static void setUpBeforeClass() {
        // Note, can't use something like
        //   Log.i(TAG, setUpBeforeClass()");
        //   here because android classes such as Log
        //   declared but don't have any behavior.
        //   Calling Log.i() would cause a "method
        //   not implemented error.
        //Log.i("abc", "setUpBeforeClass()");
        //Log a;
        output = new StringBuffer("setUpBeforeClass()");
    }

    @Before
    public void setUp() {
        output.append("\nsetUp()");
    }

    // Simple unit test. No dependencies.
    @Test
    public void verifyCreditGrantedForHighScore() throws Exception {
        output.append("\nverifyCreditGrantedForHighScore()");

        CreditRatingModel model = new CreditRatingModel(800);
        Assert.assertTrue("High credit score yet credit denied",
                model.grantCredit());
    }

    // Simple unit test. No dependencies.
    @Test
    public void verifyCreditDeniedForLowScore() throws Exception {
        output.append("\nverifyCreditDeniedForLowScore()");

        CreditRatingModel model = new CreditRatingModel(1);
        Assert.assertFalse("Low credit score yet credit granted",
                model.grantCredit());
    }

    @After
    public void tearDown() {
        output.append("\ntearDown()");
    }

    // Method can have any name. Attribute @AfterClass
    //   causes the method to execute once after all
    //   test cases have ran.
    @AfterClass
    public static void tearDownAfterClass() {
        output.append("\ntearDownAfterClass()");
        System.out.println(output);
    }
}
