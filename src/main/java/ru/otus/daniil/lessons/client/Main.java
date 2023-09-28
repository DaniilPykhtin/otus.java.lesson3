package ru.otus.daniil.lessons.client;

import java.net.Socket;
import java.net.UnknownHostException;


public class Main {
    public static void main(String[] args) {


        try (Client client = new Client(new Socket("localhost", 8088))) {

            System.out.println(client.read());  // hello string

            while (true) {
                String str = client.prepareExpression();
                client.write(str);
                if (str.equals(":q") ) {

                    break;
                }
                System.out.println(client.read());

            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
