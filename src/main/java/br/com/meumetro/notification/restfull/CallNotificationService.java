package br.com.meumetro.notification.restfull;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

public class CallNotificationService {

    private static final String STATUS_OFFICIAL_END_POINT = "notification/send/status/lines";

    public static void callNotification() {
        LocalDateTime dateTime = LocalDateTime.now(DateTimeZone.forID("America/Sao_Paulo"));
        if (dateTime.hourOfDay().get() >= 1 && dateTime.hourOfDay().get() <= 6) {
            System.out.println("Hora fora de serviço: " + dateTime.hourOfDay().get());
        } else {
            callNotificationStatusOfficial(dateTime);
        }
    }

    private static void callNotificationStatusOfficial(LocalDateTime dateTime) {
        try {
            String url = String.format(Locale.getDefault(), "%s%s", RestConfig.BASE_URL, STATUS_OFFICIAL_END_POINT);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            HttpHeaders headers = new HttpHeaders();
            headers.add("security_key", RestConfig.BASIC_AUTHENTICATE_PASSWORD);

            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Notificação de status das linhas gerada! " + dateTime.toString());
            } else {
                System.out.println("Erro ao gerar notificação de status das linhas! " + dateTime.toString()
                        + "\nStatus: " + response.getStatusCode().name() + "\nCode: " + response.getStatusCode().value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
