package com.example.demo.web;


import com.example.demo.common.properties.TestProperties;
import com.example.demo.domain.GoodsInfoVo;
import com.example.demo.domain.SysArea;
import com.example.demo.domain.User;
import com.example.demo.service.TestService;
import com.example.demo.service.impl.AsyncTask;
import com.example.demo.web.support.BaseController;
import com.example.demo.web.support.Result;
import com.example.demo.web.support.Success;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试控制器
 *
 * @author bowensun
 */
@RestController
public class TestController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    static RestTemplate restTemplate = new RestTemplate();

    static String url = "http://api.yogovi.vip/api/b/1030";

    static String url2 = "";

    @Autowired
    private TestService testService;
    @Autowired
    private TestProperties testProperties;
    @Autowired
    private AsyncTask asyncTask;

    /**
     * 异步调用测试
     *
     * @return
     */
    @PostMapping("/task")
    public Result taskTest(){
        System.out.println("异步调用测试开始");
        testService.taskTask();
        System.out.println("异步调用测试结束");
        return new Success(true);
    }

    /**
     * 测试properties属性获取
     *
     * @return
     */
    @RequestMapping("helloWorld")
    public String helloWorld(){
        System.out.println("进入测试");
        System.out.println(testProperties.getAuthor());
        System.out.println(testProperties.getDesc());
        return "helloWorld";
    }

    @RequestMapping(value = "rest", method = RequestMethod.POST)
    public String restTest(){
        logger.info("测试restTemplate方法");
        //getForObject();
        //postForObject();
        //getEntity();
        //postForEntity();
        //exchange();
        //postForLocation(); 返回uri,但是测试一直为null
        //put(); 返回值为空
        //patch 类似于get但是可以指定请求方法，
        //Success success = restTemplate.patchForObject(url, HttpMethod.POST, Success.class);
        // head请求 返回值为header请求头 // HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
        // delete restTemplate.delete(url);
        //restTemplate.execute(url, HttpMethod.POST,);
        postEntity();
        return "成功";
    }

    /**
     * 测试文件上传
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public Result uoloadTest(MultipartFile file){
        logger.info("测试上传文件开始");
        testService.upload(file);
        logger.info("测试上传文件结束");
        return new Success("ok");
    }

    /**
     * 测试文件下载
     *
     * @param filepath
     * @param response
     * @return
     */
    @RequestMapping("download")
    public Result downloadTest(HttpServletResponse response, HttpServletRequest request, String filepath){
        logger.info("测试上传文件开始");
        testService.download(response, request, filepath);
        logger.info("测试上传文件结束");
        return new Success("ok");
    }

    /**
     *
     *
     */
    private void put() {
        String url = "http://api.yogovi.vip/api/a/51"+
                "?apiId={apiId}&apiName={apiName}&createUid={createUid}&requestUri={requestUri}&serviceType={serviceType}";
        Map<String, String> map = new HashMap<>();
        map.put("apiId", "123456");
        map.put("apiName", "测试接口");
        map.put("createUid", "bowensun");
        map.put("requestUri", "test/test");
        map.put("serviceType","1");
        restTemplate.put(url, map);
    }

    /**
     * postLocation，返回新增资源的uri
     *
     * @return
     */
    private void postForLocation() {
        String url = "http://api.yogovi.vip/api/a/51"+
                "?apiId={apiId}&apiName={apiName}&createUid={createUid}&requestUri={requestUri}&serviceType={serviceType}";
        Map<String, String> map = new HashMap<>();
        map.put("apiId", "123456");
        map.put("apiName", "测试接口");
        map.put("createUid", "bowensun");
        map.put("requestUri", "test/test");
        map.put("serviceType","1");
        URI uri = restTemplate.postForLocation(url, HttpMethod.POST, map);
        System.out.println(uri.toString());
    }

    /**
     * exchange方法
     *
     */
    private void exchange() {
        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, Integer> map = new LinkedMultiValueMap<>();
        map.set("goodsStatus", 1);
        HttpEntity<MultiValueMap<String, Integer>> multiValueMapHttpEntity = new HttpEntity<>(map);
        HttpEntity<MultiValueMap<String, Integer>> httpEntity = new HttpEntity(map, httpHeaders);
        //ResponseEntity<? extends TypeReference<List<GoodsInfoVo>>> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, new TypeReference<List<GoodsInfoVo>>() {
        //}.getClass());
        ResponseEntity<Success> exchange = restTemplate.exchange(url, HttpMethod.POST, multiValueMapHttpEntity, Success.class);
        //logger.info("请求方式为：", multiValueMapHttpEntity.getHeaders().getAccessControlRequestMethod().toString());
        Success success = exchange.getBody();
        if(success.getCode() == 200){
            Object data = success.getData();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result = objectMapper.writeValueAsString(data);
                List<GoodsInfoVo> goodsInfoVos = objectMapper.readValue(result, new TypeReference<List<GoodsInfoVo>>() {});
                for (GoodsInfoVo sysArea : goodsInfoVos) {
                    System.out.println(sysArea.getGoodsName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * post获取请求体
     *
     */
    private void postForEntity() {
        //Map<String, Integer> map = new HashMap<>();
        //map.put("goodsStatus", 1);
        //ResponseEntity<Success> entity = restTemplate.postForEntity(url, map, Success.class);
        MultiValueMap<String, Integer> map = new LinkedMultiValueMap<>();
        map.set("goodsStatus", 1);
        ResponseEntity<Success> entity = restTemplate.postForEntity(url, map, Success.class);
        Success success = entity.getBody();
        if(success.getCode() == 200){
            Object data = success.getData();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result = objectMapper.writeValueAsString(data);
                List<GoodsInfoVo> goodsInfoVos = objectMapper.readValue(result, new TypeReference<List<GoodsInfoVo>>() {});
                for (GoodsInfoVo sysArea : goodsInfoVos) {
                    System.out.println(sysArea.getGoodsName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * getEntity
     *
     */
    private void getEntity() {
        String newUrl = url+"?goodsStatus={goodsStatus}";
        Map<String, Integer> map = new HashMap<>();
        map.put("goodsStatus", 1);
        ResponseEntity<Success> entity = restTemplate.getForEntity(newUrl, Success.class, map);
        HttpStatus statusCode =entity.getStatusCode();
        HttpHeaders headers = entity.getHeaders();
        MediaType contentType = headers.getContentType();
        Success success = entity.getBody();
        if(success.getCode() == 200){
            Object data = success.getData();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result = objectMapper.writeValueAsString(data);
                List<GoodsInfoVo> goodsInfoVos = objectMapper.readValue(result, new TypeReference<List<GoodsInfoVo>>() {});
                for (GoodsInfoVo sysArea : goodsInfoVos) {
                    System.out.println(sysArea.getGoodsName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * post获取对象
     *
     */
    private void postForObject() {
        String newUrl = url+"?goodsStatus={goodsStatus}";
        //Map<String, Integer> map = new HashMap<>();
        //map.put("goodsStatus", 1);
        MultiValueMap<String, Integer> map = new LinkedMultiValueMap<>();
        map.set("goodsStatus", 1);
        Success success = restTemplate.postForObject(url, map, Success.class);
        //Success success = restTemplate.postForObject(newUrl, null, Success.class,map);
        int code = success.getCode();
        if(code == 200){
            Object data = success.getData();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result = objectMapper.writeValueAsString(data);
                List<GoodsInfoVo> goodsInfoVos = objectMapper.readValue(result, new TypeReference<List<GoodsInfoVo>>() {});
                for (GoodsInfoVo sysArea : goodsInfoVos) {
                    System.out.println(sysArea.getGoodsName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * get直接获取对象
     *
     */
    private void getForObject() {
        String newUrl = url+"?goodsStatus={goodsStatus}";
        Map<String, Integer> map = new HashMap<>();
        map.put("goodsStatus", 1);
        //Success success = restTemplate.getForObject(newUrl, Success.class, map);
        //MultiValueMap<String, Integer> map = new LinkedMultiValueMap<>();
        //map.set("goodsStatus", 1);
        Success success = restTemplate.getForObject(newUrl, Success.class, map);
        //Success success = restTemplate.getForObject(newUrl, Success.class, "320000");
        int code = success.getCode();
        if(code == 200){
            Object data = success.getData();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String result = objectMapper.writeValueAsString(data);
                List<GoodsInfoVo> goodsInfoVos = objectMapper.readValue(result, new TypeReference<List<GoodsInfoVo>>() {});
                for (GoodsInfoVo sysArea : goodsInfoVos) {
                    System.out.println(sysArea.getGoodsName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void postEntity(){
        String url = "http://api.yogovi.vip/api/a/67";
        MultiValueMap<String, Integer> map = new LinkedMultiValueMap<>();
        map.set("pid", 320000);
        ResponseEntity<Success> entity = restTemplate.postForEntity(url, map, Success.class);
        Success success = entity.getBody();

        if (success.getCode() == 200){
            Object data = success.getData();
            ObjectMapper mapper = new ObjectMapper();
            List<SysArea> sysAreaList = null;
            try {
                String string = mapper.writeValueAsString(data);
                sysAreaList = mapper.readValue(string, new TypeReference<List<SysArea>>() {
                });

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (SysArea sysArea : sysAreaList) {
                System.out.println(sysArea.getShortname());
            }
        }
    }



}
