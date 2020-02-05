package servlet;
import application.ThymeleaUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import pojo.Company;
import service.CompanyService;
import service.CompanyServiceImpl;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "CompanyServlet", urlPatterns = {"/Company"}, loadOnStartup = 1)


public class CompanyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String getTamplate=null;
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        CompanyService companyService = new CompanyServiceImpl();

        Map<String, Object> variables = new HashMap<>();
        IContext context;
        String paramName=null;


        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();


//           response.getWriter().println(paramName);
//            String[] paramValues = request.getParameterValues(paramName);
//            for (int i = 0; i < paramValues.length; i++) {
//                String paramValue = paramValues[i];
//                response.getWriter().println(paramValue);
            }
        if(paramName!=null){
            String[] paramValues = request.getParameterValues(paramName);
            int id = Integer.parseInt(paramValues[0]);

            Company companyFromService = companyService.readCompany(id);
            variables.put("name", companyFromService.getCompanyName());
            variables.put("description", companyFromService.getCompanyDescription());
            context = new Context(Locale.getDefault(), variables);
            getTamplate = "showExactCompany";

        }
        else{
            List<Company> companyListFromService = companyService.readCompanyList();
            variables.put("massage", "Company for Secret Santa");
            variables.put("recordList", companyListFromService);
            context = new Context(Locale.getDefault(), variables);
            getTamplate = "showCompanyList";
            //    ThymeleaUtils.drawPage("showCompanyList", context);

        }


        ThymeleaUtils.getTemplateEngine().process(getTamplate, context, response.getWriter());

        }
    }
