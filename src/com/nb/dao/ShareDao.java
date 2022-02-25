package com.nb.dao;

import com.nb.pojo.Book;
import com.nb.pojo.Share;

import java.util.List;

public interface ShareDao {
    /**
     * 根据id查分享图书信息
     * @param id
     * @return
     */
    public Share queryShareBookById(int id);
    /**
     * 通过id删除分享图书信息
     * @param id
     * @return
     */
    public int deleteShareById(int id);

    /**
     * 添加图书至图书资源库
     * @param share
     * @return
     */
    public int addBookToBooks(Share share);

    /**
     * 修改用户输入的图书信息
     * @param share
     * @return
     */
    public int updateSharesBook(Share share);

    /**
     * 当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Share> queryPageItems(int begin,int pageSize,String book_name);
    /**
     * 总记录数
     * @return
     */
    public Integer queryPageTotalCount(String book_name);

    public int createShare(Share share);

    public List<Share> queryShareByUser_id(int user_id);
}
