package com.fh.shop.api.mq;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//启动扫描
@Component
public class MQSender {
  @Autowired
  private AmqpTemplate amqpTemplate;

  public void  sendMail(String info){

    amqpTemplate.convertAndSend(MQConfig.MAIL_EXCHANGE,MQConfig.MAIL,info);
  }

  public void sendMailMessage(MailMessage mailMessage){
      String mailJson = JSONObject.toJSONString(mailMessage);
      amqpTemplate.convertAndSend(MQConfig.MAIL_EXCHANGE,MQConfig.MAIL,mailJson);
  }

}
