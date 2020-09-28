package com.ch_01_dependency_injection.ex_05_scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ConsoleInput {

    public void say() {
        System.out.println("I'm console input");
    }
}
