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
import com.appiancorp.suiteapi.process.framework.Unattended;

import com.appiancorp.suiteapi.process.palette.PaletteInfo; 
import com.appiancorp.suiteapi.security.external.SecureCredentialsStore;
import com.twilio.sdk.TwilioRestException;

@PaletteInfo(paletteCategory = "Integration Services", palette = "Connectivity Services") 
@Unattended
public class SendSMS extends AppianSmartService {

	private static final Logger LOG = Logger.getLogger(SendSMS.class);
	private final SmartServiceContext smartServiceCtx;
	private final SecureCredentialsStore scs;
	private String accountSID;
	private String authTokenScsKey;
	private String authToken;
	private String body;
	private String[] to;
	private String from;
	private String[] messageSids;

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
			messageSids = TwilioSendSMS.send(to, from, body, accountSID, authToken);
		} catch (TwilioRestException e) {
			
			e.printStackTrace();
		}

	}

	public SendSMS(SmartServiceContext smartServiceCtx, SecureCredentialsStore scs) {
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
	
	@Name("messageSids")
	public String[] getMessageSids() {
		return this.messageSids;
	}

}
