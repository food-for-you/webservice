package ga.rugal.food.core.service.impl;

import ga.rugal.food.core.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.service.MenuService;
import ml.rugal.sshcommon.page.Pagination;
/**
 *
 * @author Ying Mi
 */
public class MenuServiceImpl implements MenuService{
    
    @Autowired
    private MenuDao menuDao;
    
    @Override
    public Menu save(Menu bean) {
        return menuDao.save(bean);
    }
    
   @Override
    public Pagination getPage(int pageNo, int pageSize) {
        return menuDao.getPage(pageNo, pageSize);
    }
    
}