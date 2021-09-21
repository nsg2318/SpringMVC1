package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//                    v1/ 하위에 어떤 url이 들어와도 무조건 이 서블릿이 호출됨. 남)컨트롤러V1들의 구현체들에 경로가 없었던 부분이 해결됨
@WebServlet(name ="frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    //맵핑정보 어떤 url에 무슨 컨트롤러가 호출될 지. 그래서 인터페이스로 만들었나 ?
    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
                                // 이 url이 요청이 오면                        얘가 실행 됨
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //                    요청 들어온 url 반환
        String requestURI = request.getRequestURI();
        //                  각 url의 new로 생성된 "객체의 인스턴스"가 반환됨
        ControllerV1 controllerV1 = controllerV1Map.get(requestURI);

        // url 이상한 거 처리
        if(controllerV1 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controllerV1.process(request,response);
    }
}
