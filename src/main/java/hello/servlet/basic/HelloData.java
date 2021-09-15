package hello.servlet.basic;


import lombok.Getter;
import lombok.Setter;

//롬복 게터세터
@Getter @Setter
public class HelloData {
    private String username;
    private int age;
    private String sex;
}
