package de.saar.coli.dialogos.echoclient;

import com.clt.script.exp.*;
import com.clt.script.exp.values.*;
import com.clt.dialog.client.*;

class EchoClient extends Client {
    public EchoClient() {

    }

    // this is the name that the DialogOS Rendezvous connector uses to find your client
    @Override
    public String getName() {
        return "Echo";
    }

    @Override
    public void output(Value v) {
        // check what kind of value it is
        if (v instanceof StringValue) {
            System.out.println("Received a string: " + ((StringValue) v).getString());
        } else if (v instanceof IntValue) {
            System.out.println("Received an int: " + ((IntValue) v).getInt());
        } else {
            System.out.println("Received something else: " + v.toString());
        }

        // send it back
        try {
            send(v);
        } catch (Exception exn) {
            error(exn);
        }
    }

    @Override
    public void sessionStarted() {
        System.out.println("Session started.");
    }

    @Override
    public void reset() {
        System.out.println("Reset.");
    }

    @Override
    public void stateChanged(ConnectionState state) {
        System.out.println("State changed: " + state);
    }

    @Override
    public void error(Throwable t) {
        System.err.println("An internal error has occurred: " + t);
    }

    public static void main(String[] args) {
        try {
            new EchoClient().open();
        } catch (Exception exn) {
            exn.printStackTrace();
        }
    }
}
