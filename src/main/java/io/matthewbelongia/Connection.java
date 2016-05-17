package io.matthewbelongia;

/**
 * Created by matthewb on 5/16/16.
 */
public interface Connection{

    public String connect();

    public String getIP() throws IPClosedException;

    public int getPort() throws PortClosedException;

    public String getProtocol() throws ProtocolClosedException;

    public String close();

}
