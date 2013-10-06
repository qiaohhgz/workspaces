package ibatis;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Hello world!
 */
public interface BlogDao {

    @Insert("insert into blog (subject,content,createOn) values(#{0},#{1},#{2})")
    public void save(String subject, String content, Date date);

    @Insert("insert into blog (subject,content,createOn) values(#{subject},#{content},#{createOn})")
    public void saveBlog(Blog blog);

    @Insert("insert into blog (subject,content) values(#{subject},#{content})")
    public void saveBlogBean(BlogBean blog);

    @Select("select * from blog where id = #{0}")
    @Results(value = {@Result(column = "createOn", property = "createOn", jdbcType = JdbcType.DATE)})
    public Blog getBlogById(int id);

    @Select("select * from blog")
    @Results(value = {@Result(column = "createOn", property = "createOn", jdbcType = JdbcType.DATE)})
    public List<Blog> getBlogList();

    @Select("select * from blog")
    public List<BlogBean> getBlogBeanList();

    @Update("update blog set subject=#{subject},content=#{content} where id = #{1}")
    public void update(BlogBean blog, int id);

    @Delete("delete from blog where id = #{0}")
    public void delete(int id);
}
