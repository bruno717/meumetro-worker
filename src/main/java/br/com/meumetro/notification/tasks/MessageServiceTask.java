package br.com.meumetro.notification.tasks;

import java.util.TimerTask;

import br.com.meumetro.notification.restfull.CallNotificationService;

public class MessageServiceTask extends TimerTask {

	@Override
	public void run() {
		CallNotificationService.callNotification();
		
	}
}