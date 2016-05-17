package io.matthewbelongia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by matthewb on 5/17/16.
 */
public class ConnectionSpecs {

    ConnectionManager connectionManagerTest;
    Connection connection1;
    Connection connection2;
    Connection connection3;
    Connection connection4;
    Connection connection5;
    Connection connection6;
    Connection connection7;


    @Before
    public void initialize(){
        connectionManagerTest = new ConnectionManager();
        connection1 = connectionManagerTest.getConnection("100.000.001",0,0);
        connection2 = connectionManagerTest.getConnection("100.000.002",1,1);
        connection3 = connectionManagerTest.getConnection("100.000.003",2,2);
        connection4 = connectionManagerTest.getConnection("100.000.004",3,3);
        connection5 = connectionManagerTest.getConnection("100.000.005",4,4);
        connection6 = connectionManagerTest.getConnection("100.000.006",5,5);
        connection7 = connectionManagerTest.getConnection("100.000.007",6,6);


    }

    @Test
    public void testConnectionLimit(){
        assertNotNull(connection1);
        assertNotNull(connection2);
        assertNotNull(connection3);
        assertNotNull(connection4);
        assertNotNull(connection5);
        assertNull(connection6);
        assertNull(connection7);
    }

    @Test
    public void testGetIP (){

        String actualIP=null;
        try{actualIP = connection1.getIP();}catch (IPClosedException e){actualIP = "error";}
        assertEquals("IP should be returned",actualIP,"100.000.001");
        String otherIP=null;
        try{otherIP = connection6.getIP();}catch (IPClosedException e){otherIP = "closed";}catch (NullPointerException e){ otherIP = "nulled";}
        assertEquals("otherIP should be nulled",otherIP,"nulled");
        connection2.close();
        String closedIP=null;
        try{closedIP = connection2.getIP();}catch (IPClosedException e){closedIP = "closed";}catch (NullPointerException e){closedIP = "nulled";}
        assertEquals("IP should return error","closed",closedIP);
    }

    @Test
    public void testGetPort(){
        int actualPort=0;
        try{actualPort = connection1.getPort();}catch (PortClosedException e){actualPort = -1;}
        assertEquals("Port should be returned",actualPort,0);
        int otherPort=0;
        try{otherPort = connection6.getPort();}catch (PortClosedException e){otherPort = -1;}catch (NullPointerException e){ otherPort = -2;}
        assertEquals("Port should be nulled",otherPort,-2);
        connection2.close();
        int closedPort=0;
        try{closedPort = connection2.getPort();}catch (PortClosedException e){closedPort = -1;}catch (NullPointerException e){closedPort = -2;}
        assertEquals("Port should return error",closedPort,-1);
    }

    @Test
    public void testGetProtocol (){

        String actualProtocol=null;
        try{actualProtocol = connection1.getProtocol();}catch (ProtocolClosedException e){actualProtocol = "error";}
        assertEquals("IP should be returned",actualProtocol,"HTTP");
        String beforeClosedProtocol=null;
        try{beforeClosedProtocol = connection2.getProtocol();}catch (ProtocolClosedException e){actualProtocol = "error";}
        assertEquals("IP should be returned",beforeClosedProtocol,"SSH");
        String otherProtocol=null;
        try{otherProtocol = connection6.getProtocol();}catch (ProtocolClosedException e){otherProtocol = "closed";}catch (NullPointerException e){ otherProtocol = "nulled";}
        assertEquals("otherIP should be nulled",otherProtocol,"nulled");
        connection2.close();
        String closedProtocol=null;
        try{closedProtocol = connection2.getProtocol();}catch (ProtocolClosedException e){closedProtocol = "closed";}catch (NullPointerException e){closedProtocol = "nulled";}
        assertEquals("IP should return error","closed",closedProtocol);
    }

    @Test
    public void testClose(){
        String beforeCloseConnect = connection4.connect();
        String beforeClose = connection4.close();
        String afterCloseConnect = connection4.connect();
        String afterClose = connection4.close();
        assertNotEquals(beforeCloseConnect,afterCloseConnect);
        assertNotEquals(beforeClose,afterClose);
    }
}
