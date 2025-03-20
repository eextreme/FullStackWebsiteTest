package com.testng_selenium;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryMech implements IRetryAnalyzer {
    private int count = 0;
    private static final int MAX_TRIES = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_TRIES) {
            count++;
            return true;
        }
        return false;
    }
}