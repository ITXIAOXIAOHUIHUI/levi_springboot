package com.levi.springmachine.config;

import com.levi.springmachine.enums.Events;
import com.levi.springmachine.enums.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;

/**
 * @author jianghaihui
 * @date 2019/12/10 14:35
 */
@Configuration
@EnableStateMachine
public class StateMachingConfig  extends EnumStateMachineConfigurerAdapter<States, Events> {



}
