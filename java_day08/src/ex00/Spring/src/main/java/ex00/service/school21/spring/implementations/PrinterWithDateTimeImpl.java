package ex00.service.school21.spring.implementations;

import ex00.service.school21.spring.models.Renderer;
import org.springframework.stereotype.Component;
import ex00.service.school21.spring.models.Printer;

import java.time.LocalDateTime;

@Component
public class PrinterWithDateTimeImpl implements Printer {
    private final Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    @Override
    public void print(String str) {
        renderer.render(LocalDateTime.now() + " " + str);
    }
}
