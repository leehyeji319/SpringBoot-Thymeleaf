package hello.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;

@Controller
@RequestMapping("/basic")
public class BasicController {

	@GetMapping("/text-basic")
	public String textBasic(Model model) {
		model.addAttribute("data", "Hello <b>Spring!</b>");
		return "basic/text-basic";
	}

	@GetMapping("/text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data", "Hello <b>Spring!</b>");
		return "basic/text-unescaped";
	}

	@GetMapping("/variable")
	public String variable(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);

		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);

		Map<String, User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB);

		model.addAttribute("user", userA);
		model.addAttribute("users", list);
		model.addAttribute("userMap", map);

		return "basic/variable";
	}

	@Data
	static class User {
		private String username;
		private int age;

		public User(String username, int age) {
			this.username = username;
			this.age = age;
		}
	}

	@GetMapping("/basic-objects")
	public String basicObjects(HttpSession session) {
		session.setAttribute("sessionData", "Hello Session");
		return "basic/basic-objects";
	}

	@Component("helloBean")
	static class HelloBean {
		public String hello(String data) {
			return "Hello " + data;
		}
	}

	//링크달기 -> 중요!!
	@GetMapping("/link")
	public String link(Model model) {
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", " data2");
		return "basic/link";
	}

	//리터럴
	@GetMapping("/literal")
	public String literal(Model model) {
		model.addAttribute("data", "Spring!");
		return "basic/literal";
	}

	//연산자
	@GetMapping("/operation")
	public String operation(Model model) {
		model.addAttribute("nullData", null);
		model.addAttribute("data", "Spring!");
		return "basic/operation";
	}

	//속성 -> 조금 어려운듯 ㅋㅋ
	@GetMapping("/attribute")
	public String attribute() {
		return "basic/attribute";
	}

	//반복 -> 중요!
	@GetMapping("/each")
	public String each(Model model) {
		addUsers(model);
		return "basic/each";
	}

	private void addUsers(Model model) {
		List<User> list = new ArrayList<>();
		list.add(new User("userA", 10));
		list.add(new User("userB", 20));
		list.add(new User("userC", 30));

		model.addAttribute("users", list);
	}

	@GetMapping("/condition")
	public String condition(Model model) {
		addUsers(model);
		return "basic/condition";
	}

	@GetMapping("/comments")
	public String comments(Model model) {
		model.addAttribute("data", "Spring!");
		return "basic/comments";
	}

	//html 태그 안에 속성으로 기능을 정의해서 사용하는데, 이 예처럼 사용하기 애매할 때 사용하면 된다.
	@GetMapping("/block")
	public String block(Model model) {
		addUsers(model);
		return "basic/block";
	}

	@GetMapping("/javascript")
	public String javascript(Model model) {
		model.addAttribute("user", new User("userA", 10));
		addUsers(model);

		return "basic/javascript";
	}

}
