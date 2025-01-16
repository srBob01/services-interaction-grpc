package ru.arsentiev;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ru.arsentiev.service.HelloServiceImpl;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new HelloServiceImpl())
                .build();

        server.start();

        System.out.println("Server started");

        server.awaitTermination();
    }
}
