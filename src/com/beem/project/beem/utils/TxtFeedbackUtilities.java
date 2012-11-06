package com.beem.project.beem.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TxtFeedbackUtilities {
	private final static String cConversationSeparator = "-";
	public static String createConvId(TxtPacket iInternalPacket) {
		String conversationId = ""; 
		if (iInternalPacket.getIsSms()) {
			conversationId = cleanupPhoneNumber(iInternalPacket.getFromAddress()) + "-" + cleanupPhoneNumber(iInternalPacket.getToAddress());	
		} else {
			// ConversationId is SomGUID@txtfeedback.net-wp1@lidl.txtfeedback.net
			conversationId = iInternalPacket.getFromAddress() + "-" + iInternalPacket.getToAddress();			
		}
		return conversationId;
	}
	public static String[] getFromToConversationID(String convID) {
		return convID.split(cConversationSeparator);
	}
	
	public static String createConvId(String from, String to) {		
		String conversationId = from + cConversationSeparator + to;					
		return conversationId;
	}
		
	public static String cleanupPhoneNumber(String phoneNumber) {
		//take into account that they could start with + or 00 - so we strip away any leading + or 00
		String transformedString;
		String pattern1 = "^00";
		String pattern2 = "^+";
		// delete 00
		transformedString = phoneNumber.replaceAll(pattern1, "");
		// delete +
		transformedString = transformedString.replaceAll(pattern2, "");
		return transformedString;
	}
	
	}
