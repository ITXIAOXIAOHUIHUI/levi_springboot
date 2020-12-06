package com.levi.springboot.socket.util;

/**
 * @author jianghaihui
 * @date 2020/11/25 11:31
 */
public class SocketInfo {

        private String ip;

        private int port;

    public SocketInfo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "SocketInfo{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
