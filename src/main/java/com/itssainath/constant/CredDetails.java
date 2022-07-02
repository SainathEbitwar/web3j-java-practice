package com.itssainath.constant;

import java.math.BigInteger;

public abstract class CredDetails {

    // Change all credentials according to your local setup
    public static final String NODE_RPC_URL = "http://127.0.0.1:7545/";

    public static final String ACCOUNT1_PUBLIC_KEY = "0x1D2121f2dd500E2624e561d8b27421Dd05425f0c";
    public static final String ACCOUNT1_PRIVATE_KEY
            = "0x4efa8fa05dd408661d83f705c7730a31b9092042a083649a7235c26395af6a1f";

    public static final String ACCOUNT2_PUBLIC_KEY = "0xb04Bb27A33BADE894D93BC83B641d9182Be15c2d";
    public static final String ACCOUNT2_PRIVATE_KEY
            = "0x53cf33d1bbdada9db33ee6d373bf10360965d6c3896e84cd83b4a2a5cbc2b0f7";

    public static final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000l);
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(6721975);


}