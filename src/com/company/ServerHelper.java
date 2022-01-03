package com.company;

import java.net.Socket;

public class ServerHelper extends Thread {

    Socket socket;

    ServerHelper(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
