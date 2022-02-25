package com.nb.dao;

import com.nb.pojo.Back;

import java.util.List;

public interface BackDao {
    /**
     * 根据图书或者查所有记录数
     * @return
     */
    public Integer queryPageTotalCount();

    /**
     * 查询当前页记录数
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Back> queryPageItems(int begin,int pageSize);

    /**
     * 根据图书id或者查所有记录数
     * @param id
     * @return
     */
    public Integer queryPageTotalCountByBook_Id(int id);
    /**
     * 根据图书id查还书记录
     * @param begin
     * @param pageSize
     * @param id
     * @return
     */
    public List<Back> queryPageItemsByBook_Id(int begin,int pageSize,int id);

    /**
     * 通过图书id修改该书最新借书记录的归还状态为归还
     * @param book_id
     * @return
     */
    public int updateBorrowFlagByBookId(int book_id);

    public int addOneBack(int book_id,int borrow_id);

    public int getNewBorrowIdByBookId(int book_id);
    public int updateBookStateById(int book_id);
}
