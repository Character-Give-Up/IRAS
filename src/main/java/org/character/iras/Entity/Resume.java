package org.character.iras.Entity;

import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Resume {
    private final String path; // 简历文件路径

    private final List<String> keyword = new ArrayList<>(); // 简历关键字
    private String name; // 姓名
    private int age; // 年龄
    private String HighestDegree; // 最高学历
    private String GraduateSchool; // 毕业院校
    private String WorkingSeniority; // 工作年限

    public Resume(String path) {
        this.path = path;
    }
}
