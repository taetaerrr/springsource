package com.example.mart;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.constant.OrderStatus;
import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;
import com.example.mart.entity.item.Order;
import com.example.mart.entity.item.OrderItem;
import com.example.mart.repository.item.ItemRepository;
import com.example.mart.repository.item.MemberRepository;
import com.example.mart.repository.item.OrderItemRepository;
import com.example.mart.repository.item.OrderRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class MartRepositoryTests {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ItemRepository itemRepository;

	// C
	@Test
	public void memberInserTest() {
		memberRepository.save(Member.builder().name("user1").city("서울시").street("187-12").zipcode("15100").build());
		memberRepository.save(Member.builder().name("user2").city("경기도").street("236-12").zipcode("16500").build());
		memberRepository.save(Member.builder().name("user3").city("부산시").street("856-12").zipcode("18100").build());
	}

	@Test
	public void itemInserTest() {
		itemRepository.save(Item.builder().name("tshirt").price(25300).quantity(15).build());
		itemRepository.save(Item.builder().name("shoes").price(17300).quantity(20).build());
	}

	@Test
	public void overInsertTest() {

		Member member = memberRepository.findById(1L).get();
		Item item = itemRepository.findById(102L).get();

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

		OrderItem orderItem = orderItemRepository.findById(1L).get();
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
		Member member = memberRepository.findById(1L).get();
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

		OrderItem orderItem = orderItemRepository.findById(1L).get();
		orderItem.setPrice(102000);
		orderItemRepository.save(orderItem);
	}

	// D
	@Test
	public void memberAndItemAndOrderDeleteTest() {
		// 주문 취소

		// 주문 상품 취소
		orderItemRepository.deleteById(1L);

		// 주문 취소
		orderRepository.deleteById(1L);
	}

	// 양방향
	// Order ==> OrderItem 객체 그래프 탐색
	@Transactional
	@Test
	public void testOrderItemList() {
		Order order = orderRepository.findById(2L).get();
		System.out.println(order);
		// Order ==> OrderItem 탐색시도
		order.getOrderItemsList().forEach(orderItem -> System.out.println(orderItem));
	}

	@Transactional
	@Test
	public void testOrderList() {

		Member member = memberRepository.findById(1L).get();
		System.out.println(member);
		// Member ==> Order 탐색 시도
		member.getOrders().forEach(order -> System.out.println(order));

	}

}
