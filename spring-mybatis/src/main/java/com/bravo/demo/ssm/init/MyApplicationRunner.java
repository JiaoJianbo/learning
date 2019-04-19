package com.bravo.demo.ssm.init;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class MyApplicationRunner implements ApplicationRunner {
	
	private static Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);

	/*
	 * 与 org.springframework.boot.CommandLineRunner 不同的是，这里传入的参数可以带key。
	 * 如：java -jar devtools-0.0.1-SNAPSHOT.jar 三国演义 西游记 --age=99
	 * 1. args.getNonOptionArgs();可以用来获取命令行中的无key参数（和CommandLineRunner一样）。
	 * 2. args.getOptionNames();可以用来获取所有key/value形式的参数的key。--key=value 的形式。
	 * 3. args.getOptionValues(key));可以根据key获取key/value 形式的参数的value。
	 * 4. args.getSourceArgs(); 则表示获取命令行中的所有参数。
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<String> nonOptionArgs = args.getNonOptionArgs(); //三国演义 西游记
		logger.debug("nonOptionArgs: {}", nonOptionArgs);
		Set<String> optionNames = args.getOptionNames(); //
		//List<String> optionValues = args.getOptionValues("foo");
		for(String optionName : optionNames) {
			logger.debug("{} = {}", optionName, args.getOptionValues(optionName).get(0));
		}
		
		String[] sourceArgs = args.getSourceArgs();
		logger.debug("sourceArgs: {}", Arrays.toString(sourceArgs)); //三国演义 西游记 --age=99
	}

}
