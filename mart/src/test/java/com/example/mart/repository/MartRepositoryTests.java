package com.example.mart.repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.constant.DeliveryStatus;
import com.example.mart.entity.constant.OrderStatus;
import com.example.mart.entity.item.Delivery;
import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;
import com.example.mart.entity.item.Order;
import com.example.mart.entity.item.OrderItem;
import com.example.mart.entity.product.Album;
import com.example.mart.entity.product.Book;
import com.example.mart.entity.product.Movie;
import com.example.mart.repository.item.DeliveryRepository;
import com.example.mart.repository.item.ItemRepository;
import com.example.mart.repository.item.MemberRepository;
import com.example.mart.repository.item.OrderItemRepository;
import com.example.mart.repository.item.OrderRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MartRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    // C
    @Test
    public void memberInserTest() {
        memberRepository.save(Member.builder().name("user1").city("서울시").street("187-12").zipcode("15100").build());
        memberRepository.save(Member.builder().name("user2").city("경기도").street("236-12").zipcode("16500").build());
        memberRepository.save(Member.builder().name("user3").city("부산시").street("856-12").zipcode("18100").build());
    }

    @Test
    public void itemInserTest() {
        // itemRepository.save(Item.builder().name("tshirt").price(25300).quantity(15).build());
        // itemRepository.save(Item.builder().name("shoes").price(17300).quantity(20).build());

        Album album = new Album();
        album.setArtist("로제");
        album.setName("아파트");
        album.setPrice(15200);
        album.setQuantity(15);
        itemRepository.save(album);

        Book book = new Book();
        book.setAuthor("한강");
        book.setIsbn("122ㄱ");
        book.setName("소년이 온다");
        book.setPrice(10800);
        book.setQuantity(15);
        itemRepository.save(book);

        Movie movie = new Movie();
        movie.setActor("폴 메스칼");
        movie.setDirector("라들리 스콧");
        movie.setName("글래디에이터2");
        movie.setPrice(25000);
        movie.setQuantity(300);
        itemRepository.save(movie);
    }

    @Test
    public void overInsertTest() {

        Member member = memberRepository.findById(1L).get();
        Item item = itemRepository.findById(1L).get();

        Order order = Order.builder()
                .overDate(LocalDateTime.now())
                .status(OrderStatus.ORDER)
                .member(member)
                .build();

        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder()
                .price(200000)
                .count(2)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);

    @Test
    public void overItemInsertTest() {

       Item item = itemRepository

        OrderItem orderItem = OrderItem.builder()
                .price(200000)
                .count(2)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);
        // item 수량 감소

        // R

    }

    @Test
    public void memberAndItemAndOrderListTest() {
        // 주문 내역 조회
        // orderRepository.findAll().forEach(order -> System.out.println(order));

        // 주문 상세 내역 조회
        orderItemRepository.findAll().forEach(orderItem -> {
            System.out.println(orderItem);
            // 상품 상세 조회
            System.out.println(orderItem.getItem());
            // 주문 상세 내역 조회
            System.out.println(orderItem.getOrder());
            // 주문자 상세 조회
            System.out.println(orderItem.getOrder().getMember());
        });
    }

    @Test
    public void memberAndItemAndOrderRowTest() {
        // 주문 내역 조회
        // orderRepository.findAll().forEach(order -> System.out.println(order));

        OrderItem orderItem = orderItemRepository.findById(3L).get();
        // 주문 상세 내역 조회
        System.out.println(orderItem);
        // 상품 상세 조회
        System.out.println(orderItem.getItem());
        // 주문 상세 내역 조회
        System.out.println(orderItem.getOrder());
        // 주문자 상세 조회
        System.out.println(orderItem.getOrder().getMember());
    }

    // U
    @Test
    public void memberAndItemAndOrderUpdateTest() {

        // 주문자의 주소 변경
        // Member member = Member.builder()
        // .id(1L)
        // .name("user1")
        // .street("187-12")
        // .zipcode("15100")
        // .build();
        Member member = memberRepository.findById(4L).get();
        member.setStreet("189-12");

        // save : insert or update
        // 엔티티 매니저가 있어서 현재 entity 가 new 인지 기존 entity 인지 구분이 가능함
        // new : insert 호출 / 기존 entity : update 호출
        // update 는 무조건 전체 칼럼이 수정되는 형태로 작성됨
        System.out.println(memberRepository.save(member));

        // 1번 주문상품의 아이템(2번 아이템) 가격 변경
        // 아이템 수량, 가격 변경
        Item item = itemRepository.findById(102L).get();
        item.setPrice(22000);
        itemRepository.save(item);

        OrderItem orderItem = orderItemRepository.findById(3L).get();
        orderItem.setPrice(102000);
        orderItemRepository.save(orderItem);
    }

    // D
    // @Test
    // public void memberAndItemAndOrderDeleteTest() {
    // // 주문 취소

    // // 주문 상품 취소
    // orderItemRepository.deleteById(3L);

    // // 주문 취소
    // orderRepository.deleteById(2L);
    // }

    // 양방향
    // Order ==> OrderItem 객체 그래프 탐색
    @Transactional
    @Test
    public void testOrderItemList() {
        Order order = orderRepository.findById(4L).get();
        System.out.println(order);
        // Order ==> OrderItem 탐색시도
        order.getOrderItemsList().forEach(orderItem -> System.out.println(orderItem));
    }

    @Transactional
    @Test
    public void testOrderList() {
        Member member = memberRepository.findById(4L).get();
        System.out.println(member);
        // Member ==> Order 탐색 시도
        member.getOrders().forEach(order -> System.out.println(order));

    }

    // 일대일
    @Test
    public void testDeliveryInsert() {

        // 배송 정보 입력
        Delivery delivery = Delivery.builder()
                .city("서울시")
                .street("동소문로1가")
                .zipcode("11051")
                .deliveryStatus(DeliveryStatus.READY)
                .build();

        deliveryRepository.save(delivery);

        // order 와 배송정보 연결
        Order order = orderRepository.findById(4L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);

    }

    // 일대일
    @Test
    public void testOrderRead() {
        Order order = orderRepository.findById(4L).get();
        System.out.println(order);

        System.out.println(order.getDelivery());
    }

    // 양방향
    @Test
    public void testDeliveryRead() {
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println(delivery);
        System.out.println(delivery.getOrder());
    }

    @Test
    public void testRead() {
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println(delivery);
        System.out.println(delivery.getOrder());
    }

    // querydsl
    @Test
    public void testMembers() {
        System.out.println(orderRepository.members());
    }

    @Test
    public void testItems() {
        System.out.println(orderRepository.items());
    }

    @Test
    public void testJoin() {
        List<Object[]> result = orderRepository.joinTest();

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            System.out.println((Order) objects[0]);
            System.out.println((Member) objects[1]);
        }
    }
}
