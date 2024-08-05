package ru.mts.process_service.bpmn;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.data_models.OptyData;
import ru.mts.data_models.StatusData;

@Log4j2
@Component
public class ChangeStatusDelegate implements JavaDelegate {
    @Autowired
    private KafkaTemplate<String, StatusData> kafkaTemplate;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        StatusData statusData = new StatusData();
        log.info("Message from Change Status: " + delegateExecution.getVariableLocal("status").toString());
        statusData.setId(delegateExecution.getVariable("optyId").toString());
        statusData.setStatus(delegateExecution.getVariableLocal("status").toString());
        statusData.setComment(delegateExecution.getVariableLocal("comment").toString());
        kafkaTemplate.send("opty-status", statusData.getId(), statusData);
    }
}
