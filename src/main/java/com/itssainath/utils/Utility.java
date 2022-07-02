package com.itssainath.utils;

import com.itssainath.constant.CredDetails;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Utility {

    public static void sendingSynchronousRequest() throws IOException {

        Web3j web3j = Web3j.build(new HttpService(CredDetails.NODE_RPC_URL));
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        System.out.println("Web3ClientVersion : " + web3ClientVersion.getWeb3ClientVersion() + " : Sync");
    }

    public static void sendingAsynchronousRequest() throws IOException {

        Web3j web3j = Web3j.build(new HttpService(CredDetails.NODE_RPC_URL));
        CompletableFuture<Web3ClientVersion> web3ClientVersionCompletableFuture =
                web3j.web3ClientVersion().sendAsync();

        System.out.println("Web3ClientVersion : "
                + web3ClientVersionCompletableFuture.join().getWeb3ClientVersion()
                + " : Async");
    }

    public static void sendingReactiveRequest() throws IOException {

        Web3j web3j = Web3j.build(new HttpService(CredDetails.NODE_RPC_URL));
        web3j.web3ClientVersion().flowable()
                .subscribe(web3ClientVersion -> {
                    System.out.println("Web3ClientVersion : "
                            + web3ClientVersion.getWeb3ClientVersion() + " : Reactive");
                });
    }
}
