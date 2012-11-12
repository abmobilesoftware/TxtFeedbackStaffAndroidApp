//package com.beem.project.beem.service;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.java.otr4j.OtrException;
//
//import org.jivesoftware.smack.Chat;
//
//import android.os.Environment;
//import android.os.RemoteException;
//import android.preference.PreferenceManager;
//import android.util.Log;
//
//import com.beem.project.beem.BeemApplication;
//import com.beem.project.beem.BeemService;
//import com.beem.project.beem.otr.BeemOtrManager;
//import com.beem.project.beem.service.BeemChatManager.ChatListener;
//import com.beem.project.beem.service.aidl.IChat;
//import com.beem.project.beem.service.aidl.IMessageListener;
//import com.beem.project.beem.utils.TxtPacket;
//
//public class ChatAdapterSyndication {
//	private static final String TAG = "ChatAdapterSyndication";
//	private final Map<String, ChatAdapter> mAdapterChats = new HashMap<String, ChatAdapter>();
//	private final BeemService mService;
//	private BeemChatManager mManager;
//	public ChatAdapterSyndication(final BeemService service, final Chat defaultChat, BeemChatManager mng) {
//		mService = service;
//		//make sure that the initial chat is present
//		mManager = mng;
//		this.getChat(defaultChat, defaultChat.getThreadID(), mManager);
//	}
//	
//	public ChatAdapter getChat(Chat chat, String key, BeemChatManager mng) {
//		//String key = chat.getThreadID();
//		//TODO DA getParticipant()
//		if (mAdapterChats.containsKey(key)) {
//		    return mAdapterChats.get(key);
//		}
//		//DA if mng is null then we reuse one of the already existing managers.
//		if (mng == null) {
//			String s = "";			
//		}
//		ChatAdapter res = new ChatAdapter(chat,key, this);
//		BeemChatManager newMng = new BeemChatManager(mManager);
//		res.addMessageListener(newMng.new ChatListener(mManager));
//		//TODO DA - fix the history issue
////		boolean history = PreferenceManager.getDefaultSharedPreferences(mService.getBaseContext()).getBoolean(
////		    "settings_key_history", false);
////		String accountUser = PreferenceManager.getDefaultSharedPreferences(mService.getBaseContext()).getString(
////		    BeemApplication.ACCOUNT_USERNAME_KEY, "");
////		String historyPath = PreferenceManager.getDefaultSharedPreferences(mService.getBaseContext()).getString(
////		    BeemApplication.CHAT_HISTORY_KEY, "");
////		if ("".equals(historyPath)) historyPath = "/Android/data/com.beem.project.beem/chat/";
////		res.setHistory(history);
////		res.setAccountUser(accountUser);
////		res.listenOtrSession();
////		res.setHistoryPath(new File(Environment.getExternalStorageDirectory(), historyPath));
//		Log.d(TAG, "getChat put " + key);
//		mAdapterChats.put(key, res);
//		return res;
//	    }
//	    
//	    public ChatAdapter getChat(Contact contact) {
//		String key = contact.getJID();
//		return mAdapterChats.get(key);
//	    }
//	    
//	    private ChatAdapter getChat(String key) {
//	    	return mAdapterChats.get(key);
//	    }
//	    
//	    public void destroyChat(IChat chat) throws RemoteException {
//		// Can't remove it. otherwise we will lose all future message in this chat
//		// chat.removeMessageListener(mChatListener);
//		if (chat == null)
//		    return;
//		deleteChatNotification(chat);
//		//mChats.remove(chat.getParticipant().getJID());
//		mAdapterChats.remove(chat.getThreadID());
//	    }
//	    
//	    public void deleteChatNotification(IChat chat) {
//	    	try {
//	    	    mService.deleteNotification(chat.getThreadID().hashCode());
//	    	} catch (RemoteException e) {
//	    	    Log.v(TAG, "Remote exception ", e);
//	    	}
//	    }
//		
//	    public void processMessage(Chat chat, org.jivesoftware.smack.packet.Message message) {
//			
//			TxtPacket pkg = new TxtPacket(message.getBody());
//			String key = pkg.getFromAddress();			
//		    Message msg = new Message(message, pkg.getConversationId());
//		    Log.d(TAG, "new msg " + msg.getBody());
//		    String body;
//
////		    if (mOtrSessionId != null) {
////			try {
////			    body = BeemOtrManager.getInstance().getOtrManager()
////				.transformReceiving(mOtrSessionId, msg.getBody());
////			    msg.setBody(body);
////			} catch (OtrException e) {
////			    Log.w(TAG, "Unable to decrypt OTR message", e);
////			}
////		    }
//		    //TODO add que les message pas de type errors
//		    ChatAdapter cht = getChat(chat, key, null);
//		    cht.addMessage(msg);
//		    final int n = cht.getRemoteListeners().beginBroadcast();
//		    for (int i = 0; i < n; i++) {
//			IMessageListener listener = cht.getRemoteListeners().getBroadcastItem(i);
//			try {
//			    if (listener != null)
//				listener.processMessage(cht, msg);
//			} catch (RemoteException e) {
//			    Log.w(TAG, "Error while diffusing message to listener", e);
//			}
//		    }
//		    cht.getRemoteListeners().finishBroadcast();
//		}
//}
