package com.levi.springboot.websocket.csdnSocket;

/**
 * @author jianghaihui
 * @date 2020/11/18 11:46
 */
public class SocketPool {

    private static final ConcurrentHashMap<String, ClientSocket> ONLINE_SOCKET_MAP = new ConcurrentHashMap<>();


    public static void add(ClientSocket clientSocket){
        if (clientSocket != null && !clientSocket.getKey().isEmpty())
            ONLINE_SOCKET_MAP.put(clientSocket.getKey(), clientSocket);
    }

    public static void remove(String key){
        if (!key.isEmpty())
            ONLINE_SOCKET_MAP.remove(key);
    }
}
