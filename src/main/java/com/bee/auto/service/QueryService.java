package com.bee.auto.service;


import com.bee.auto.bean.Answer;
import com.bee.auto.bean.Command;
import com.bee.auto.bean.CommandContent;
import com.bee.auto.bean.Message;
import com.bee.auto.dao.ICommand;
import com.bee.auto.dao.MessageDAO;
import com.bee.auto.util.Iconst;
import com.bee.auto.util.Page;
import com.bee.auto.util.TulingClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 查询相关的业务功能
 */
@Service
public class QueryService {

    @Autowired
    private MessageDAO iMessage;
    @Resource
    private ICommand iCommand;

    /**
     * 根据id查询列表
     */
    public Message queryMessageById(Integer id) {
        Message message = iMessage.queryMessageById(id);
        return message;
    }

    /**
     * 根据查询条件分页查询消息列表
     */
    public List<Message> queryMessageListByPage(String command, String description, Page page) {
        // 组织消息对象
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        int count = iMessage.count(message);
        page.setTotalNumber(count);
        //PageHelper.startPage(currentPage, pageSize);
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("message", message);
        parameter.put("page", page);
        List<Message> messages = iMessage.queryMessageList(parameter);
        // 分页查询并返回结果
        return messages;
    }

    /**
     * 根据查询条件查询消息列表
     */
    public  PageInfo<Message> queryMessageList(String command, String description,int currentPage) {
        // 组织消息对象
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        PageHelper.startPage(currentPage, 5);
        List<Message> messages = iMessage.queryAll(message);
        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        // 查询并返回结果
        return pageInfo;
    }

    /**
     * 根据查询条件查询消息列表
     */
    public  List<Message> queryMessageList2(String command, String description) {
        // 组织消息对象
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);

        List<Message> messages = iMessage.queryAll(message);

        // 查询并返回结果
        return messages;
    }

    /**
     * 通过指令查询自动回复的内容
     *
     * @param command 指令
     * @return 自动回复的内容
     */
    public String queryByCommand(String command) {

        List<Command> commandList;
        if (Iconst.HELP_COMMAND.equals(command)) {
            commandList = iCommand.queryCommandList(null);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < commandList.size(); i++) {
                if (i != 0) {
                    result.append("<br/>");
                }
                result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
            }
            return result.toString();
        }
        Command comd = new Command();
        comd.setName(command);
        commandList = iCommand.queryCommandList(comd);
        if (commandList.size() > 0) {
            int tep = new Random().nextInt(commandList.size());
            List<CommandContent> contentList = commandList.get(tep).getContentList();
            int i = new Random().nextInt(contentList.size());
            return contentList.get(i).getContent();
        }
        return null;
    }

    /**
     * 通过指令交给图灵api自动回复的内容
     *
     * @param content 指令
     * @return 自动回复的内容
     */
    public Answer TULIApi(String content) {
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(content);
        JSONObject jsonObject = null;
        if (matcher.matches()) {
            jsonObject = TulingClient.sendImage(content);
        } else {
            jsonObject = TulingClient.sendMessage(content);
        }
        JSONArray results = jsonObject.getJSONArray("results");
        String url = null;
        String text = null;
        for (int i = 0; i < results.size(); i++) {
            JSONObject result = results.getJSONObject(i);//取得json对象
            JSONObject values = (JSONObject) result.get("values");
            if (values.get("url") != null) {
                url = (String) values.get("url");
            }
            if ((values.get("text") != null))
                text = values.getString("text");
        }
        Answer answer = new Answer();
        answer.setUrl(url);
        answer.setText(text);
        return answer;
    }
}
