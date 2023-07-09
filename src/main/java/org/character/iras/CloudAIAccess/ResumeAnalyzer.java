package org.character.iras.CloudAIAccess;

import com.alibaba.fastjson.JSONObject;
import org.character.iras.Entity.Resume;

/**
 * 简历分析器。通过实现此接口中的所有方法，完成简历分析功能。
 */
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
