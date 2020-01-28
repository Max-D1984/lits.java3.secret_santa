package application;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.io.FileWriter;
import java.io.IOException;

public class ThymeleaUtils {
    public static void drawPage(String template, IContext context) {
        TemplateEngine templateEngine = getTemplateEngine();
        FileWriter out = null;
        try {
            out = new FileWriter(template+".out.html");
            templateEngine.process(template, context, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static TemplateEngine getTemplateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
}