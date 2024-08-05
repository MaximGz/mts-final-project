package ru.mts.process_service.bpmn;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.data_models.StatusData;

@Component
public class SendForCheckHistory implements JavaDelegate {
    @Autowired
    private KafkaTemplate<String, StatusData> kafkaTemplate;
    @Override
    public void execute(DelegateExecution delegateExecution) {
        StatusData statusData = new StatusData();
        statusData.setId(delegateExecution.getVariable("optyId").toString());
        statusData.setStatus(delegateExecution.getVariable("status").toString());
        kafkaTemplate.send("check_history_send", statusData.getId(), statusData);
    }
}
