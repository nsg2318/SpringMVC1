package hello.servlet.web.frontcontroller.v3;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//                    v2/ 하위에 어떤 url이 들어와도 무조건 이 서블릿이 호출됨. 남)컨트롤러2들의 구현체들에 경로가 없었던 부분이 해결됨
@WebServlet(name ="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    //맵핑정보 어떤 url에 무슨 컨트롤러가 호출될 지. 그래서 인터페이스로 만들었나 ?
    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
                                // 이 url이 요청이 오면                        얘가 실행 됨
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //                    요청 들어온 url 반환
        String requestURI = request.getRequestURI();
        //                  각 url의 new로 생성된 "객체의 인스턴스"가 반환됨
        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);

        // url 이상한 거 처리
        if(controllerV3 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controllerV3.process(paramMap);

        String viewName = mv.getViewName();//논리이름 new-form

        //뷰의 경로를 나는 그냥 viewName만 전달하고싶어서 메서드로 뽑았고 그 이름이 뷰 리졸버
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        //paramMap
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
