package br.com.meumetro.notification;

import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Timer;

import br.com.meumetro.notification.tasks.MessageServiceTask;

/**
 * Hello world!
 *
 */
public class ApplicationNotification {
	
	private static final long PERIOD = 1800000;
	
	public static void main(String[] args) {
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		GregorianCalendar.getInstance(tz);
		new Timer().scheduleAtFixedRate(new MessageServiceTask(), 0, PERIOD);
	}
}
