package com.example.demo.apec.Job;

import com.example.demo.apec.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ronaldo
 * @version 2.0
 * @date 2019/6/19 9:47
 */
@Slf4j
@Component
@DisallowConcurrentExecution//此注解是用在Job的实现类上面，意思是不允许并发访问，注意org.quartz.threadPool.threadCount线程池中的线程数量至少要多个，否则慈竹姐不生效怕
//假如job的设置时间间隔为3秒，但Job执行时间是5秒设置@DisallowConcurrentExecution则会等任务执行完毕后再执行，否则会新建线程去处理吧
public class DynamicJob implements Job {


    /**
     * Job任务的核心方法，Quartz真正的执行逻辑
     * @param jobContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        //JobDetail中的JobDataMap是共用的，从getMergedJobDataMap获取JobDataMap是新的对象
        JobDataMap map = jobContext.getMergedJobDataMap();
        String jarPath = map.getString("jarPath");
        String parameter = map.getString("parameter");
        String vmParam = map.getString("vmParam");
        log.info("Running Job name:{},Running Job description : {},Running Job group: {} "
                ,map.getString("name"),map.getString("jobDescription"),map.getString("group"));
        log.info(String.format("Running Job cron:%s",map.getString("cronExpression")));
        log.info("Running Job jar path : {} ", jarPath);
        log.info("Running Job parameter : {} ", parameter);
        log.info("Running Job vmParam : {} ", vmParam);

        long startTime = System.currentTimeMillis();
        if(!StringUtils.isEmpty(jarPath)){
            File file = new File(jarPath);
            if(file.exists()){
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.directory(file.getParentFile());
                List<String> commands = new ArrayList<>();
                commands.add("jar");
                if(!StringUtils.isEmpty(vmParam)){
                    commands.add("-jar");
                    commands.add(jarPath);
                }
                if (!StringUtils.isEmpty(parameter)){
                    commands.add(parameter);
                }
                processBuilder.command(commands);
                log.info("Running Job Detail As Follows>>>>>>>>>>>>>>>>>>>>>>>");
                log.info("Running Job Command:{}", StringUtil.getListString(commands));
                try {
                    Process process = processBuilder.start();
                    logProcess(process.getInputStream(),process.getErrorStream());
                } catch (IOException e) {
                    throw new JobExecutionException();
                }


            }else {
                throw new JobExecutionException("Job jar is not found>>>"+jarPath);
            }

            log.info(">>>>>>>>>>>>>>>>>>>>>>>>Running Job has been completed,cost time:{}ms\n",(System.currentTimeMillis()-startTime));
        }

    }

    //记录Job执行内容
    private void logProcess(InputStream inputStream, InputStream errorStream) throws IOException {

        String inputLine;
        String errorLine;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

        while (Objects.isNull(inputLine = inputReader.readLine())){
            log.info(inputLine);
        }

        while (Objects.isNull(errorLine = errorReader.readLine())){
            log.error(errorLine);
        }






    }
}
