package hello.servlet.web.frontcontroller.v2;


import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//                    v2/ 하위에 어떤 url이 들어와도 무조건 이 서블릿이 호출됨. 남)컨트롤러2들의 구현체들에 경로가 없었던 부분이 해결됨
@WebServlet(name ="frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    //맵핑정보 어떤 url에 무슨 컨트롤러가 호출될 지. 그래서 인터페이스로 만들었나 ?
    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerServletV2() {
                                // 이 url이 요청이 오면                        얘가 실행 됨
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //                    요청 들어온 url 반환
        String requestURI = request.getRequestURI();
        //                  각 url의 new로 생성된 "객체의 인스턴스"가 반환됨
        ControllerV2 controllerV2 = controllerV2Map.get(requestURI);

        // url 이상한 거 처리
        if(controllerV2 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controllerV2.process(request, response);
        view.render(request, response);
    }
}
