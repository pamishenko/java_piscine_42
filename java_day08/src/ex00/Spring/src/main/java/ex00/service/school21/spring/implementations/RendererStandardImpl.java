package ex00.service.school21.spring.implementations;

import ex00.service.school21.spring.models.Renderer;
import org.springframework.stereotype.Component;
import ex00.service.school21.spring.models.PreProcessor;

@Component
public class RendererStandardImpl implements Renderer {
    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }
    @Override
    public void render(String str) {
        System.out.println(preProcessor.process(str));
    }
}
