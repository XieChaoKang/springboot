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
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLEncoder;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private RedisTemplate<String,String> redisTemplate;
    //SpringBoot 默认日志slf4j 已配置好 直接使用
	Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

	public void testJedis(){
		System.out.println("============");
		ValueOperations<String, String> operations = redisTemplate.opsForValue();
		System.out.println(operations.get("k1"));
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
    public  void test() throws Exception{
		String username = "19";
		String pwd = "1234";
		//0cd44355fd830ba2150f68d77f2d2269
		System.out.println(ByteSource.Util.bytes("19")+"\n");
		System.out.println(new SimpleHash("MD5", pwd, username, 2).toString());
//		System.out.println("MD5+盐两次加密后的密码"+ new SimpleHash("MD5","1234",username,2));
		//60ced2479e5530acb6a7e61192fb216b
	}

}
