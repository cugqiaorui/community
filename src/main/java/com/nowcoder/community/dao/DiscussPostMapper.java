package com.nowcoder.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface DiscussPostMapper extends BaseMapper<DiscussPost> {

    //首页查询时userId为0，个人主页查询时为个人userId，需要写动态sql
    //limit 分页查询每页多少行，offset,这一页起始行的行号
    List<DiscussPost> selectDiscussPosts(@Param("userId")int userId,@Param("offset")int offset,@Param("limit")int limit);

    //一共有多少行
    //@Param 用于给参数起别名
    //如果需要动态的sql,在<if>里使用，并且这个方法只有一个参数，那这个参数前面需要写别名
    int selectDiscussPostRows(@Param("userId") int userId);


}
