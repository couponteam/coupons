package com.masou.coupon.config;



import com.masou.coupon.utils.CheckMobile;
import com.masou.coupon.utils.IPUtil;
import com.masou.coupon.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Paul on 2017/2/27.
 */
@Configuration
public class WebInterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private CheckMobile checkMobile;
    @Autowired
    private IPUtil ipUtil;

    @Autowired
    private MD5Util md5Util;



    /**
     * 允许跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        // 用于日志记录

//		 优化跳转
//        registry.addInterceptor(new RedirectInterceptor())
//                .addPathPatterns("/**");
        // 用于在各个视图之间共享特定的数据
//        registry.addInterceptor(new GlobalComposerInterceptor(pageService, authService)).addPathPatterns("/**");




		// sign校验
//		registry.addInterceptor(new CheckSignInterceptor(md5Util))
//				.addPathPatterns(
//						"/api/**"
//				);

		// token 校验
//		registry.addInterceptor(checkTokenInterceptor)
//				.addPathPatterns(
//						"/api/**",
//                        "/management/api/**"
//				);

        // 管理后台权限验证
//        registry.addInterceptor(managementAuthInterceptor)
//                .addPathPatterns("/management/api/admin/**")
//                .excludePathPatterns("/management/api/admin/manager/login");

		// 登陆 校验
//		registry.addInterceptor(loginCheckInterceptor)
//                .addPathPatterns("/api/user/**")
//                .excludePathPatterns("/api/user/token/create",
//                        "/api/user/login",
//                        "/api/user/register",
//                        "/api/user/forgetPassword"
//
//                );
    }

}
