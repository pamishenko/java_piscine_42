package ex00.service.school21.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ex00.service.school21.spring.models.Printer;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "context.xml"
        );
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        printer.print("Hello!");
        System.err.println();
        Printer printer1 = context.getBean("printerWithDateTime", Printer.class);
        printer1.print("Hello Date!!!");
        context.close();
    }
}
