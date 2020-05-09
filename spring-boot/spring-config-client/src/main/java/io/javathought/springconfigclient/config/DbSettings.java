package io.javathought.springconfigclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("db")
@RefreshScope
public class DbSettings {
	private String connection;
	private String host;
	private int port;
	public DbSettings() {
	}
	public String getConnection() {
		return connection;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "DbSettings [connection=" + connection + ", host=" + host + ", port=" + port + "]";
	}
	

}
