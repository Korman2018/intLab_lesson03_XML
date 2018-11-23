package task01;

import task01.service.IDemoservice;
import task01.service.impl.DemoServiceImpl;

public class Runner {
    public static void main(String[] args) {
        IDemoservice demoservice = new DemoServiceImpl();
        demoservice.runDemo();
    }
}
