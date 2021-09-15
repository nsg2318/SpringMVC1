package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //바이트 코드로 바로 얻음
        ServletInputStream inputStream = request.getInputStream();
        //스프링이 제공하는 캐스팅
        String masageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        //하지만 이렇게 그냥 메시지로 주고받진 않음 ._. Json으로 하니까 Json으로 하자
        System.out.println("masageBody = " + masageBody);
        response.getWriter().write("this is response message");

    }
}
