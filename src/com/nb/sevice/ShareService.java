package com.nb.sevice;

import com.nb.pojo.Book;
import com.nb.pojo.Page;
import com.nb.pojo.Share;

import java.util.List;

public interface ShareService {
    /**
     * 对所有分享图书数据进行分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Share> pageAllShares(int pageNo, int pageSize,String book_name);

    /**
     * 将分享图书添加到图书库
     * @param share
     */
    public void addToBooks(Share share);

    /**
     * 修改分享图书数据
     * @return
     */
    public int updateSharesBook(Share share);

    /**
     * 根据id查分享图书信息
     * @param id
     * @return
     */
    public Share queryShareBookById(int id);

    public int createShare(Share share);

    public List<Share> queryShareByUser_id(int user_id);
}
