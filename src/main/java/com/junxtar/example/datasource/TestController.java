package com.junxtar.example.datasource;


import com.junxtar.example.datasource.repository.primary.PrimaryRepository;
import com.junxtar.example.datasource.repository.secondary.SecondaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final PrimaryRepository primaryRepository;
    private final SecondaryRepository secondaryRepository;

    @GetMapping("/test")
    public void test() {
        long start = System.nanoTime();
        primaryRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
        long middle = System.nanoTime();
        secondaryRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
        long end = System.nanoTime();


        log.info("primary: {} µs", (middle - start) / 1_000);
        log.info("secondary: {} µs", (end - middle) / 1_000);
    }
}



