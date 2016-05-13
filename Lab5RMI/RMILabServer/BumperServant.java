//*************************************************************
// CalculatorServant.java
// A Remote object class that implements Calculator.
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.math.BigInteger;

public class BumperServant extends UnicastRemoteObject implements Bumper {

    private BigInteger source = null;
    private BigInteger one = null;
    public BumperServant() throws RemoteException {
        source = new BigInteger("0");
        one = new BigInteger("1");
    }

    public boolean bump() throws RemoteException {
        source = source.add(one);
        return true;
    }

    public BigInteger get(){
        return source;
    }
}
