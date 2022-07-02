package com.itssainath.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert.Unit;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import static com.itssainath.constant.CredDetails.*;

public class Utility {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static void sendingSynchronousRequest() throws IOException {

        Web3j web3j = Web3j.build(new HttpService(NODE_RPC_URL));
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        System.out.println("Web3ClientVersion : " + web3ClientVersion.getWeb3ClientVersion() + " : Sync");
    }

    public static void sendingAsynchronousRequest() throws IOException {

        Web3j web3j = Web3j.build(new HttpService(NODE_RPC_URL));
        CompletableFuture<Web3ClientVersion> web3ClientVersionCompletableFuture =
                web3j.web3ClientVersion().sendAsync();

        System.out.println("Web3ClientVersion : "
                + web3ClientVersionCompletableFuture.join().getWeb3ClientVersion()
                + " : Async");
    }

    public static void sendingReactiveRequest() throws IOException {

        Web3j web3j = Web3j.build(new HttpService(NODE_RPC_URL));
        web3j.web3ClientVersion().flowable()
                .subscribe(web3ClientVersion -> {
                    System.out.println("Web3ClientVersion : "
                            + web3ClientVersion.getWeb3ClientVersion() + " : Reactive");
                });
    }

    public static void sendingEther() throws Exception {

        Web3j web3j = Web3j.build(new HttpService(NODE_RPC_URL));
        Credentials credentials = Credentials.create(ACCOUNT1_PRIVATE_KEY);

        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j,
                credentials,
                ACCOUNT2_PUBLIC_KEY,
                BigDecimal.valueOf(1.0),
                Unit.ETHER).send();

        EthTransaction ethTransaction = web3j.ethGetTransactionByBlockHashAndIndex(transactionReceipt.getBlockHash(),
                transactionReceipt.getTransactionIndex()).send();

        System.out.println("Eth_Transaction : " + MAPPER.writeValueAsString(ethTransaction));
        System.out.println("TransactionReceipt : " + MAPPER.writeValueAsString(transactionReceipt));
    }

    public static void sendingEtherWithCustomRawTransaction() throws Exception {

        Web3j web3j = Web3j.build(new HttpService(NODE_RPC_URL));
        Credentials credentials = Credentials.create(ACCOUNT1_PRIVATE_KEY);
        EthGetTransactionCount transactionCount =
                web3j.ethGetTransactionCount(ACCOUNT1_PUBLIC_KEY, DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = transactionCount.getTransactionCount();
        RawTransaction rawTransaction =
                RawTransaction.createEtherTransaction(nonce,
                        GAS_PRICE, GAS_LIMIT, ACCOUNT2_PUBLIC_KEY, BigInteger.valueOf(1000000000000000000l));
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
        EthTransaction ethTransaction = web3j.ethGetTransactionByHash(ethSendTransaction.getTransactionHash()).send();

        System.out.println("Eth_Transaction : " + MAPPER.writeValueAsString(ethTransaction));
        System.out.println("EthSendTransaction : " + MAPPER.writeValueAsString(ethSendTransaction));
    }


}