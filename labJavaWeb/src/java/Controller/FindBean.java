/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Address;
import Entity.Opening;
import Model.FindUsDAO;
import java.util.List;

/**
 *
 * @author ANH DUC
 */
public class FindBean {

    int page, pageSize;

    public FindBean() {
        page = 1;
        pageSize = 1;
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
    public List<Address> getAddress() throws Exception {
        return new FindUsDAO().getAddress(page, pageSize);
    }
    public int getTotalPages() throws Exception {
        return new FindUsDAO().getTotalPages(pageSize);
    }


}
