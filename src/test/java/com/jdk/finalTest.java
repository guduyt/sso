package com.jdk;

import org.junit.Test;

import com.sso.entity.auto.model.ErrorMessage;
import com.sso.yt.commons.utils.BeanCopyUtils;

/**
 * Created by yt on 2017-9-7.
 */
public class finalTest {
	final ErrorMessage fErrorMessage= new ErrorMessage();

	@Test
	public void finalClassTest(){
		final String string;
		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setId(1);
		errorMessage.setErrorMessage("test");
		fErrorMessage.setId(2);
		BeanCopyUtils.copy(errorMessage,fErrorMessage);
		fErrorMessage.getErrorMessage();
	}

}
