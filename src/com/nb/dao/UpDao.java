package com.nb.dao;

import com.nb.pojo.Up;

import java.util.List;

public interface UpDao {
    /**
     * 生成一条上架记录
     * @param up
     * @return
     */
    public int createUp(Up up);

    /**
     * 查询当前页数据(图书id模糊查找)
     * @param begin
     * @param pageSize
     * @param book_id
     * @return
     */
    public List<Up> queryPageAllItemsByBookId(int begin, int pageSize, String book_id);

    /**
     * 查询总记录数
     * @param book_id
     * @return
     */
    public Integer queryPageTotalCount(String book_id);
}
