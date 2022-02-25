package com.nb.sevice.impl;

import com.nb.dao.ShareDao;
import com.nb.dao.impl.ShareDaoImpl;
import com.nb.pojo.Page;
import com.nb.pojo.Share;
import com.nb.sevice.ShareService;

import java.util.List;

public class ShareServiceImpl implements ShareService {
    private ShareDao shareDao=new ShareDaoImpl();
    @Override
    public Page<Share> pageAllShares(int pageNo, int pageSize,String book_name) {
        Page<Share> page = new Page<Share>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = shareDao.queryPageTotalCount(book_name);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Share> items = shareDao.queryPageItems(begin,pageSize,book_name);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public void addToBooks(Share share) {
        shareDao.addBookToBooks(share);
        shareDao.deleteShareById(share.getShare_id());
    }

    @Override
    public int updateSharesBook(Share share) {
        return shareDao.updateSharesBook(share);
    }

    @Override
    public Share queryShareBookById(int id) {
        return shareDao.queryShareBookById(id);
    }

    @Override
    public int createShare(Share share) {
        return shareDao.createShare(share);
    }

    @Override
    public List<Share> queryShareByUser_id(int user_id) {
        return shareDao.queryShareByUser_id(user_id);
    }
}
