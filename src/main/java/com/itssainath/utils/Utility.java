package com.itssainath.utils;

import com.itssainath.constant.CredDetails;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

public class Utility {

    public String sendingSynchronousRequest() throws IOException {

        Web3j web3j = Web3j.build(new HttpService(CredDetails.NODE_RPC_URL));
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        return "Web3ClientVersion : " + web3ClientVersion.getWeb3ClientVersion();
    }
}
