package com.nb.sevice;

import com.nb.pojo.Back;
import com.nb.pojo.Page;

public interface BackService {
    /**
     * 分页查询所有还书记录
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Back> pageAllBacks(int pageNo, int pageSize);

    /**
     * 根据图书id查询所有还书记录
     * @param pageNo
     * @param pageSize
     * @param id
     * @return
     */
    public Page<Back> pageAllBacksByBook_Id(int pageNo, int pageSize, int id);

    /**
     * 用户还书
     * @param book_id
     * @return
     */
    public int userBackBook(int book_id);
}
