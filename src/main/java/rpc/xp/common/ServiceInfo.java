package rpc.xp.common;

import java.util.List;

public class ServiceInfo {
	private String ip;
	private int port;
	private List<String> interfaces;

	public ServiceInfo(){}
	
	
	
	public ServiceInfo(String ip, int port, List<String> interfaces) {
		super();
		this.ip = ip;
		this.port = port;
		this.interfaces = interfaces;
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

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	@Override
	public String toString() {
		return ip + ":" + port;
	}

}
