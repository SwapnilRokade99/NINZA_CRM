package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkingWithIRetryAnalyzer {
	
	@Test(retryAnalyzer = genaricUtilities.IretryImplimentation.class)
	
	public void t1() {
		Assert.assertEquals("swapnil", "Nil");
	}

}
