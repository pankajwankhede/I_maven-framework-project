# spring-cache-example

基于注解(@Cacheable)的spring cache

需要使用`<cache:annotation-driven />`或`@EnableCaching`开启缓存

### 1. 使用ehcache

```
<bean id="cacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager" p:cache-manager-ref="ehcache"/>

<bean id="ehcache" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean" p:config-location="ehcache.xml"/>
```

### 2. 使用spring内置的默认实现基于JDK的ConcurrentMap

```
<bean id="cacheManager" class="org.springframework.cache.support.SimpleCacheManager">
    <property name="caches">
    <set>
        <bean class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean" p:name="default"/>
        <bean class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean" p:name="employeeCache"/>
    </set>
    </property>
</bean>
```

### @Cacheable、@CachePut、@CacheEvict 注释介绍

@Cacheable 的作用	主要针对方法配置，能够根据方法的请求参数对其结果进行缓存 

@Cacheable 主要的参数


属性 | 描述 | 示例
--- | --- | ---
value 		|	缓存的名称，在 spring 配置文件中定义，必须指定至少一个 									  	|	例如：@Cacheable(value=”mycache”) 或者 @Cacheable(value={”cache1”,”cache2”}
key 		|	缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合   	|	例如：@Cacheable(value=”testcache”,key=”#userName”)
condition 	|	缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存 			   	|	例如：@Cacheable(value=”testcache”,condition=”#userName.length()>2”)



@CachePut 作用和配置方法

@CachePut 的作用	主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用 

@CachePut 主要的参数


属性 | 描述 | 示例
--- | --- | ---
value 		|	缓存的名称，在 spring 配置文件中定义，必须指定至少一个 										|	例如：@Cacheable(value=”mycache”) 或者 @Cacheable(value={”cache1”,”cache2”}
key 		|	缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合 	|	例如：@Cacheable(value=”testcache”,key=”#userName”)
condition 	|	缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存 				|	例如：@Cacheable(value=”testcache”,condition=”#userName.length()>2”)


@CacheEvict 作用和配置方法

@CachEvict 的作用	主要针对方法配置，能够根据一定的条件对缓存进行清空 

@CacheEvict 主要的参数


属性 | 描述 | 示例
--- | --- | ---
value 				|	缓存的名称，在 spring 配置文件中定义，必须指定至少一个 																			|	例如：@CachEvict(value=”mycache”) 或者 @CachEvict(value={”cache1”,”cache2”}
key 				|	缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合 										|	例如：@CachEvict(value=”testcache”,key=”#userName”)
condition 			|	缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才清空缓存 													|	例如：@CachEvict(value=”testcache”,condition=”#userName.length()>2”)
allEntries 			|	是否清空所有缓存内容，缺省为 false，如果指定为 true，则方法调用后将立即清空所有缓存 													|	例如：@CachEvict(value=”testcache”,allEntries=true)
beforeInvocation 	|	是否在方法执行前就清空，缺省为 false，如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存 		|	例如：@CachEvict(value=”testcache”，beforeInvocation=true) 



