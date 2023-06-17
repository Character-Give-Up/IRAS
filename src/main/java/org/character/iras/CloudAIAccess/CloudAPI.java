package org.character.iras.CloudAIAccess;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

/**
 * 云端API接口。所有需要同url调用的服务的驱动类<b>均需实现此接口</b>。
 */
public interface CloudAPI {
    /**
     * 设置请求路径的前缀
     * 这是一个可选的实现，对于某些通过SDK调用的云端API来说，通常不需要设定前缀
     * @param prefix 请求路径的前缀
     */
    default void setPathPrefix(String prefix){
    }

    /**
     * 设置请求路径的前缀
     * 这是一个可选的实现，对于某些通过SDK调用的云端API来说，通常不需要设定前缀
     */
    default String getPathPrefix(){
        return null;
    }

    /**
     * 设置请求路径
     * 这是一个可选的实现，对于某些通过SDK调用的云端API来说，通常不需要设定请求路径
     * @param path 请求路径
     */
    default void setPath(String path){
    }

    /**
     * 设置请求路径
     * 这是一个可选的实现，对于某些通过SDK调用的云端API来说，通常不需要设定请求路径
     */
    default String getPath(){
        return null;
    }

    /**
     * 增加请求参数
     * @param key 请求参数字段名称
     * @param value 请求参数的参数值
     */
    void putParameter(String key, Object value);

    /**
     * 批量增加请求参数
     * @param params 以键值对形式新增一些请求参数
     */
    void putParameters(Map<String, Object> params);

    /**
     * 回调云端API，并以JSON格式返回回调的结果
     * @return JSON格式的回调结果
     */
    JSONObject call();
}
