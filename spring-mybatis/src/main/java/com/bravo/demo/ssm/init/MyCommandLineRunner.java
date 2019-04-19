package com.bravo.demo.ssm.init;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class MyCommandLineRunner implements CommandLineRunner {
	private static Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);

	/*
	 * 参数 args 是来自于项目的启动参数，即项目入口类中，main方法的参数会被传到这里。
	 * 如：java -jar devtools-0.0.1-SNAPSHOT.jar 三国演义 西游记 
	 * 这里参数传递时没有key, 即接收到的值就是 [三国演义, 西游记]
	 * 如果参数要带key，参照 @see org.springframework.boot.ApplicationRunner
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.debug("MyCommandLineRunner参数：{}", Arrays.deepToString(args));
	}

}
