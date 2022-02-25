package com.nb.dao;

import com.nb.pojo.Down;
import com.nb.pojo.Up;

import java.util.List;

public interface DownDao {
    /**
     * 修改图书状态为下架
     * @param book_id
     * @return
     */
    public int updateBookState(int book_id);
    /**
     * 生成一条上架记录
     * @param down
     * @return
     */
    public int createDown(Down down);

    /**
     * 查询当前页数据(图书id模糊查找)
     * @param begin
     * @param pageSize
     * @param book_id
     * @return
     */
    public List<Down> queryPageAllItemsByBookId(int begin, int pageSize, String book_id);

    /**
     * 查询总记录数
     * @param book_id
     * @return
     */
    public Integer queryPageTotalCount(String book_id);
}
