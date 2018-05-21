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
	
	public static final String SERVER_URL = "http://172.104.228.65:8080/KingServer/";//Online
	//public static final String SERVER_URL = "http://192.168.1.110:8080/KingServer/";//Local
	
	private static OnlineInputOutput instance;
	
	public static final String URL_CREATE_NOTIFICATION = "createNotificationServlet";
	public static final String URL_GET_NOTIFICATION_LIST = "getNotificationListServlet";
	public static final String URL_UPDATE_NOTIFICATION = "updateNotificationSceneServlet";
	public static final String URL_GET_SCENE_LIST = "getSceneListServlet";
	public static final String URL_GET_PRE_SCENE_LIST = "getPreSceneListServlet";
	public static final String URL_GET_ALL_PRE_SCENE_LIST = "getAllPreSceneListServlet";
	public static final String URL_UPDATE_SCENE = "updateSceneServlet";
	public static final String URL_GET_START_SCENE = "getStartSceneServlet";
	public static final String URL_GET_SCENE = "getSceneController";
	
	public static OnlineInputOutput getInstance(){
		if(instance == null){
			instance = new OnlineInputOutput();
		}
		return instance;
	}
	
	public String sendNotifiation(String URL, String scene, String user, String message){
		HttpURLConnection connection = null;
		String result = "";
		
		try {
			// open URL connection
			URL url = new URL(SERVER_URL + URL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("scene", scene);
			connection.setRequestProperty("user", user);
			connection.setRequestProperty("message", message);
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

			System.out.println(result);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public NotificationListData reviceNotificationListData(String user){
		NotificationListData notificationListData = null;
		HttpURLConnection connection = null;
		try {
			// open URL connection
			String encodeUrl = SERVER_URL + URL_GET_NOTIFICATION_LIST;
			URL url = new URL(encodeUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/octet-stream");
			connection.setRequestMethod("GET");
			connection.setRequestProperty("user", user);
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
			URL url = new URL(SERVER_URL + URL);
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

			System.out.println(result);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public String sendPreScene(String URL, String map, String user, String name){
		HttpURLConnection connection = null;
		String result = "";
		try {
			// open URL connection
			URL url = new URL(SERVER_URL + URL);
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

			System.out.println(result);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public String sendInscription(String URL, String scene, String user, String create){
		HttpURLConnection connection = null;
		String result = "";
		try {
			// open URL connection
			URL url = new URL(SERVER_URL + URL);
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

			System.out.println(result);
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
			URL url = new URL(SERVER_URL + URL);
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

			System.out.println(result);
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	public PreSceneListData revicePreSceneListData(String URL, String user){
		PreSceneListData preSceneListData = null;
		HttpURLConnection connection = null;
		try {
			// open URL connection
			//String encodeUrl = SERVER_URL + URL + URLEncoder.encode("?user=" + user);
			String encodeUrl = SERVER_URL + URL;
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
	
	public PreSceneListData reviceAllPreSceneListData(String URL){
		PreSceneListData preSceneListData = null;
		HttpURLConnection connection = null;
		try {
			// open URL connection
			//String encodeUrl = SERVER_URL + URL + URLEncoder.encode("?user=" + user);
			String encodeUrl = SERVER_URL + URL;
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
			String encodeUrl = SERVER_URL + URL_GET_SCENE_LIST;
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
			String encodeUrl = SERVER_URL + URL;
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
