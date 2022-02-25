package com.nb.dao;

import com.nb.pojo.Borrow;

import java.util.List;

public interface BorrowDao {
    /**
     * 总记录数
     * @param name 为空字符时查询所有，否则按名字查
     * @return
     */
    public Integer queryPageTotalCount(String name);

    /**
     * 当前页数据
     * @param begin
     * @param pageSize
     * @param name
     * @return
     */
    public List<Borrow> queryPageItems(int begin,int pageSize ,String name );

    /**
     * 根据图书id更改图书状态为state
     * @param id
     * @return
     */
    public int updateBookStateById(int id);

    /**
     * 添加一条借阅记录
     * @return
     */
    public int addOneBorrow(int user_id,int book_id);//user_id,book_id,borrow_time即可

    /**
     * 在借书记录中通过图书id得到最新该图书记录的借阅者id
     * @param book_id
     * @return
     */
    public Integer getUserIdByBookId(int book_id);
}
