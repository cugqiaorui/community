package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.MailClient;
import com.nowcoder.community.util.SensitiveFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void testMessage(){
        List<Integer> ids = new ArrayList<>();
        ids.add(27);
        messageMapper.updateStatus(ids,1);
    }


    @Test
    public void testSensitiveFilter(){
        String filter = sensitiveFilter.filter("不准(☆-ｖ-)赌(☆-ｖ-)博(☆-ｖ-)啊");
        System.out.println(filter);
    }

    @Test
    public void testLoginTicket(){
       /* LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(23);
        loginTicket.setTicket("123");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));
        loginTicketMapper.insertLoginTicket(loginTicket);*/

        LoginTicket loginTicket = loginTicketMapper.selectByTicket("123");
        System.out.println(loginTicket);
       loginTicketMapper.updateStatus("123",1);

    }

    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username","sunny");
        String content = templateEngine.process("/mail/demo", context);
        mailClient.sendMail("724857684@qq.com","html",content);
    }

    @Test
    public void testTextMail(){
        mailClient.sendMail("724857684@qq.com","test","helloworld");
    }

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);
    }

    @Test
    public void testSelectDiscussPost(){
        log.info("111");
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for(DiscussPost discussPost:discussPosts){
            System.out.println(discussPost);
        }
        int i = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(i);

    }

}
