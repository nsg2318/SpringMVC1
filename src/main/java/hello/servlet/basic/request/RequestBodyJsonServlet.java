package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//Json 방식은 raw 가 아니라 객체로 보통 전송하고 받기 때문에 basic 패키지 밑에 HelloData 클래스 파둠
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    //스프링은 기본적으로 JSON '변환' 라이브러리를 제공해주는데 Jackson임

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        System.out.println("helloData.Username = " + helloData.getUsername());
        System.out.println("helloData.Age = " + helloData.getAge());
        System.out.println("helloData.Sex = " + helloData.getSex());
        System.out.println("helloData.Class = " + helloData.getClass());

    }
}
