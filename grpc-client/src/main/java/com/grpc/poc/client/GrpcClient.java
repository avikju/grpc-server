package com.grpc.poc.client;

import com.grpc.poc.User.APIResponse;
import com.grpc.poc.User.LoginRequest;
import com.grpc.poc.userGrpc;
import com.grpc.poc.userGrpc.userBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

	public static void main(String[] args) {

		//this is the channel through which the communication will happen
		//we could use usetls for mroe security
		ManagedChannel channel=ManagedChannelBuilder.forAddress("localhost", 9191).usePlaintext().build();
		//generally proto files are given to the client and client generates the stubs
		//client needs to generate the language specific files from proto files by using maven plugin 
		userBlockingStub userStub=userGrpc.newBlockingStub(channel);
		LoginRequest loginRequest=LoginRequest.newBuilder().setUsername("Hello").setPassword("Hello").build();
		APIResponse apiResponse=userStub.login(loginRequest);
		System.out.println("Response:"+apiResponse.getResponsemessage()+"-"+apiResponse.getResponseCode());

	}

}
