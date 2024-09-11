package com.heartfoilo.demo.domain.donation.api;

import com.heartfoilo.demo.domain.donation.dto.requestDto.PaymentCallbackRequestDto;
import com.heartfoilo.demo.domain.donation.dto.requestDto.RequestPayDto;
import com.heartfoilo.demo.domain.donation.entity.Donation;
import com.heartfoilo.demo.domain.donation.service.DonationService;
import com.heartfoilo.demo.domain.donation.service.PaymentService;
import com.heartfoilo.demo.domain.user.entity.User;
import com.heartfoilo.demo.domain.user.repository.UserRepository;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Controller
public class LocalHomeController {
    private final UserRepository userRepository;
    private final DonationService donationService;
    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/order")
    public String order(@RequestParam(name = "message", required = false) String message,
                        @RequestParam(name = "orderUid", required = false) String id,
                        Model model) {

        model.addAttribute("message", message);
        model.addAttribute("orderUid", id);

        return "order";
    }
    private final PaymentService paymentService;

    @GetMapping("/payment/{id}")
    public String paymentPage(@PathVariable(name = "id", required = false) String id, Model model){
        RequestPayDto requestDto = paymentService.findRequestDto(id);
        model.addAttribute("requestDto",requestDto);
        System.out.println(requestDto);
        return "payment";
    }

    @GetMapping("/success-payment")
    public String successPaymentPage() {
        return "success-payment";
    }

    @GetMapping("/fail-payment")
    public String failPaymentPage() {
        return "fail-payment";
    }
    @PostMapping("/order")
    public String makeDonation(HttpServletRequest request, Long cash){
//        String userId = (String) request.getAttribute("userId");
//        Map<String, Object> response = new HashMap<>();
//        if (userId == null){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("userId is null");
//        }
        String userId = "3674718789"; // TODO: 하드코딩 해제 필요
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new RuntimeException("User not found"));

        Donation donation = donationService.makeDontaion(user,cash);
        String message = "주문 실패";
        if (donation != null) {
            message = "주문 성공";
        }
        String encode = URLEncoder.encode(message, StandardCharsets.UTF_8);

        return "redirect:/order?message="+encode+"&orderUid="+donation.getOrderUid();

    }

}
