package com.bravo.demo.ssm.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.bravo.demo.ssm.dao.UserDao;
import com.bravo.demo.ssm.entity.User;
import com.bravo.demo.ssm.service.UserService;

@Service ("userService")
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	/*
	 * @Cacheable 标注的方法执行之前先检查缓存中有没有这个数据，默认按照参数的值作为key去查缓存。
	 * 如果没有就运行方法并将结果放入缓存。
	 */
	@Override
	@Cacheable(key = "#root.methodName")
	public List<User> listAllUser() {
		return userDao.getAllUser();
	}

	/*
	 * @Cacheable 的运行流程
	 * 1. 方法运行之前，先去查询Cache，按照cacheNames指定的名称获取。CacheManager先获取相应的Cache，
	 * 	第一次获取Cache组件，如果没有会自动创建。
	 * 2. 去Cache查找缓存的内容，使用一个key，默认就是方法参数。key是按照某种策略生成的，默认是使用
	 * 	SimpleKeyGenerator生成。
	 * 		SimpleKeyGenerator生成Key的默认策略：
	 * 			1).如果没有参数，key = new SimpleKey()
	 * 			2).如果有一个参数，key = 参数值
	 * 			3).如果有多个参数，key = new SimpleKey(params)
	 * 3. 没有查询到缓存就调用目标方法。
	 * 4. 将目标方法返回的结果，放进缓存。
	 */
	@Override
	@Cacheable(key = "#userId") //在方法之前执行
	public User getUserById(String userId) {
		logger.debug("Calling getUserById:{}", userId);
		return userDao.getUserById(userId);
	}

	@Override
	@Cacheable(key = "#username")
	// 查询的时候是只用@Cacheable缓存，在创建或更新的时候再使用组合操作更新多个缓存
	/*
	 * 貌似只能缓存下key为id的，key为username的不能缓存，始终会去查数据库。？？？
	 * 其实，数据已经正常缓存了，而且也能从缓存中取到。始终会调目标方法（去查数据库），
	 * 是因为标注了@CachePut，需要在目标方法执行之后对结果进行缓存。所以调用该方法的时候
	 * 仍然每次都会查询数据库，感觉好像数据没有被缓存一样。因此个人感觉在查询的时候是只用@Cacheable缓存，
	 * 在创建或更新的时候再使用组合操作更新多个缓存的数据
	 */
//	@Caching(
//		cacheable= {
//			@Cacheable(key = "#username")
//		},
//		put = {
//			@CachePut(key = "#result.username"), 
//			@CachePut(key = "#result.id")
//		}
//	)
	public User getUserByName(String username) {
		logger.debug("Calling getUserByName:{}", username);
		return userDao.getUserByName(username);
	}
	
	@Override
	//@CachePut(key = "#user.id") //先调用目标方法，然后将返回的结果缓存起来
	@Caching(
		put = {@CachePut(key = "#result.username"), @CachePut(key = "#result.id")}
	)
	public String createUser(User user) {
		userDao.insertUser(user); 
		return user.getId(); //id已回填
	}

	@Override
	//allEntries=true:删除缓存中的所有数据，beforeInvocation=false:默认是在方法之后执行清除缓存
	//TODO 这里还应该清除key为username的缓存
	@CacheEvict(key = "#userId", beforeInvocation = false, allEntries = false)
	public int delByUserId(String userId) {
		return userDao.delUserById(userId);
	}
	
}
