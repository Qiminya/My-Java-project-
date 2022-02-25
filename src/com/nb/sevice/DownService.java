package com.nb.sevice;

import com.nb.pojo.Down;
import com.nb.pojo.Page;

public interface DownService {

    /**
     * 下架图书
     * @param down
     * @return
     */
    public int downBook(Down down);

    /**
     * 下架记录分页
     * @param pageNo
     * @param pageSize
     * @param book_id
     * @return
     */
    public Page<Down> pageByBook_id(int pageNo,int pageSize,String book_id);
}
