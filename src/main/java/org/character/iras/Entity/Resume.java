package org.character.iras.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 简历实体
 */
public class Resume {

    private int id;
    /**
     * 简历文件路径
     */
    private final String path; // 简历文件路径
    /**
     * 简历关键字
     */
    private final List<String> keyword = new ArrayList<>(); // 简历关键字
    /**
     * 姓名
     */
    private String name; // 姓名
    /**
     * 年龄
     */
    private int age; // 年龄
    /**
     * 最高学历
     */
    private String HighestDegree; // 最高学历
    /**
     * 毕业院校
     */
    private String GraduateSchool; // 毕业院校
    /**
     * 工作年限
     */
    private String WorkingSeniority; // 工作年限
    /**
     * 原始内容
     */
    private String originalContent;

    /**
     * 创建简历实体
     * @param path 文件路径
     * @throws IOException 遇到I/O问题
     */
    public Resume(String path) throws IOException {
        this.path = path;
        setOriginalContent(new PDFResolver(path).resolve());
    }

    public Resume(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    /**
     * 获取简历文件的路径
     * @return 简历文件的路径
     */
    public String getPath() {
        return path;
    }



    /**
     * 获取简历信息的关键字
     * @return 简历信息的关键字
     */
    public List<String> getKeyword() {
        return keyword;
    }

    /**
     * 获取姓名
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置简历的姓名
     * @param name 简历的姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取年龄
     * @return 年龄
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置简历的年龄
     * @param age 简历的年龄
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取最高学历
     * @return 最高学历
     */
    public String getHighestDegree() {
        return HighestDegree;
    }

    /**
     * 设置简历的最高学历
     * @param highestDegree 最高学历
     */
    public void setHighestDegree(String highestDegree) {
        HighestDegree = highestDegree;
    }

    /**
     * 获取毕业院校
     * @return 毕业院校
     */
    public String getGraduateSchool() {
        return GraduateSchool;
    }

    /**
     * 设置简历的毕业院校
     * @param graduateSchool 毕业院校
     */
    public void setGraduateSchool(String graduateSchool) {
        GraduateSchool = graduateSchool;
    }

    /**
     * 获取工作年限
     * @return 工作年限
     */
    public String getWorkingSeniority() {
        return WorkingSeniority;
    }

    /**
     * 设置简历的工作年限
     * @param workingSeniority
     */
    public void setWorkingSeniority(String workingSeniority) {
        WorkingSeniority = workingSeniority;
    }

    /**
     * 获取简历的原始信息
     * @return 原始信息
     */
    public String getOriginalContent() {
        return originalContent;
    }

    /**
     * 设置简历的原始信息
     * @param originalContent 原始信息
     */
    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    /**
     * 增加简历的关键字
     * @param keyword 关键字
     */
    public void addKeyword(String keyword){
        this.keyword.add(keyword);
    }

    /**
     * 移除简历的关键字
     * @param keyword 要移除的关键字
     */
    public void removeKeyword(String keyword){
        this.keyword.removeIf(s -> s.equals(keyword));
    }
}
