package cn.nanfeng.dao;

import cn.nanfeng.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 查询所有联系人
     * @return
     */
    List<User> findAll();

    /**
     * 增加一个联系人
     * @param user
     */
    void add(User user);

    /**
     * 删除一个联系人
     * @param id
     */
    void delete(int id);

    /**
     * 按照联系人的id进行查找
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 修改一个联系人
     * @param user
     */
    void update(User user);

    /**
     * 分页复杂查询
     * @param condition
     * @return
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
    User findUserByUsernameAndPassword(String username, String password);

}
