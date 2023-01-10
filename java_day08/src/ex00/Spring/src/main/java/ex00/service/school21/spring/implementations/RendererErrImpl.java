package ex00.service.school21.spring.implementations;

import ex00.service.school21.spring.models.Renderer;
import org.springframework.stereotype.Component;
import ex00.service.school21.spring.models.PreProcessor;

@Component
public class RendererErrImpl implements Renderer {
    private final PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String str) {
        System.err.print(preProcessor.process(str));

    }
}
