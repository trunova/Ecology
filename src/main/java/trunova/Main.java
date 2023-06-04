package trunova;

import trunova.build.Autowired;
import trunova.controller.Controller;
import trunova.build.Context;
import trunova.model.ModelSQLDAO;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    @Autowired
    private static Controller controller;

    @Autowired
    private static ModelSQLDAO dao;

    static {
        try {
            Context.injectDependencies();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setContextPath("/api");
        servletContextHandler.addServlet(new ServletHolder(controller), "/*");
        server.setHandler(servletContextHandler);
        server.start();
        server.join();
//        Factory f1 = new Factory(1, 2, 3, 4);
//        Factory f2 = new Factory(5, 6, 7, 8);
//        List<Factory> l = new ArrayList<>();
//        l.add(f1);
//        l.add(f2);
//        Model m = new Model(l, 1, 2, 3, 4, 5,6 , 7);
//        dao.addNewModel(m);
    }
}
