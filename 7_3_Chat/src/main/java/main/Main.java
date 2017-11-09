package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        SimpleSocketServer simpleSocketServer = new SimpleSocketServer(new ServerSocket(5050));

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println("Server started");
        executorService.submit(simpleSocketServer).get();

        simpleSocketServer.shutdown();

        executorService.shutdown();
        if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }

    }
}
