package java.com.sso.entity.auto.dao.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sso.entity.auto.dao.ErrorMessageDao;
import com.sso.entity.auto.model.ErrorMessage;
import com.sso.entity.auto.model.ErrorMessageExample;
import com.sso.yt.commons.utils.DateUtils;

/**
 * Created by yt on 2017/7/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml" })
public class ErrorMessageDaoImplTest {
    @Autowired
    private ErrorMessageDao errorMessageDao;
    

    @Test
    public void insetTest(){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setErrorCode(1000);
        errorMessage.setErrorMessage("错误信息");
        errorMessage.setModule(1000);
        errorMessage.setModuleName("测试");
        errorMessageDao.insert(errorMessage);
    }

    @Test
    public void updateTest(){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setErrorCode(1000);
        errorMessage.setErrorMessage("错误信息");
        errorMessage.setModule(1000);
        errorMessage.setModuleName("测试");
        errorMessage.setCreator("test");
        errorMessage.setIsDisplay(1);
        errorMessage.setLastTime(DateUtils.getDateFor59(new Date()));
        ErrorMessageExample errorMessageExample=new ErrorMessageExample();
        errorMessageExample.createCriteria().andErrorCodeEqualTo(errorMessage.getErrorCode());
        errorMessageDao.updateByExampleSelective(errorMessage,errorMessageExample);
    }

    @Test
    public void updateByKeyTest(){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setId(555);
        errorMessage.setIsDisplay(0);
        errorMessage.setLastTime(DateUtils.getDateFor59(new Date()));
        errorMessageDao.updateByPrimaryKeySelective(errorMessage);
    }

}