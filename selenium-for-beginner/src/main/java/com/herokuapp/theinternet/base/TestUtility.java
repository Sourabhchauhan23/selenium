package com.herokuapp.theinternet.base;

public class TestUtility extends BaseTest {

	protected void sleep(int m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
