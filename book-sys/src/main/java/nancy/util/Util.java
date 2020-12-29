package nancy.util;


import nancy.pojo.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName Util
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/14 19:09
 * @Version 1.0
 **/
public class Util {
    //解析搜索和分页的数据searchText=&sortOrder=asc&pageSize=7&pageNumber=1
    public static Page parse(HttpServletRequest request){
        Page p = new Page();
        p.setSearchText(request.getParameter("searchText"));//如果key没有的话返回null
        p.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        p.setSortOrder(request.getParameter("sortOrder"));
        p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
        return p;
    }
}
