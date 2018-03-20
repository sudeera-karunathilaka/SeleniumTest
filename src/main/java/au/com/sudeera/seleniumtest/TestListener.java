package au.com.sudeera.seleniumtest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("** Test failure - " + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("** Test skipped - " + result.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("** Test start - " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("** Test success - " + result.getName());
	}

}
