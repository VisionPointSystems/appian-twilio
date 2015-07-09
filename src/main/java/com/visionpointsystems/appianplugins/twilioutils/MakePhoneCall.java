package com.visionpointsystems.appianplugins.twilioutils;

import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.common.Name;
import com.appiancorp.suiteapi.process.exceptions.SmartServiceException;
import com.appiancorp.suiteapi.process.framework.AppianSmartService;
import com.appiancorp.suiteapi.process.framework.Input;
import com.appiancorp.suiteapi.process.framework.MessageContainer;
import com.appiancorp.suiteapi.process.framework.Required;
import com.appiancorp.suiteapi.process.framework.SmartServiceContext;

import com.appiancorp.suiteapi.process.palette.PaletteInfo; 
import com.twilio.sdk.TwilioRestException;

@PaletteInfo(paletteCategory = "Integration Services", palette = "Connectivity Services") 
public class MakePhoneCall extends AppianSmartService {

	private static final Logger LOG = Logger.getLogger(MakePhoneCall.class);
	private final SmartServiceContext smartServiceCtx;
	private String accountSID;
	private String authToken;
	private String callerId;
	private String to;
	private String twimlUrl;
	private String callSid;

	@Override
	public void run() throws SmartServiceException {
		try {
			this.callSid = TwilioMakeCall.makecall(callerId, to, twimlUrl, accountSID, authToken);
		} catch (TwilioRestException e) {
			
			e.printStackTrace();
		}

	}

	public MakePhoneCall(SmartServiceContext smartServiceCtx) {
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
	@Name("twimlUrl")
	public void setTwimlUrl(String val) {
		this.twimlUrl = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("to")
	public void setTo(String val) {
		this.to = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("callerId")
	public void setCallerId(String val) {
		this.callerId = val;
	}
	
	@Name("callSid")
	public String getCallSid() {
		return this.callSid;
	}
	

}
