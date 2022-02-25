package com.nb.sevice;

import com.nb.pojo.Borrow;
import com.nb.pojo.Page;

public interface BorrowService {
    /**
     * 分页查询借阅记录
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    public Page<Borrow> pageAllBorrows(int pageNo, int pageSize,String name);

    /**
     * 用户借阅书籍
     * @param user_id
     * @param book_id
     * @return
     */
    public int userBorrow(int user_id,int book_id);

}
