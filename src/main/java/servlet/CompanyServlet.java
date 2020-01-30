package servlet;
import application.ThymeleaUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import pojo.Company;
import service.CompanyService;
import service.CompanyServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "CompanyServlet", urlPatterns = {"/showExactCompany"}, loadOnStartup = 1)


public class CompanyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        CompanyService companyService = new CompanyServiceImpl();
        Company companyFromService = companyService.readCompany(3);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", companyFromService.getCompanyName());
        variables.put("description", companyFromService.getCompanyDescription());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.getTemplateEngine().process("showExactCompany", context, response.getWriter());

    }
}