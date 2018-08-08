package com.luis.kingboot.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import com.luis.strategy.datapackage.scene.NotificationListData;
import com.luis.strategy.datapackage.scene.PreSceneListData;
import com.luis.strategy.datapackage.scene.SceneData;
import com.luis.strategy.datapackage.scene.SceneListData;

public class OnlineInputOutput {
	
	private static OnlineInputOutput instance;
	
	public static final String URL_CREATE_INSCRIPTION = "createInscriptionServlet";
	public static final String URL_CREATE_PRE_SCENE = "createPreSceneServlet";
	
	public static final String URL_CREATE_NOTIFICATION = "createNotificationServlet";
	public static final String URL_GET_NOTIFICATION_LIST = "getNotificationListServlet";
	public static final String URL_UPDATE_NOTIFICATION = "updateNotificationSceneServlet";
	public static final String URL_GET_SCENE_LIST = "getSceneListServlet";
	public static final String URL_GET_PRE_SCENE_LIST = "getPreSceneListServlet";
	public static final String URL_GET_ALL_PRE_SCENE_LIST = "getAllPreSceneListServlet";
	public static final String URL_UPDATE_SCENE = "updateSceneServlet";
	public static final String URL_GET_START_SCENE = "getStartSceneServlet";
	public static final String URL_GET_SCENE = "getSceneController";
	
	//Notifications code
	public static final int CODE_NOTIFICATION_YOU_LOST_GAME= 0;
	public static final int CODE_NOTIFICATION_LOST_GAME= CODE_NOTIFICATION_YOU_LOST_GAME+1;
	public static final int CODE_NOTIFICATION_YOUR_ARMY_DEFEATED= CODE_NOTIFICATION_LOST_GAME+1;
	public static final int CODE_NOTIFICATION_YOUR_ARMY_WON= CODE_NOTIFICATION_YOUR_ARMY_DEFEATED+1;
	public static final int CODE_NOTIFICATION_YOUR_ARMY_DESTROYED= CODE_NOTIFICATION_YOUR_ARMY_WON+1;
	public static final int CODE_NOTIFICATION_YOUR_ARMY_DESTROYED_ENEMY= CODE_NOTIFICATION_YOUR_ARMY_DESTROYED+1;
	public static final int CODE_NOTIFICATION_CHANGE_CAPITAL= CODE_NOTIFICATION_YOUR_ARMY_DESTROYED_ENEMY+1;
	
	public static OnlineInputOutput getInstance(){
		if(instance == null){
			instance = new OnlineInputOutput();
		}
		return instance;
	}
	
	public String sendNotifiation(String scene, String from, String to, String message, String type){
		HttpURLConnection connection = null;
		String result = "";
		
		try {
			// open URL connection
			URL url = new URL(ServerURL.SERVER_URL + URL_CREATE_NOTIFICATION);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("scene", scene);
			connection.setRequestProperty("from", from);
			connection.setRequestProperty("to", to);
			connection.setRequestProperty("message", message);
			connection.setRequestProperty("type", type);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			BufferedReader in = 
					new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String str = "";
			while ((str = in.readLine()) != null) {
				result += str;// + "\n";
			}
			in.close();

			//System.out.println(result);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public NotificationListData reviceNotificationListData(String to){
		NotificationListData notificationListData = null;
		HttpURLConnection connection = null;
		try {
			// open URL connection
			String encodeUrl = ServerURL.SERVER_URL + URL_GET_NOTIFICATION_LIST;
			URL url = new URL(encodeUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("to", to);
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.setUseCaches(false);
			
			ObjectInputStream objIn = new ObjectInputStream(connection.getInputStream());
			notificationListData = (NotificationListData) objIn.readObject();
			objIn.close();
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificationListData;
	}
	
	public String sendUser(String URL, String name, String password){
		HttpURLConnection connection = null;
		String result = "";
		try {
			// open URL connection
			URL url = new URL(ServerURL.SERVER_URL + URL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("name", name);
			connection.setRequestProperty("password", password);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			BufferedReader in = 
					new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String str = "";
			while ((str = in.readLine()) != null) {
				result += str;// + "\n";
			}
			in.close();

			//System.out.println(result);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public String sendPreScene(String map, String user, String name){
		HttpURLConnection connection = null;
		String result = "";
		try {
			// open URL connection
			URL url = new URL(ServerURL.SERVER_URL + URL_CREATE_PRE_SCENE);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("map", map);
			connection.setRequestProperty("user", user);
			connection.setRequestProperty("name", name);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			BufferedReader in = 
					new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String str = "";
			while ((str = in.readLine()) != null) {
				result += str;// + "\n";
			}
			in.close();

			//System.out.println(result);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public String sendInscription(String scene, String user, String create){
		HttpURLConnection connection = null;
		String result = "";
		try {
			// open URL connection
			URL url = new URL(ServerURL.SERVER_URL + URL_CREATE_INSCRIPTION);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("scene", scene);
			connection.setRequestProperty("user", user);
			connection.setRequestProperty("create", create);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			BufferedReader in = 
					new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String str = "";
			while ((str = in.readLine()) != null) {
				result += str;// + "\n";
			}
			in.close();

			//System.out.println(result);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public String sendDataPackage(String URL, Serializable dataPackage){
		HttpURLConnection connection = null;
		String result = "";
		try {
			// open URL connection
			URL url = new URL(ServerURL.SERVER_URL + URL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// send object
			ObjectOutputStream objOut = new ObjectOutputStream(
					connection.getOutputStream());
			objOut.writeObject(dataPackage);
			objOut.flush();
			objOut.close();

			BufferedReader in = 
					new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String str = "";
			while ((str = in.readLine()) != null) {
				result += str;// + "\n";
			}
			in.close();

			//System.out.println(result);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public PreSceneListData revicePreSceneListData(String user){
		PreSceneListData preSceneListData = null;
		HttpURLConnection connection = null;
		try {
			// open URL connection
			//String encodeUrl = SERVER_URL + URL + URLEncoder.encode("?user=" + user);
			String encodeUrl = ServerURL.SERVER_URL + URL_GET_PRE_SCENE_LIST;
			URL url = new URL(encodeUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("user", user);
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.setUseCaches(false);
			
			ObjectInputStream objIn = new ObjectInputStream(connection.getInputStream());
			preSceneListData = (PreSceneListData) objIn.readObject();
			objIn.close();
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return preSceneListData;
	}
	
	public PreSceneListData reviceAllPreSceneListData(){
		PreSceneListData preSceneListData = null;
		HttpURLConnection connection = null;
		try {
			// open URL connection
			//String encodeUrl = SERVER_URL + URL + URLEncoder.encode("?user=" + user);
			String encodeUrl = ServerURL.SERVER_URL + URL_GET_ALL_PRE_SCENE_LIST;
			URL url = new URL(encodeUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.setUseCaches(false);
			
			ObjectInputStream objIn = new ObjectInputStream(connection.getInputStream());
			preSceneListData = (PreSceneListData) objIn.readObject();
			objIn.close();
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return preSceneListData;
	}
	
	public SceneListData reviceSceneListData(String user){
		SceneListData sceneListData = null;
		HttpURLConnection connection = null;
		try {
			// open URL connection
			//String encodeUrl = Define.SERVER_URL + URL + URLEncoder.encode("?user=" + user);
			String encodeUrl = ServerURL.SERVER_URL + URL_GET_SCENE_LIST;
			URL url = new URL(encodeUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("user", user);
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.setUseCaches(false);
			
			ObjectInputStream objIn = new ObjectInputStream(connection.getInputStream());
			sceneListData = (SceneListData) objIn.readObject();
			objIn.close();
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sceneListData;
	}
	
	
	public SceneData reviceSceneData(String URL, String scene){
		SceneData sceneData = null;
		HttpURLConnection connection = null;
		try {
			// open URL connection
			//String encodeUrl = Define.SERVER_URL + URL + URLEncoder.encode("?user=" + user);
			String encodeUrl = ServerURL.SERVER_URL + URL;
			URL url = new URL(encodeUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("scene", scene);
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.setUseCaches(false);
			
			ObjectInputStream objIn = new ObjectInputStream(connection.getInputStream());
			sceneData = (SceneData) objIn.readObject();
			objIn.close();
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sceneData;
	}

}
