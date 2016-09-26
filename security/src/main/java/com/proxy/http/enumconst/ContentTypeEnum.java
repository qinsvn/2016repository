package com.proxy.http.enumconst;
/**
 * 类注释 http请求数据类型
 *
 * @version 2016年7月6日 上午10:47:59
 * @since JDK1.7+
 */
public enum ContentTypeEnum {
    /**
     * json类型数据
     */
    JSON("application/json"),
    /**
     * xml类型数据
     */
    XML("application/xml");
        //可扩展其他类型
    private String contentTypeValue;
 
    ContentTypeEnum(String contentTypeValue) {
        this.contentTypeValue = contentTypeValue;
    }
 
    public String getContentTypeValue() {
        return contentTypeValue;
    }
     
}