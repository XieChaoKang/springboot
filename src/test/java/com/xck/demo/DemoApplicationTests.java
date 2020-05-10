package com.xck.demo;

import com.xck.demo.Model.Permission;
import com.xck.demo.Service.Shiro_Service.Shiro_ServiceImpl.GetPermissionServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLEncoder;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    //SpringBoot 默认日志slf4j 已配置好 直接使用
	Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Test
	public void contextLoads() {
	}

	@Test
	public void testLogger(){
	    //级别从低到高
        //SpringBoot 默认级别info 也就是info级别前的不打印 后的打印
	    logger.trace("trace");
	    logger.debug("debug");
	    logger.info("info");
	    logger.warn("warn");
	    logger.error("error");
    }

    @Test
    public void testpwd() throws Exception{
		ByteSource s = ByteSource.Util.bytes("19");
		System.out.println("盐值："+s);
		String pwd = URLEncoder.encode("123","utf-8");
		String s1 = URLEncoder.encode("MTcwODAxMDEzOA==","utf-8");
		String s2 = URLEncoder.encode("MTcwODAxMDEzOA==","GBK");
		System.out.println("MD5+盐两次加密后的密码"+ new SimpleHash("md5","11",s,2).toString()+"  "+s+"\n");
		System.out.println(new SimpleHash("md5",pwd,s1,2).toString()+"\n");
		System.out.println(new SimpleHash("md5",pwd,s2,2).toString()+"\n");
		System.out.println(new SimpleHash("md5",pwd,"MTk=",2).toString()+"\n");//
		System.out.println(new SimpleHash("md5","123","MTk=",2).toString()+"\n");//
		System.out.println(new SimpleHash("md5","123",s1,2).toString()+"\n");
	}

}
