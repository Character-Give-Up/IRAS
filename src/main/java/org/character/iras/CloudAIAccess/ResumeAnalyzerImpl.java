package org.character.iras.CloudAIAccess;
import com.alibaba.fastjson.JSONObject;
import org.character.iras.Entity.Resume;


import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.io.IOException;


public class ResumeAnalyzerImpl implements ResumeAnalyzer{

    private Resume pdfText;
    /**
     * 传递简历
     *
     * @param resume 要分析的简历实体
     */

    @Override
    public void setResume(Resume resume) {
        this.pdfText = resume;

            }

    /**
     * 提取简历关键信息
     *
     * @return 简历关键信息，包含姓名、年龄、最高学历、毕业院校、工作年限的信息
     */
    @Override

    public JSONObject getKeyInfo() {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket("localhost", 8888);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 发送文本数据到Python端
            out.println(this.pdfText);

            // 接收Python端处理后的结果
            System.out.println("Receiving begin...");
            String jsonResult = in.readLine();
            System.out.println("Receive successfully!");

            JSONObject jsonObject = JSONObject.parseObject(jsonResult);
            return jsonObject;
//            System.out.println(jsonObject);
//            for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {
//                System.out.println(stringObjectEntry.getKey() + "=" + stringObjectEntry.getValue());
//            }

//            System.out.println("Received result from Python: " + jsonResult);



        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } return null;

    }
}
