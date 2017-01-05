"# SpringBootMybatisExample" 


##主要知识点
####1.从resource文件导入资源  
 
* 用一个`AppConfig.java`写读取和载入资源文件的方法  
* 然后在`ApplicationMain.java`中调用并读入文件  
* 再调用`SpringApplicationBuilder()`这个方法启动项目  
需要引入`BaseConfiguration`的Dependency:  
```  
	<dependency>
		<groupId>commons-configuration</groupId>
		<artifactId>commons-configuration</artifactId>
		<version>1.9</version>
	</dependency>  
```

####2.配置Mybatis，并用注解的方式操作数据库的主要步骤  
* 编写`POJO`/`Entity`基础类
* 设计`IRecommendUserDao`接口
* 实现Maytis的`Mapper`文件，在`Mapper`中以注解的方式操作数据库  
	public interface RecommendUserMapper {  
		@Select("SELECT * FROM recommend_user")  
		List<RecommendUser> getAll();  
* 实现`IRecommendUserDao`接口,并在其实现类`RecommendUserDaoImpl`中调用`Mapper`中的方法，其中需要`SqlSessionFactory`来打开一个回话，并用`SqlSession`来获得`Mapper`  
```  
	public RecommendUserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		super();
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private RecommendUserMapper getMapper(SqlSession sqlSession) {
		return sqlSession.getMapper(RecommendUserMapper.class);
	}
	
	@Override
	public List<RecommendUser> getAll() {
		try(SqlSession session = sqlSessionFactory.openSession(true)) {
			RecommendUserMapper mapper = getMapper(session);
			return mapper.getAll();
		}
	}  
```  
***这里把SqlSession放在try里是因为用这种方法，在回话结束之后，它会自己关闭Session***  
* 编写一个`Util`类来获取`SqlSessionFactory`,并将所有`Mapper`在其中进行注册  
```  
	public class MybatisSessionUtil {  
		public static SqlSessionFactory getSessionFactory(DataSource dataSource) {  
			TransactionFactory transactionFactory = new JdbcTransactionFactory();  
			Environment environment = new Environment("development", transactionFactory, dataSource);  
			Configuration configuration = new Configuration(environment);  
			//TODO:Remember all mappers should register here.  
			configuration.addMapper(RecommendUserMapper.class);  
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);  
			return sqlSessionFactory;
		}
	}  
```
* 可以编写一个`DataSourceFactory`来获取一个数据源流对象  
* 然后编写`AppConfig`，用`@Configuration @EnableScheduling``@Bean`来载入`DataSource`或者叫做`Inject`dataSource。当然这个步骤可以自己设计  

其中需要的Dependency有：  
- Mybatis  
```
	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis</artifactId>
		<version>3.3.0</version>
	</dependency>  
```
- MySQL连接 
``` 
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>5.1.38</version>
	</dependency> 
``` 
- Spring JDBC  
```  
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-jdbc</artifactId>
		<version>4.3.5.RELEASE</version>
	</dependency>
```  
- Tomcat 连接池  
```
	<dependency>
		<groupId>org.apache.tomcat</groupId>
		<artifactId>tomcat-jdbc</artifactId>
		<version>8.5.9</version>
	</dependency>  
```
- 测试用的嵌入式数据库H2  
```  
<dependency>  
	<groupId>com.h2database</groupId>  
	<artifactId>h2</artifactId>
	<scope>test</scope>
</dependency>
```

####3.引入Swagger Test API
* 编写了`SwaggerConfig.java`来配置相关属性  
* 用`@Configuration @EnableSwagger2`让这个配置类自动装载  
* 在`Controller`中直接进行使用  
* 可以用`@ApiOperation(value = "Get all recommended user's list")`来解释某个方法的作用，这句话会出现在界面上  
* 所需引入的Depend的Dependency  
```  
	<dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger-ui</artifactId>
		<version>${swagger.version}</version>
		<scope>compile</scope>
	</dependency>

	<dependency>  
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger2</artifactId>		<version>${swagger.version}</version>
		<scope>compile</scope>
	</dependency>  
```  
* 然后就可以通过`http://localhost:8080/swagger-ui.html`访问了  
  
####4.编写了一个`ResponseWrapper`，在`Controller`和`Service`中通过调用这个工具类，将`Response`的消息进行格式化返回  
  
####5.用`Junit`编写测试类  
* 用H2数据库来模拟真实数据，导入schema和scripts来建立这个数据库  
* 为每个方法添加test cases  
* 文件结构如下  
![FileOrderForJunit](https://github.com/GavinLee1/SpringBootMybatisExample/blob/master/src/main/resources/FileOrderForTest.png)  

				