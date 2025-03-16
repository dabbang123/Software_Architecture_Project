package com.sa.sls.loanoffer.factory;

import org.springframework.stereotype.Component;

/*
 * Author: Rushabh Botadra
 */
import com.sa.sls.loanoffer.decorator.GoldMemberShipDecorator;
import com.sa.sls.loanoffer.decorator.IInterestRate;
import com.sa.sls.loanoffer.decorator.NormalInterestRate;
import com.sa.sls.loanoffer.decorator.SilverMemberShipDecorator;
import com.sa.sls.membershiptype.MembershipType;

@Component
public class InterestRateFactory {
	
	public IInterestRate getInterestRateFactory(String membershipType)
	{
		if(membershipType.equals(MembershipType.GOLD.getMembershipType()))
		{
			return new GoldMemberShipDecorator(new NormalInterestRate());
		}
		else if(membershipType.equals(MembershipType.SILVER.getMembershipType()))
		{
			return new SilverMemberShipDecorator(new NormalInterestRate());
		}
		else if(membershipType.equals(MembershipType.REGULAR.getMembershipType()))
		{
			return new NormalInterestRate();
		}
		else
		{
			throw new IllegalArgumentException("Unexpected value: " + membershipType);
		}
	}

}
