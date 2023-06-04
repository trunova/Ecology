package trunova.service;

import trunova.build.Autowired;
import trunova.build.Component;
import trunova.model.Model;
import trunova.model.ModelSQLDAO;

@Component
public class Service {

    @Autowired
    private ModelSQLDAO modelDao;

    public Model getModel(int id) {
        return modelDao.getModel(id);
    }

    public void create(Model model) {
        modelDao.addNewModel(model);
    }

    public void update(Model model) {
        modelDao.updateModel(model);
    }

}
