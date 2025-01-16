package ru.arsentiev.service;

import io.grpc.stub.StreamObserver;
import ru.arsentiev.grpc.HelloServiceGrpc;
import ru.arsentiev.grpc.HelloServiceOuterClass;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloServiceOuterClass.HelloRequest request,
                      StreamObserver<HelloServiceOuterClass.HelloResponse> responseObserver) {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Формируем строку со всеми хобби
            String hobbies = String.join(", ", request.getHobbiesList());

            // Формируем ответ
            HelloServiceOuterClass.HelloResponse response = HelloServiceOuterClass.HelloResponse
                    .newBuilder()
                    .setGreeting("Hello from server, " + request.getName() +
                                 " with hobbies: " + hobbies + "!")
                    .build();

            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }
}
