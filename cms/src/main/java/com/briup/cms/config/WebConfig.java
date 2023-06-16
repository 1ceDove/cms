package com.briup.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.briup.cms.web.interceptor.JwtInterceptor;

/**
 * 1.配置拦截器 2.解决跨域问题
 * 
 * @author
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	// 1.配置拦截器
	/**
	 * 将自定义拦截器作为Bean写入配置
	 * 
	 * @return
	 */
	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截路径可自行配置多个 可用 ，分隔开
		// 设置自定义拦截器
		registry.addInterceptor(jwtInterceptor())
				// 拦截所有路径
				.addPathPatterns("/**")
				// 放行哪些请求
				.excludePathPatterns("/index/**", "/swagger-resources/**", "/v2/**", "/swagger-ui.html", "/webjars/**",
						"/user/login");
	}

	// 解决跨域问题
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 指哪些接口URL需要增加跨域设置
		registry.addMapping("/**")
				// 指的是前端哪些域名被允许跨域
				// .allowedOrigins("*")
				.allowedOriginPatterns("*")
				// 指的是允许哪些方法
				.allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
				// 指的是允许哪些请求头
				.allowedHeaders("*")
				// 项目需要带凭证
				.allowCredentials(true)
				// 准备响应前的 缓存持续的最大时间
				.maxAge(3600);
	}

}
