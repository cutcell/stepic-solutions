package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.*;

public class SimpleSocketServer implements Callable<SimpleSocketServer> {

    private final ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private Queue<Socket> socketsToClose = new ConcurrentLinkedQueue<>();

    public SimpleSocketServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public SimpleSocketServer call() throws Exception {

        while (true) {

            Socket clientSocket = serverSocket.accept();
            executor.submit(() -> processSocket(clientSocket));
            closeSockets();

        }

    }

    public void shutdown() throws IOException, InterruptedException {

        while (!socketsToClose.isEmpty()) {
            Socket s = socketsToClose.poll();
            if (s != null) {
                s.close();
            }

        }

        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }

    }

    private void processSocket(Socket clientSocket) {

        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }

            socketsToClose.offer(clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void closeSockets() throws IOException {

        if (socketsToClose.isEmpty()) {
            return;
        }

        Socket s = socketsToClose.poll();

        if (s != null) {
            s.close();
        }

    }

}
