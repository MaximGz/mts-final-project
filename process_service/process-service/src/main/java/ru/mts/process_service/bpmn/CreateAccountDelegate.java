package ru.mts.process_service.bpmn;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.data_models.OptyData;

@Component
public class CreateAccountDelegate implements JavaDelegate {
    @Autowired
    private KafkaTemplate<String, OptyData> kafkaTemplate;
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String optyJson = execution.getVariable("optyData").toString();
        OptyData optyData = objectMapper.readValue(optyJson, OptyData.class);
        kafkaTemplate.send("create_account", optyData.getId(), optyData);
    }
}
