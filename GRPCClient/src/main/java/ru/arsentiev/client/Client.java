package ru.arsentiev.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.arsentiev.grpc.HelloServiceGrpc;
import ru.arsentiev.grpc.HelloServiceOuterClass;

import java.util.Arrays;
import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();


        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloServiceOuterClass.HelloRequest request = HelloServiceOuterClass.HelloRequest.newBuilder()
                .setName("Nikitos")
                .addAllHobbies(Arrays.asList("Hockey", "Football"))
                .build();

        //вариант для одного response
//        //вот тут и осуществляется удаленный вызов
//        HelloServiceOuterClass.HelloResponse response = stub.hello(request);
//
//        System.out.println(response);

        //вариант для stream response
        Iterator<HelloServiceOuterClass.HelloResponse> iterator = stub.hello(request);
        while (iterator.hasNext()) {
            HelloServiceOuterClass.HelloResponse helloResponse = iterator.next();
            System.out.println(helloResponse);
        }

        channel.shutdownNow();


    }
}
