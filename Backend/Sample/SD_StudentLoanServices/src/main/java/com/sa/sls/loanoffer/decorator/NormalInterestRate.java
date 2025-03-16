package com.sa.sls.loanoffer.decorator;

/*
 * Author: Rushabh Botadra
 */
public class NormalInterestRate implements IInterestRate{

	@Override
	public double getRate() 
	{
		return 10.0;
	}
}
