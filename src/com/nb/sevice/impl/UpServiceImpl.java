package com.nb.sevice.impl;

import com.nb.dao.UpDao;
import com.nb.dao.impl.UpDaoImpl;
import com.nb.pojo.Down;
import com.nb.pojo.Page;
import com.nb.pojo.Up;
import com.nb.sevice.UpService;

import java.util.List;

public class UpServiceImpl implements UpService {
    private UpDao upDao=new UpDaoImpl();
    @Override
    public int createUp(Up up) {
        return upDao.createUp(up);
    }

    @Override
    public Page<Up> pageAllUps(int pageNo, int pageSize, String book_id) {
        Page<Up> page = new Page<Up>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = upDao.queryPageTotalCount(book_id);
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
        List<Up> items = upDao.queryPageAllItemsByBookId(begin,pageSize,book_id);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }
}
