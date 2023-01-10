package ex00.service.school21.spring.implementations;

import ex00.service.school21.spring.models.Printer;
import ex00.service.school21.spring.models.Renderer;
import org.springframework.stereotype.Component;

@Component
public class PrinterWithPrefixImpl implements Printer {
    private final Renderer renderer;
    private String prefix ="";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String str) {
        this.renderer.render(prefix);
        this.renderer.render(str);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
