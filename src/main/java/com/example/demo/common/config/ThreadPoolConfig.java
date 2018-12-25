package com.example.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 *
 * @author bowensun
 * @since 2018/7/27
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean(value = "taskExecutor")
    public Executor taskExcutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setKeepAliveSeconds(60);
        //谁否允许销毁核心线程，true最后会销毁到0个
        taskExecutor.setAllowCoreThreadTimeOut(false);
        taskExecutor.setThreadNamePrefix("taskExcutor_");
        //线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，
        // 当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；
        // 如果执行程序已关闭，则会丢弃该任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }

}
