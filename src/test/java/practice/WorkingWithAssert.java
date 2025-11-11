package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkingWithAssert {
	
	@Test
	//Hard Assert
	public void test() {
		System.out.println("start");
		//Assert.assertEquals("swapnil", "swapnil");
		Assert.assertNotEquals("swapnil", "swapnil");
		System.out.println("end");
	}
	
@Test
	//Soft Assert
	public void test1() {  
		System.out.println("start");
		SoftAssert soft=new SoftAssert();
		//soft.assertEquals("swapnil", "swapnil1");
		soft.assertNotEquals("swapnil", "swapnil1");
		System.out.println("end");
		soft.assertAll();
	}

@Test
//Hard Assert
public void test2() {
	System.out.println("start");
	//Assert.assertTrue("swapnil".equals("swapnil"));
	Assert.assertFalse("swapnil".equals("swapnil"));
	System.out.println("end");
}

@Test
//Soft Assert
public void test3() {  
	System.out.println("start");
	SoftAssert soft=new SoftAssert();
	//soft.assertTrue("swapni;".equals("swapni"));
	soft.assertFalse("swapni;".equals("swapni"));
	System.out.println("end");
	soft.assertAll();
}

@Test

public void test4() {
	System.out.println("start");
	//String s=null;
	String s="swapnil";
	//Assert.assertTrue("swapnil".equals("swapnil"));
	Assert.assertNull(s);
	System.out.println("end");
}

@Test

public void test5() {
	System.out.println("start");
	//String s=null;
	String s="swapnil";
	//Assert.assertTrue("swapnil".equals("swapnil"));
	Assert.assertNotNull(s);
	System.out.println("end");
}

}
