package com.visionpointsystems.appianplugins.twilioutils;

import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.common.Name;
import com.appiancorp.suiteapi.process.exceptions.SmartServiceException;
import com.appiancorp.suiteapi.process.framework.AppianSmartService;
import com.appiancorp.suiteapi.process.framework.Input;
import com.appiancorp.suiteapi.process.framework.MessageContainer;
import com.appiancorp.suiteapi.process.framework.Required;
import com.appiancorp.suiteapi.process.framework.SmartServiceContext;
import com.appiancorp.suiteapi.process.framework.Unattended;

import com.appiancorp.suiteapi.process.palette.PaletteInfo; 
import com.twilio.sdk.TwilioRestException;

@PaletteInfo(paletteCategory = "Integration Services", palette = "Connectivity Services") 
@Unattended
public class SendSMS extends AppianSmartService {

	private static final Logger LOG = Logger.getLogger(SendSMS.class);
	private final SmartServiceContext smartServiceCtx;
	private String accountSID;
	private String authToken;
	private String body;
	private String[] to;
	private String from;

	@Override
	public void run() throws SmartServiceException {
		try {
			TwilioSendSMS.send(to, from, body, accountSID, authToken);
		} catch (TwilioRestException e) {
			
			e.printStackTrace();
		}

	}

	public SendSMS(SmartServiceContext smartServiceCtx) {
		super();
		this.smartServiceCtx = smartServiceCtx;
	}

	public void onSave(MessageContainer messages) {
	}

	public void validate(MessageContainer messages) {
	}

	@Input(required = Required.ALWAYS)
	@Name("accountSID")
	public void setAccountSID(String val) {
		this.accountSID = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("authToken")
	public void setAuthToken(String val) {
		this.authToken = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("body")
	public void setBody(String val) {
		this.body = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("to")
	public void setTo(String[] val) {
		this.to = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("from")
	public void setFrom(String val) {
		this.from = val;
	}

}
