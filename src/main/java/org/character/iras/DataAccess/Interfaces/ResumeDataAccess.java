package org.character.iras.DataAccess.Interfaces;

import org.character.iras.Entity.Resume;

import java.util.List;

/**
 * 简历数据连接层接口
 */
public interface ResumeDataAccess extends DataAccess{
    /**
     * 新增简历数据
     * @param id 简历ID号
     * @param path 简历路径
     */
    void putNewResumeData(int id, String path);


    void putNewResumeData(int id, Resume resume);

    /**
     * 获取所有的简历的ID
     * @return 由所有简历ID组成的列表
     */
    List<Integer> getIds();
    /**
     * 获取最大简历ID号
     * @return 最大的简历ID号
     */
    int getMaximumId();
    /**
     * 获取简历URL
     * @param id 指定的简历的ID
     * @return 指定ID简历的URL地址
     */
    String getURL(int id);

    List<Resume> getResumes();
}

