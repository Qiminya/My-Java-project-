package com.nb.sevice.impl;

import com.nb.dao.DownDao;
import com.nb.dao.impl.DownDaoImpl;
import com.nb.pojo.Down;
import com.nb.pojo.Page;
import com.nb.sevice.DownService;

import java.util.List;

public class DownServiceImpl implements DownService {
    private DownDao downDao=new DownDaoImpl();

    @Override
    public int downBook(Down down) {
        //修改图书状态为下架
        if(downDao.updateBookState(down.getBook_id())==1){//修改成功
            //新增一条下架记录
            if (downDao.createDown(down)==1){
                return 1;//操作成功
            }else {
                return 0;//修改状态成功，新增下架记录失败
            }
        }else{
            return -1;//修改状态失败
        }
    }

    @Override
    public Page<Down> pageByBook_id(int pageNo, int pageSize, String book_id) {
        Page<Down> page = new Page<Down>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = downDao.queryPageTotalCount(book_id);
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
        List<Down> items = downDao.queryPageAllItemsByBookId(begin,pageSize,book_id);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }
}
