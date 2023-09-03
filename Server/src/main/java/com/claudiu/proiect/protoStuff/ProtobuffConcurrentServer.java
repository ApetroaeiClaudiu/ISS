package com.claudiu.proiect.protoStuff;

import com.claudiu.proto.client.IServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class ProtobuffConcurrentServer extends AbsConcurrentServer implements CommandLineRunner {
    private IServer server;
    @Autowired
    public ProtobuffConcurrentServer(@Value("55560")int port, IServer server) {
        super(port);
        this.server = server;
        System.out.println("Concurrent Server Starting");
    }

    @Override
    protected Thread createWorker(Socket client) {
        ProtoWorker worker=new ProtoWorker(server,client);
        Thread tw=new Thread(worker);
        return tw;
    }

    @Override
    public void run(String... args) throws Exception {
        this.start();
    }
}