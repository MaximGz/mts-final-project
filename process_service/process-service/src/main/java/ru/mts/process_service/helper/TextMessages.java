package ru.mts.process_service.helper;

import lombok.Getter;

@Getter
public enum TextMessages {
    REGISTER_OPTY("Заявка успешно зарегестрирована."),
    VALIDATE_PASS("Идентификация клиента в системе успешно пройдена."),
    VALIDATE_FAILED("Клиент не найден в базе данных банка."),
    CREDIT_CHECK_PASS("Проверка кредитной истории клиента завершена успешно."),
    CREDIT_CHECK_FAILED("Клиент не прошёл кпедитную историю.");

    private final String message;

    TextMessages(String message) {
        this.message = message;
    }
}
