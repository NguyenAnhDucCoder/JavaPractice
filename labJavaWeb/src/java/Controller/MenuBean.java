/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.MenuPrice;
import Model.MenuDAO;
import java.util.List;

/**
 *
 * @author ANH DUC
 */
public class MenuBean {
    int page, pageSize;

    public MenuBean() {
        page = 1;
        pageSize = 3;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public List<MenuPrice> getAllMenu() throws Exception {
        return new MenuDAO().getAllMenu(page, pageSize);
    }
    
    public int getTotalPages() throws Exception {
        return new MenuDAO().getTotalPages(pageSize);
    }
}
