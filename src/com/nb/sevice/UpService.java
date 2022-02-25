package com.nb.sevice;

import com.nb.pojo.Page;
import com.nb.pojo.Up;

public interface UpService {
    /**
     * 生成一条上架记录
     * @param up
     * @return
     */
    public int createUp(Up up);

    /**
     * 对上架记录分页
     * @param pageNo
     * @param pageSize
     * @param book_id
     * @return
     */
    public Page<Up> pageAllUps(int pageNo,int pageSize,String book_id);
}
