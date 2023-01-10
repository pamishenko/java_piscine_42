package ex00.service.school21.spring.implementations;

import ex00.service.school21.spring.models.PreProcessor;
import org.springframework.stereotype.Component;

@Component
public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String process(String str) {
        return str.toUpperCase();
    }
}
