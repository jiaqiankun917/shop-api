package com.fh.shop.api.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//可以注入
@Configuration
public class MQConfig {

  public static final String MAIL_EXCHANGE = "mailExchange";
  public static final String MAIL_QUEUE = "mailQueue";
  public static final String MAIL = "mail";


  @Bean
  public DirectExchange mailExchange(){
    return new DirectExchange(MAIL_EXCHANGE,true,false);
  }

  @Bean
  public Queue mailQueue(){
    return new Queue(MAIL_QUEUE,true);
  }

  @Bean
  public Binding mailBinding(){
    return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MAIL);
  }


  public static final String ORDER_EXCHANGE = "orderExchange";
  public static final String ORDER_QUEUE = "orderQueue";
  public static final String ORDER = "order";

  @Bean
  public DirectExchange orderExchange(){
    return new DirectExchange(ORDER_EXCHANGE,true,false);
  }
  @Bean
  public Queue orderQueue(){
    return new Queue(ORDER_QUEUE,true);
  }
  @Bean
  public Binding orderBinding(){
    return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(ORDER);
  }

}
