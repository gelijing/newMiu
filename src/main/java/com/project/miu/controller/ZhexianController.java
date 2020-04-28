package com.project.miu.controller;

import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import javafx.beans.binding.ObjectExpression;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ZhexianController {

    @GetMapping("/html")
    public String index(){
        return "/index.html";
    }

    @RequestMapping(value = "/showZhexian",method = {RequestMethod.GET})
    public Result showZhexian(Integer digit){
        String fileName = "/Users/gelijing/workspace/JavaProjects/000000_0";
        Map map = toArrayByInputStreamReader(fileName,digit);
        System.out.println(map.toString());
        return ResultUtil.success(map);
    }

    public Map toArrayByInputStreamReader(String name,Integer digit) {
        // 使用ArrayList来存储每行读取到的字符串
        HashMap<Double,Integer> hashMap = new HashMap<Double, Integer>();
        List<Double> list = new ArrayList<Double>();
        Map<Double,String> ipMap = new HashMap<>();
        Map<Double,String> ipMapSub = new HashMap<>();
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        try {
            File file = new File(name);
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                // 2、获取每一个单词
                String[] words = str.split("\\.");
                double val=0;
                for(int i=0;i<words.length;i++)
                {
                    val+=Long.parseLong(words[i])*Math.pow(2,8*(3-i));
                }
                min = Math.min(val,min);
                max = Math.max(val,max);
                list.add(val);
                ipMap.put(val,str);
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Double res : list){
            double res0 = (res - min)/(max-min);
            res0 = setscale(res0,digit);
            Integer orDefault = hashMap.getOrDefault(res0, 0);
            hashMap.put(res0,++orDefault);
            ipMapSub.put(res0,ipMap.get(res));
        }
        Set<Double> doubles = hashMap.keySet();
        Object[] objects = doubles.toArray();
        Arrays.sort(objects);
        List<Double> keys = new ArrayList<>();
        List<String> ipkeys = new ArrayList<>();

        List<Integer> values = new ArrayList<>();
        for(Object dou : objects){
            keys.add((Double) dou);
            ipkeys.add(ipMapSub.get(dou));
            values.add(hashMap.get(dou));
        }
        HashMap<String,Object> result = new HashMap<>();
        result.put("xAxis",ipkeys);
        result.put("yAxis",values);
        return result;
    }
    public double setscale(double f,Integer digit) {
        BigDecimal bg = new BigDecimal(f);
        /**
         * 参数：
         newScale - 要返回的 BigDecimal 值的标度。
         roundingMode - 要应用的舍入模式。
         返回：
         一个 BigDecimal，其标度为指定值，其非标度值可以通过此 BigDecimal 的非标度值乘以或除以十的适当次幂来确定。
         */
        double f1 = bg.setScale(digit, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
}
