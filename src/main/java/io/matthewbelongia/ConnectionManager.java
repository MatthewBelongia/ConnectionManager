package io.matthewbelongia;

/**
 * Created by matthewb on 5/16/16.
 */
public class ConnectionManager {

    int currentConnections= 0;

    public Connection getConnection(String IP, int Port, int Protocol){
        if(currentConnections ==5) {
            return null;
        }
        currentConnections++;
        return this.new ManagedConnection(IP,Port,Protocol);
    }





    private class ManagedConnection implements Connection{

        private String IP;
        private int Port;
        private String Protocol;
        private Boolean statusOpen;

        //Protocol Options HTTP, SSH, FTP

        public ManagedConnection(String IP, int Port, int Protocol){
            this.IP = IP;
            this.Port =Port;
            this.statusOpen = true;
            switch (Protocol){
                case 0: this.Protocol = "HTTP";
                    break;
                case 1: this.Protocol = "SSH";
                    break;
                case 2: this.Protocol = "FTP";
                    break;
                default:
                    this.Protocol = "HTTP";
            }

        }

        @Override
        public String getIP() throws IPClosedException {
            if(statusOpen) {
                return IP;
            }
            System.err.println("Error");
            throw new IPClosedException();
        }

        @Override
        public int getPort() throws PortClosedException {
            if(statusOpen) {
                return Port;
            }
            System.err.println("Error");
            throw new PortClosedException();
        }

        @Override
        public String getProtocol() throws ProtocolClosedException {
            if(statusOpen) {
                return Protocol;
            }
            System.err.println("Error");
            throw new ProtocolClosedException();
        }

        @Override
        public String connect() {
            if (!statusOpen){
                return "Error connection closed";
            }
            return "You're Connected";
        }

        @Override
        public String close(){
            if (!statusOpen){
                return "Connection already closed";
            }
            currentConnections--;
            statusOpen = false;
            return "Closed";}
    }
}
