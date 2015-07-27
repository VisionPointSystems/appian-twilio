package com.visionpointsystems.appianplugins.twilioutils2;

import java.util.Map;

import org.apache.log4j.Logger;

import com.appiancorp.exceptions.InsufficientPrivilegesException;
import com.appiancorp.exceptions.ObjectNotFoundException;
import com.appiancorp.suiteapi.common.Name;
import com.appiancorp.suiteapi.process.exceptions.SmartServiceException;
import com.appiancorp.suiteapi.process.framework.AppianSmartService;
import com.appiancorp.suiteapi.process.framework.Input;
import com.appiancorp.suiteapi.process.framework.MessageContainer;
import com.appiancorp.suiteapi.process.framework.Required;
import com.appiancorp.suiteapi.process.framework.SmartServiceContext;

import com.appiancorp.suiteapi.process.palette.PaletteInfo; 
import com.appiancorp.suiteapi.security.external.SecureCredentialsStore;
import com.twilio.sdk.TwilioRestException;

@PaletteInfo(paletteCategory = "Integration Services", palette = "Connectivity Services") 
public class MakePhoneCall extends AppianSmartService {

	private static final Logger LOG = Logger.getLogger(MakePhoneCall.class);
	private final SmartServiceContext smartServiceCtx;
	private final SecureCredentialsStore scs;
	private String accountSID;
	private String authToken;
	private String authTokenScsKey;
	private String callerId;
	private String to;
	private String twimlUrl;
	private String callSid;

	@Override
	public void run() throws SmartServiceException {
		try {
			Map<String, String> credentials = scs.getSystemSecuredValues(authTokenScsKey);
			authToken = credentials.get("authtoken");
		} catch (InsufficientPrivilegesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ObjectNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			this.callSid = TwilioMakeCall.makecall(callerId, to, twimlUrl, accountSID, authToken);
		} catch (TwilioRestException e) {
			
			e.printStackTrace();
		}

	}

	public MakePhoneCall(SmartServiceContext smartServiceCtx, SecureCredentialsStore scs) {
		super();
		this.smartServiceCtx = smartServiceCtx;
		this.scs = scs;
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
	@Name("authTokenScsKey")
	public void setAuthTokenScsKey(String val) {
		this.authTokenScsKey = val;
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
