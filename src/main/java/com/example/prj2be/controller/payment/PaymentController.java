package com.example.prj2be.controller.payment;

import com.example.prj2be.config.PaymentConfig;
import com.example.prj2be.domain.Payment.Payment;
import com.example.prj2be.dto.PaymentDto;
import com.example.prj2be.dto.PaymentRequsetDto;
import com.example.prj2be.dto.PaymentResDto;
import com.example.prj2be.exception.CustomLogicException;
import com.example.prj2be.service.payment.EntityService;
import com.example.prj2be.service.payment.PaymentService;
import com.fasterxml.jackson.core.io.JsonEOFException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final EntityService entityService;
    private final PaymentConfig paymentConfig;

    @PostMapping("/toss")
    public ResponseEntity requestTossPayment(@RequestBody @Valid PaymentDto paymentReqDto) throws CustomLogicException {
        PaymentResDto paymentResDto = (PaymentResDto) paymentService.requsetTossPayment(
                paymentReqDto, paymentReqDto.getEmail());
        paymentResDto.setSuccessUrl(paymentReqDto.getSuccessUrl() == null ? paymentConfig.getSuccessUrl() : paymentReqDto.getSuccessUrl());
        paymentResDto.setFailUrl(paymentReqDto.getFailUrl() == null ? paymentConfig.getFailUrl() : paymentReqDto.getFailUrl());

        return ResponseEntity.ok(paymentResDto);
    }

    @GetMapping("/toss/success")
    public ResponseEntity tossPaymentSuccess(@RequestParam PaymentRequsetDto paymentRequsetDto) throws JsonEOFException {
        String paymentKey = paymentRequsetDto.getPaymentKey();
        String orderId = paymentRequsetDto.getPaymentUid();
        Long amount = paymentRequsetDto.getAmount();
        return ResponseEntity.ok().body(paymentService.tossPaymentSuccess(paymentKey,orderId,amount));
    }
}
