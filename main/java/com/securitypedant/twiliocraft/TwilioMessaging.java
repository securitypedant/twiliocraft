package com.securitypedant.twiliocraft;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
 
import java.util.ArrayList;
import java.util.List;

public class TwilioMessaging {

	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = CommonProxy.TWILIO_ACCOUNT_SID;
	public static final String AUTH_TOKEN = CommonProxy.TWILIO_AUTH_TOKEN;
	public static final String TWILIO_NUMBER = CommonProxy.TWILIO_NUMBER;
	
	public void sendSMS(String smsMessage, String toNumber) throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
	    // Build a filter for the MessageList
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("Body", smsMessage));
	    params.add(new BasicNameValuePair("To", toNumber));
	    params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

	    MessageFactory messageFactory = client.getAccount().getMessageFactory();
	    Message message = messageFactory.create(params);
	    System.out.println(message.getSid());
	}
	
}
