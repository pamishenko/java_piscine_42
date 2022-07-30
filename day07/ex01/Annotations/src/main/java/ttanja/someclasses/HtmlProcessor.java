package ttanja.someclasses;

import ttanja.annotation.HtmlForm;
import ttanja.annotation.HtmlInput;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@SupportedAnnotationTypes({"ttanja.annotation.HtmlForm", "ttanja.annotation.HtmlInput"})
public class HtmlProcessor extends fromAbstractProcessor{
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element userForm : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            HtmlForm htmlFormAnn = userForm.getAnnotation(HtmlForm.class);
            String line = "<form action = \"" + htmlFormAnn.action() + "\" method = \"" + htmlFormAnn.method() + "\">\n";
            List<? extends Element> userFormElements = userForm.getEnclosedElements();
            for (Element field : roundEnv.getElementsAnnotatedWith(HtmlInput.class)) {
                if (!userFormElements.contains(field)) {
                    continue;
                }
                HtmlInput htmlInputAnn = field.getAnnotation(HtmlInput.class);
                line += "\t<input type = " + htmlInputAnn.type() + "\" name = \"" +
                        htmlInputAnn.name() + "\" placeholder = \"" + htmlInputAnn.placeholder() + "\">\n";
            }
            line += "\t<input type = \"submit\" value = \"Send\">\n</form>";
            try (BufferedWriter writter = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + htmlFormAnn.fileName()))) {
                writter.write(line);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}