package com.lws.juzimi.mvp.model.bean;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 * 句子合集-列表
 */
public class SentenceDetail {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SentenceDetail{" +
                "content='" + content + '\'' +
                '}';
    }
}
