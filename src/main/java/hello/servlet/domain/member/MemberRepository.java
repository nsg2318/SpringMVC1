package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 동시성 문제가 고려되어 있지 않음, 컨커런트해시맵, 애토믹롱
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //싱글톤으로 만들고싶어서 인스턴스를 하나 먼저 만들어 놓음
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    //private 로 막음
    private MemberRepository(){}

    public Member save(Member member) {
        member.setId(++sequence);
        //key, value
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        // store의 해당 키를 반환
        return store.get(id);
    }

    public List<Member> findAll() {
        //store 에서 values를 다 반환해서 ArrayList에 넣어줌
        return new ArrayList<>(store.values());
    }


    //테스트용
    public void clearStore(){
        store.clear();
    }
}
