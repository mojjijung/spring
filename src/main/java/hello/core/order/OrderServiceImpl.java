package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements  OrderService{


    //DIP 의존성을 해치기 때문에 주석
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    private final DiscountPolicy discountPolicy = new RateDiscoutPolicy();
    // 의존성을 해치지 않기 위해 소스 변경
    // 만약 위의 소스대로 한다면 OrderServiceImpl => DiscountPolicy 를 거치기도 하지만 => RateDiscountPolicy , FixDiscountPolicy 에도 의존하고있기 때문에 DIP에 위배된다.
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findMyId(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return  new Order(memberId, itemName , itemPrice , discountPrice);
    }
}

