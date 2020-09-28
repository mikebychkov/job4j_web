package com.ch_01_dependency_injection.ex_03_scan;

import org.springframework.stereotype.Component;

@Component
public class ConsoleInput {

    public void say() {
        System.out.println("I'm console input");
    }
}
