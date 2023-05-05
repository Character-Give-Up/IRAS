package org.character.iras.CloudAIAccess;

import com.alibaba.fastjson.JSONObject;
import org.character.iras.Entity.Resume;

public interface ResumeAnalyzer {
    /**
     * 传递简历
     * @param resume 要分析的简历实体
     */
    void setResume(Resume resume);

    /**
     * 提取简历关键信息
     * @return 简历关键信息，包含姓名、年龄、最高学历、毕业院校、工作年限的信息
     */
    JSONObject getKeyInfo();
}
